import { useEffect, useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import CustomerService from '../../services/CustomerService';
import Header from '../header/Header';
import Loading from '../loading/Loading';
import Pagination from "react-js-pagination";
import { Context } from '../../contextApi/ContextApi';
import { createSuccessNotification, createErrorNotification, createWarningNotification, createInfoNotification } from "../Natifications";
import 'react-notifications/lib/notifications.css';
import { CSVLink } from "react-csv";


const ListCustomer = () => {

    const headers = [
        { label: "Name", key: "name" },
        { label: "Surname", key: "surname" },
        { label: "Address", key: "address" },
        { label: "Phone", key: "phone" }
    ];


    const { user, language } = useContext(Context);
    const history = useHistory();
    const [customers, setCustomers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [activePage, setActivePage] = useState(0);
    const [totalElements, setTotalElements] = useState();

    useEffect(() => {
        if (user.username == '' && user.password == '') {
            history.push('');
        }
        getCustomersByPage(0);

    }, []);

    const getCustomersByPage = async (activePage) => {
        let res;
        console.log(language);
        await CustomerService.getCustomersByPage(activePage, language)
            .then(response => {
                setCustomers(response.data.content);
                setTotalElements(response.data.totalElements);
                setLoading(false);
            }).catch(({ response }) => {
                setLoading(false);
                createInfoNotification(response.data.message);
            });

    }


    const editCustomer = (customerId, imageId) => {
        history.push({
            pathname: '/update-customer',
            state: {
                id: customerId,
                imageId: imageId
            }
        });

    }

    const deleteCustomer = async (customerId) => {

        setLoading(true);
        let res;
        await CustomerService.deleteCustomer(customerId)
            .then(response => {
                res = response;
            }).catch(({ response }) => {

                res = response;
            });


        if (res.status === 200) {
            createSuccessNotification('Deleting record is successful.');
            await getCustomersByPage(activePage);
        }

        setLoading(false);

    }

    const getHeader = () => {
        return (
            <Header />
        )
    }


    const getCustomersTable = () => {

        if (customers != undefined) {
            return (
                <div className="row">
                    <table className="table table-striped table-bordered" >
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Address</th>
                                <th>Phone</th>
                                <th>Image</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        {
                            customers.map(
                                customer =>
                                    <tbody>
                                        {
                                            <tr key={customer.id}>
                                                <td>{customer.name}</td>
                                                <td>{customer.surname}</td>
                                                <td>{customer.address}</td>
                                                <td>{customer.phone}</td>
                                                <td><img src={'data:image/png;base64,' + customer.image.fileContent} style={{ borderRadius: "10px" }} width="50" /></td>
                                                <td>
                                                    <button onClick={() => editCustomer(customer.id, customer.image.id)}
                                                        className="btn btn-info"> Update
                                                        </button>
                                                    <button style={{ marginLeft: "6px" }} onClick={() => deleteCustomer(customer.id)}
                                                        className="btn btn-danger"> Delete
                                                        </button>
                                                </td>
                                            </tr>
                                        }
                                    </tbody>
                            )
                        }
                    </table>
                </div>
            )
        }

    }

    const onChangePage = async (e) => {

        setActivePage(e - 1);
        setLoading(true);
        await getCustomersByPage(e - 1)
        setLoading(false);

    }

    const getPagenition = () => {
        if (customers != undefined) {
            return (
                <div className="row" >
                    <Pagination
                        itemClass="page-item"
                        linkClass="page-link"
                        activePage={activePage + 1}
                        itemsCountPerPage={10}
                        totalItemsCount={totalElements}
                        pageRangeDisplayed={10}
                        onChange={onChangePage} />
                </div>
            )
        }

    }

    if (loading == true) {
        return (
            <Loading />
        )
    }
    return (
        <div>
            {getHeader()}
            <div className="container">
                <h2 className="text-center">Customer List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={() => history.push(`add-customer`)}>Add Customer</button>
                    <CSVLink className="btn btn-primary" style={{marginLeft:"10px"}} data={customers} headers={headers}>
                        Download Excel
                      </CSVLink>
                </div>
                {getCustomersTable()}
                {getPagenition()}
            </div>
        </div>

    )
}
export default ListCustomer;