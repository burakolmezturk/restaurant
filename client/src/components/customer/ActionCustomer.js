import { useEffect, useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import CustomerService from '../../services/CustomerService';
import Loading from '../Loading';
import Pagination from "react-js-pagination";

const ActionCustomer =() =>{
    const history = useHistory();
    const [customers, setCustomers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [activePage, setActivePage] = useState(0);
    const [totalElements, setTotalElements] = useState();
    const [name,setName] = useState('');
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

    const selectCustomer = (customerId) => {
        console.log(customerId);
        history.push({
            pathname: '/cart',
            state: {
                customerId: customerId          
            }
        });
    }
    const searchWithName = async (e) =>{
      e.preventDefault();

    const res =  await CustomerService.getCustomersPageByName(activePage,name);

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
    const changeSearch= (e) =>{
        setName(e.target.value)
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
                                                    <button onClick={() => selectCustomer(customer.id)}
                                                        className="btn btn-info"> Select
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
                    <button  className="btn btn-primary" onClick={() => history.push(`add-customer`)}>Add Customer</button>
                    <button style={{ marginTop: "15px", marginBottom: "10px" ,marginLeft:"4rem" ,width:"5rem"}}  className="btn btn-danger" onClick={() => history.push(`home`)}>Back</button>
                    <button style={{ marginTop: "15px", marginBottom: "10px" ,marginLeft:"3rem" ,width:"5rem"}} className="btn btn-success"
                            onClick={(e) => searchWithName(e)}>
                        Search
                    </button>
                    <input placeholder="Search" name="searchName" className="form-control" style={{height:"2.6rem",width:"10rem",marginLeft:"1rem",marginTop:"13px"}}
                           value={name} onChange={(e)=>changeSearch(e)}/>
                </div>
                
                {getCustomersTable()}
                {getPagenition()}
            </div>
            
        </div>

    )
}
export default  ActionCustomer;