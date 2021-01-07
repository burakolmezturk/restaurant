import axios from 'axios';

const CATEGORY_API_BASE_URL = "http://localhost:8082/category/";
class CategoryService{

    getCategory(){
        return axios.get(CATEGORY_API_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createCategory(category){
        return axios.post(CATEGORY_API_BASE_URL+"add",category,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getCategoryById(categoryId){        
        return axios.get(CATEGORY_API_BASE_URL+categoryId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updateCategory(category){
        console.log(category);
        return axios.put(CATEGORY_API_BASE_URL+"edit",category,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    deleteCategory(categoryId){
        return axios.delete(CATEGORY_API_BASE_URL+"delete/"+categoryId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new CategoryService()