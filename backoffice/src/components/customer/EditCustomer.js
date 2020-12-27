import { useEffect, useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import CustomerService from '../../services/CustomerService';
import Header from '../header/Header';
import Loading from '../loading/Loading';
import { createSuccessNotification, createErrorNotification, createWarningNotification, createInfoNotification } from "../Natifications";
const EditCustomer = () => {

    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [customer, setCustomer] = useState({
        id: history.location.state?.id,
        name: '',
        surname: '',
        address: '',
        phone: ''
    });
    const { id, name, surname, address, phone } = customer;



    useEffect(() => {

        getCustomerInfo();

    }, [])

    const onChangeHandler = (e) => {

        setCustomer({ ...customer, [e.target.name]: e.target.value });
    }

    async function getCustomerInfo() {


        await CustomerService.getCustomerById(id)
            .then(res => {
                const customer = res.data;
                setCustomer({ ...customer, name: customer.name, surname: customer.surname, address: customer.address, phone: customer.phone })
                setLoading(false);
            }).catch(({ response }) => {

                createErrorNotification('Customer not found.');
                setLoading(false);
            });



    }
    const updateCustomer = async (e) => {

        e.preventDefault();

        const res = await CustomerService.updateCustomer(customer) .then(res => {
           createSuccessNotification('Updating record is successful.');
           history.push('/customers');
        }).catch(({ response }) => {

            createErrorNotification('There was a problem updating the record.');
            setLoading(false);
        });;
      
  

    }

    const getHeader = () => {
        return (
            <Header />
        )
    }
    if (loading === true) {
        return (
            <Loading />
        )
    }

    const getForm = () => {
        return (
            <form>
                <div className="form-group">
                    <label>Name :</label>
                    <input placeholder="Name" name="name" className="form-control"
                        value={name} onChange={onChangeHandler} />
                </div>
                <div className="form-group">
                    <label>Surname :</label>
                    <input placeholder="Surname" name="surname" className="form-control"
                        value={surname} onChange={onChangeHandler} />
                </div>
                <div className="form-group">
                    <label>Address :</label>
                    <input placeholder="Adress" name="address" className="form-control"
                        value={address} onChange={onChangeHandler} />
                </div>
                <div className="form-group">
                    <label>Phone :</label>
                    <input placeholder="Phone" name="phone" className="form-control"
                        value={phone} onChange={onChangeHandler} type="number" />
                </div>
                <button className="btn btn-success" onClick={(e) => updateCustomer(e)}>Save</button>
                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push('/customers')} >Cancel</button>
            </form>
        )
    }


    return (
        <div>
            {getHeader()}
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Customer</h3>
                        <div className="card-body">
                            {getForm()}
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )
}
export default EditCustomer;