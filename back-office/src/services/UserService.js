import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/user/";
class UserService{

    getUsers(){
        return axios.get(PRODUCT_API_BASE_URL+"list");
    }
    createUser(user){
        return axios.post(PRODUCT_API_BASE_URL+"add",user);
    }
    getUserById(userId){        
        return axios.get(PRODUCT_API_BASE_URL+"list/"+userId)
    }
    updateUser(user){
        return axios.put(PRODUCT_API_BASE_URL+"update",user)
    }
    deleteUser(userId){
        return axios.delete(PRODUCT_API_BASE_URL+"delete/"+userId)
    }

}
export default new UserService()