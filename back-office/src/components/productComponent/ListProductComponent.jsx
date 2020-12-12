import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import  {AppContext} from '../../ContextApi';
import ProductService from '../../services/ProductService';
import Loader from "react-loader-spinner";


class ListProductComponent extends Component {
    static contextType = AppContext;
    
    constructor(props) {
        super(props)
        this.state = {
            products: [],
            categories: [],
           
        }
        this.addProduct = this.addProduct.bind(this);
        this.editProduct = this.editProduct.bind(this);
        this.deleteProduct = this.deleteProduct.bind(this);
        
    }
    
    componentDidMount() {
        
        
       
        
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }

        ProductService.getProduct().then((res) => {

            this.setState({ products: res.data })

          
        });
        CategoryService.getCategory().then((res) => {
 
            this.setState({ categories: res.data })
            this.context=true
            console.log(this.context)
        });
        
    }
    getProducts(id) {
        this.setState({ products: this.state.products.filter(product => product.categoryId == id) });
    }
    editProduct(id, catId,imageId) {
        this.props.history.push({
            pathname: '/update-product',
            state: {
                id: id,
                catId: catId,
                imageId:imageId

            },
        });

    }
    addProduct() {
        this.props.history.push(`add-product`);

    }
    viewProduct(id) {

        this.props.history.push(`/view-product/${id}`);
    }
    deleteProduct(id) {
        ProductService.deleteProduct(id).then(res => {
            this.setState({ products: this.state.products.filter(products => products.id !== id) });
        });
    }
    render() {
        const loading = this.context;
        console.log(this.context);
        return (
  
                <div>
                    {!loading ? <div style={{width:'100%',height:'100',display:"flex",justifyContent:"center",alignItems:"center",marginTop:"15%"}}> 
                                  
                                  <Loader type="TailSpin" color="#000" height="50" width="100"/>                                       
                         </div> :
                    <div clasname="container">
                   
                        <h2 className="text-center">Product List</h2>
                        <div className="row">
                            <button className="btn btn-primary" onClick={this.addProduct}>Add Product</button>
                            <button style={{ marginLeft: "6px" }} className="btn btn-primary" onClick={() => window.location.reload()}>All Products</button>
                            <table className="table table-striped table-bordered">
                                <thead>
                                    <tr>

                                        <th>Category</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Price</th>
                                        <th>Image</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>

                                {
                                    this.state.products.map(
                                        products =>
                                            <tbody>
                                                {

                                                    <tr key={products.id}>
                                                        <td onClick={() => this.getProducts(products.categoryId)} ><a href="#">{products.categoryName}</a></td>
                                                        <td>{products.name}</td>
                                                        <td>{products.description}</td>
                                                        <td>{products.salesPrice}</td>
                                                        <td style={{textAlign:"center"}} ><img src={'data:image/png;base64,' + products.image.fileContent} style={{borderRadius:"10px"}} width="50" /></td>
                                                        <td>
                                                            <button  onClick={() => this.editProduct(products.id, products.categoryId,products.image.id)}
                                                                className="btn btn-info"> Update
                                                                </button>
                                                            <button style={{ marginLeft: "6px" }} onClick={() => this.deleteProduct(products.id)}
                                                                className="btn btn-danger"> Delete
                                                                </button>
                                                        </td>
                                                    </tr>

                                                }
                                            </tbody>
                                    )
                                }

                            </table>

                        </div>
                    </div>
    }
                </div>
      
        );
    }
}

export default ListProductComponent;
