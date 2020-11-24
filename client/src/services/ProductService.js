import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/product";
class ProductService{

    postCart(cart){
        return axios.post(PRODUCT_API_BASE_URL+"/cart",cart,{
        auth:{
            username:localStorage.getItem("username"),
            password:localStorage.getItem("password")
        }
    })
    }
    getCategories(){
        return axios.get(PRODUCT_API_BASE_URL+"/list/category",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
    getProductsByCategory(id){
        return axios.get(PRODUCT_API_BASE_URL+"/products",{
            params: {
              categoryId: id
            },
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
    getProduct(){
        return axios.get(PRODUCT_API_BASE_URL+"/list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createProduct(product){
        return axios.post(PRODUCT_API_BASE_URL+"/add",product,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getProductById(productId){        
        return axios.get(PRODUCT_API_BASE_URL+productId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
    updateProduct(product){
        return axios.put(PRODUCT_API_BASE_URL+"/update",product,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }
    deleteProduct(productId){
        return axios.delete(PRODUCT_API_BASE_URL+"/delete/"+productId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        })
    }

}
export default new ProductService()