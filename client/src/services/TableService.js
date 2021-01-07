import axios from 'axios';

const TABLE_API_BASE_URL = "http://localhost:8082/table/";
class TableService{

    getTable(){
        return axios.get(TABLE_API_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createTable(table,placeId){
        return axios.post(TABLE_API_BASE_URL+"add",table,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },params: {
                placeId: placeId         
              }
        });
    }
    getTableById(tableId){        
        return axios.get(TABLE_API_BASE_URL+tableId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updateTable(table){
        return axios.put(TABLE_API_BASE_URL+"update",table,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    deleteTable(tableId){
        return axios.delete(TABLE_API_BASE_URL+"delete/"+tableId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new TableService()