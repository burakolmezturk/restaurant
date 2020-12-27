import { useEffect, useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import CustomerService from '../../services/CustomerService';
import MediaService from '../../services/MediaService';
import Header from '../header/Header';
import {createSuccessNotification, createErrorNotification,createWarningNotification,createInfoNotification} from "../Natifications";
const CreateCustomer = () => {

    const history = useHistory();

    const [customer, setCustomer] = useState({
        name: '', surname: '', address: '', phone: '', image: ''
    });
    const [medias, setMedias] = useState([]);
    const { name, surname, address, phone, image } = customer;

    useEffect(() => {
        getMedias();
    }, []);

    const onChangeHandler = (e) => {
        setCustomer({ ...customer, [e.target.name]: e.target.value });
    }
    const onChangeImage = (e) => {
        setCustomer({ ...customer, image: medias[e.target.value] });
    }
    async function getMedias() {

        const res = await MediaService.getMedias();

        await setMedias(res.data);

        setCustomer({ ...customer, image: res.data[0] });

    }

    const showImage = () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }

    const saveCustomer = async (e) => {
        e.preventDefault();

        if (name.trim() == '' || surname.trim() == '' || address.trim() == '' || phone.trim() == '') {
            createErrorNotification('Tüm alanları eksiksiz doldurunuz.')
            return;
        }
        let res;
         await CustomerService.createCustomer(customer).then(response => {
            res = response;
        }).catch(({ response }) => {

            res = response;
        });

        if (res.status === 200) {
            createSuccessNotification('Adding record is successful.');
            history.push('/customers')
        } else {
            createErrorNotification('There was a problem adding the record.');
            return;
        }

    }

    const getListBox = () => {
        return (
            <div className="form-group">
                <label>Category Image :</label>
                <select
                    className="form-control" id="option" onChange={onChangeImage} >
                    {
                        medias.map(
                            (media, index) =>

                                <option key={media.id} value={index}>{media.fileName}</option>
                        )
                    }
                </select>
                {showImage()}

            </div>)
    }

    const getHeader = () => {
        return (
            <Header />
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
                {getListBox()}
                <button className="btn btn-success" onClick={(e) => saveCustomer(e)}>Save</button>
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