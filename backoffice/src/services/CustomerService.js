import axios from 'axios';

const CUSTOMER_API_BASE_URL = "http://localhost:8080/customer/";
class CustomerService{

    getCustomersByPage(pageNumber,language){
      return axios.get(CUSTOMER_API_BASE_URL,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },params:{
                page:pageNumber,
                size:10
            },
            headers:{
                "Accept-Language" :language
            }
        })
        
    }
    createCustomer(customer,language){
        return axios.post(CUSTOMER_API_BASE_URL,customer,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },
            headers:{
                "Accept-Language" :language
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