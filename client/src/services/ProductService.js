import axios from 'axios';

const PRODUCT_API_BASE_URL = "http://localhost:8080/product/";
class ProductService{

    postCart(cart){
        return axios.post(PRODUCT_API_BASE_URL+"/cart",cart)
    }
    getCategories(){
        return axios.get(PRODUCT_API_BASE_URL+"/list/category")
    }
    getProductsByCategory(categoryName){
        return axios.get(PRODUCT_API_BASE_URL+"products",{
            params: {
              categoryName: categoryName
            }
        })
    }
    getProduct(){
        return axios.get(PRODUCT_API_BASE_URL+"list");
    }
    createProduct(product){
        return axios.post(PRODUCT_API_BASE_URL+"add",product);
    }
    getProductById(productId){        
        return axios.get(PRODUCT_API_BASE_URL+productId)
    }
    updateProduct(product){
        return axios.put(PRODUCT_API_BASE_URL+"update",product)
    }
    deleteProduct(productId){
        return axios.delete(PRODUCT_API_BASE_URL+"delete/"+productId)
    }

}
export default new ProductService()