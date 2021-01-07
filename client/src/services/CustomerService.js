import axios from 'axios';

const CUSTOMER_API_BASE_URL = "http://localhost:8082/customer/";
class CustomerService{

    getCustomersByPage(pageNumber){
        return axios.get(CUSTOMER_API_BASE_URL,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },params:{
                page:pageNumber,
                size:10
            }
        });
    }
    getCustomersPageByName(pageNumber,name){
        return axios.get(CUSTOMER_API_BASE_URL+"search",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },params:{
                page:pageNumber,
                size:10,
                customerName:name
            }
        });
    }
    createCustomer(customer){
        return axios.post(CUSTOMER_API_BASE_URL,customer,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getCustomerById(customerId){        
        return axios.get(CUSTOMER_API_BASE_URL+customerId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updateCustomer(customer){
        return axios.put(CUSTOMER_API_BASE_URL,customer,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    deleteCustomer(customerId){
        return axios.delete(CUSTOMER_API_BASE_URL+customerId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new CustomerService()