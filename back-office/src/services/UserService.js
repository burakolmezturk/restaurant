import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/user/function/";
class UserService{

    getUsers(){
        return axios.get(PRODUCT_API_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createUser(user,role){
        return axios.post(PRODUCT_API_BASE_URL+"add",user,{
            params: {
                role: role
              },
              auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getUserById(userId){        
        return axios.get(PRODUCT_API_BASE_URL+"list/"+userId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updateUser(user){
        return axios.put(PRODUCT_API_BASE_URL+"update",user,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    deleteUser(userId){
        return axios.delete(PRODUCT_API_BASE_URL+"delete/"+userId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new UserService()