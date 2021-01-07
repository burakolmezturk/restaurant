import axios from 'axios';

const LOGIN_API_BASE_URL = "http://localhost:8082/user/";
class LoginService{

    getLogin(username,password){
        return axios.get(LOGIN_API_BASE_URL+"login",{
            auth: {
              username: username,
              password: password
            }
            
        })
    }
   

}
export default new LoginService()