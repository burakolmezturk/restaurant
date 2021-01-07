import axios from 'axios';

const INFO_API_BASE_URL = "http://localhost:8082/info/";
class InfoService{
    
    getInfo(){
        return axios.get(INFO_API_BASE_URL+"get",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getProfileInfo(){
        return axios.get(INFO_API_BASE_URL+"getProfile",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getBeanNames(){
        return axios.get(INFO_API_BASE_URL+"getBeanNames",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
   

}
export default new InfoService()