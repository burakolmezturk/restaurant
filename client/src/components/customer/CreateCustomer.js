import { useEffect, useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import CustomerService from '../../services/CustomerService';

const CreateCustomer = () => {

    const history = useHistory();

    const [customer, setCustomer] = useState({
        name: '', surname: '', address: '', phone: ''
    });

    const { name, surname, address, phone } = customer;

    const onChangeHandler = (e) => {
        setCustomer({ ...customer, [e.target.name]: e.target.value });
    }

    const saveCustomer = async (e) => {
        e.preventDefault();

        if (name.trim() == '' || surname.trim() == '' || address.trim() == '' || phone.trim() == '') {
            window.alert("Tüm alanları eksiksiz doldurun");
            return;
        }

        const res = await CustomerService.createCustomer(customer);

        if (res.status === 200) {
            history.push('/customers')
        } else {
            window.alert("Kayıt eklemede sorun oluştu");
            return;
        }

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
                        value={surname} onChange={onChangeHandler}  />
                </div>
                <div className="form-group">
                    <label>Address :</label>
                    <input placeholder="Adress" name="address" className="form-control"
                        value={address} onChange={onChangeHandler}  />
                </div>
                <div className="form-group">
                    <label>Phone :</label>
                    <input placeholder="Phone" name="phone" className="form-control"
                        value={phone} onChange={onChangeHandler} type="number" />
                </div>
                <button className="btn btn-success" onClick={(e) => saveCustomer(e)}>Save</button>
                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push('/customers')} >Cancel</button>
            </form>
        )
    }



    return (
        <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add Customer</h3>
                        <div className="card-body">
                            {getForm()}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default CreateCustomer;