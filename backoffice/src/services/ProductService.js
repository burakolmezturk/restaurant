import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/product/";
class ProductService{

    getProduct(){
        return axios.get(PRODUCT_API_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getProductPage(pageNumber){
        return axios.get(PRODUCT_API_BASE_URL+"getPage",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },params:{
                page:pageNumber,
                size:10
            }

        });
    }
    createProduct(product,categoryId){
        return axios.post(PRODUCT_API_BASE_URL+"add",product,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },params: {
                categoryId: categoryId         
              }
        });
    }
    getProductById(productId){        
        return axios.get(PRODUCT_API_BASE_URL+productId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updateProduct(product,categoryId){
        return axios.put(PRODUCT_API_BASE_URL+"update",product,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            },
            params:{
                categoryId: categoryId         
              }
        });
    }
    deleteProduct(productId){
        return axios.delete(PRODUCT_API_BASE_URL+"delete/"+productId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new ProductService()