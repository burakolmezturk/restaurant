import { useEffect, useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import CustomerService from '../../services/CustomerService';
import Loading from '../Loading';
import Pagination from "react-js-pagination";
const ListCustomer = () => {

    const history = useHistory();
    const [customers, setCustomers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [activePage, setActivePage] = useState(0);
    const [totalElements, setTotalElements] = useState();

    useEffect(() => {

        getCustomersByPage(0);

    }, []);


    const getCustomersByPage = async (activePage) => {

        const res = await CustomerService.getCustomersByPage(activePage);
        const customerData = res.data;

        if (!customerData) {
            return (
                <div> Veriler Çekilemedi. </div>
            )
        }

        setCustomers(customerData.content);
        setTotalElements(customerData.totalElements);
        setLoading(false);

    }

    const editCustomer = (customerId) => {
        history.push({
            pathname: '/update-customer',
            state: {
                id: customerId          
            }
        });
    }

    const deleteCustomer = async (customerId) => {

        setLoading(true);

        const res = await CustomerService.deleteCustomer(customerId);

        if (res.status === 200) {
            await getCustomersByPage(activePage);
        }

        setLoading(false);

    }

    if (loading == true) {
        return (
            <Loading />
        )
    }

    const getCustomersTable = () => {
        if (!customers || customers != []) {
            return (
                <div className="row">
                    <table className="table table-striped table-bordered" >
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Address</th>
                                <th>Phone</th>
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
                                                <td>
                                                    <button onClick={() => editCustomer(customer.id)}
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
        return (
            <div>Veri Yok</div>
        )
    }

    const onChangePage = async (e) => {

        setActivePage(e - 1);
        setLoading(true);
        await getCustomersByPage(e-1)
        setLoading(false);

    }

    const getPagenition = () => {
        if(customers.length>0){
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
    return (
        <div>
            <div className="container">
                <h2 className="text-center">Customer List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={() => history.push(`add-customer`)}>Add Customer</button>                  
                </div>
                {getCustomersTable()}
                {getPagenition()}
            </div>
            
        </div>

    )
}
export default ListCustomer;