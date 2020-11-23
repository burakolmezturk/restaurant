import React, { Component } from 'react';

import ProductService from '../../services/ProductService';


class ListProductComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            products:[]
        }
        this.addProduct = this.addProduct.bind(this);
        this.editProduct=this.editProduct.bind(this);
        this.deleteProduct=this.deleteProduct.bind(this);

    }
componentDidMount(){
    
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
    
   ProductService.getProduct().then((res)=>{
    this.setState({products:res.data})   
});
}
editProduct(id){
    this.props.history.push(`/update-product/${id}`);

}
addProduct(){
    this.props.history.push(`add-product`);
   
}
viewProduct(id){
    
    this.props.history.push(`/view-product/${id}`);
}
deleteProduct(id){
ProductService.deleteProduct(id).then(res =>{
    this.setState({products:this.state.products.filter(products=>products.id!==id)});
});
}
    render() {
        return (
            <div>
                <div clasname="container">
                <h2 className="text-center">Product List</h2>
                   <div className="row">
                   <button   className="btn btn-primary" onClick={this.addProduct}>Add Product</button>
                       <table className="table table-striped table bordered">
                            <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Category</th>
                                    <th>Sales Price</th>
                                    <th>Purchase Price</th>
                                    <th>Actions</th>
                                </tr>

                            </thead>

                            <tbody>
                                {
                                    this.state.products.map(
                                       products =>
                                       <tr key ={products.id} >
                                           <td>{products.name}</td>
                                           <td>{products.description}</td>
                                           <td>{products.category}</td>
                                           <td>{products.salesPrice}</td>
                                           <td>{products.purchasePrice}</td>
                                           <td>

                                               <button style={{marginLeft:"10px"}} onClick={() => this.editProduct(products.id)} className="btn btn-info">Edit</button>
                                               <button style={{marginLeft:"10px"}} onClick={() => this.deleteProduct(products.id)} className="btn btn-danger" >Delete</button>
                                           
                                           </td>
                                       </tr>   
                                    )
                                }

                            </tbody>
                       </table>
                       
                   </div>
                   </div>
            </div>
        );
    }
}

export default ListProductComponent;