import axios from 'axios';

const WAITER_API_BASE_URL = "http://localhost:8080/waiter/";
class WaiterService{

    getWaiters(){
        return axios.get(WAITER_API_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createWaiter(waiter){
        return axios.post(WAITER_API_BASE_URL+"add",waiter,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getWaiterById(waiterId){        
        return axios.get(WAITER_API_BASE_URL+waiterId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updateWaiter(waiter){
        return axios.put(WAITER_API_BASE_URL+"edit",waiter,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    deleteWaiter(waiterId){
        return axios.delete(WAITER_API_BASE_URL+"delete/"+waiterId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new WaiterService()