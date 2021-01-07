import axios from 'axios';

const ORDER_API_BASE_URL = "http://localhost:8082/order";
class OrderService{

    addOrder(order){
        return axios.post(ORDER_API_BASE_URL,order,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new OrderService()