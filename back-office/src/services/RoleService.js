import axios from 'axios';

const ROLE_API_BASE_URL = "http://localhost:8080/role/";
class RoleService{

    getRoles(){
        return axios.get(ROLE_API_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createRole(role){
        return axios.post(ROLE_API_BASE_URL+"add",role,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getRoleById(roleId){        
        return axios.get(ROLE_API_BASE_URL+roleId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updateRole(role){
        return axios.put(ROLE_API_BASE_URL+"edit",role,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    deleteRole(role){
        console.log(role)
        return axios.delete(ROLE_API_BASE_URL+"delete",{
            
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },
            data:role
        });
    }

}
export default new RoleService()