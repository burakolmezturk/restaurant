import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import ProductService from '../../services/ProductService';

class UpdateProductComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            id:this.props.history.location.state?.id,
            name:'',
            description:'',
            categoryId:this.props.history.location.state?.catId,
            salesPrice:0,
            purchasePrice:0,
            categories:[]
           
        }
               
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changeDescriptionHandler=this.changeDescriptionHandler.bind(this);
        this.changeCategoryHandler=this.changeCategoryHandler.bind(this);
        this.changeSalesPriceHandler=this.changeSalesPriceHandler.bind(this);
        this.changePurchasePriceHandler=this.changePurchasePriceHandler.bind(this);
        this.updateProduct=this.updateProduct.bind(this);
    }
    componentDidMount(){
        
        ProductService.getProductById(this.state.id).then((res) =>{
           
            let product = res.data;
            this.setState({id:product.id,
                name:product.name,                
                description:product.description,
                salesPrice:product.salesPrice,
                purchasePrice:product.purchasePrice
            
            });
        });
        
        CategoryService.getCategory().then((res)=>{
            this.setState({categories:res.data})
            
        }
        )
    }
    updateProduct = (e) => {
        e.preventDefault();
    
        let product={id:this.state.id,name:this.state.name,description:this.state.description,
            salesPrice:this.state.salesPrice,purchasePrice:this.state.purchasePrice};
    
            ProductService.updateProduct(product,document.getElementById('option').value).then(res =>{
                this.props.history.push('/product');
    
            })
    }
    changeIdHandler=(event) =>{
        this.setState({id:event.target.value});
    }
    changeNameHandler=(event) =>{
        this.setState({name:event.target.value});
    }
    changeDescriptionHandler=(event) =>{
        this.setState({description:event.target.value});
    }
    changeCategoryHandler=(event) =>{
        this.setState({category:event.target.value});
    }
    changeSalesPriceHandler=(event) =>{
        this.setState({salesPrice:event.target.value});
    } 
    changePurchasePriceHandler=(event) =>{
        this.setState({purchasePrice:event.target.value});
    }      
    
    cancel(){
        this.props.history.push('/product')
    }

    render() {
        return (
           <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Product</h3>
                        <div className = "card-body">
                            <form>

                                <div className="form-group">
                                    <label>Product Name :</label>
                                    <input placeholder="Product Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Product Description :</label>
                                    <input placeholder="Product Description" name="name" className="form-control"
                                    value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                </div>
                                <div className="form-group">
                                <select   
                                                className="form-control" id="option">
                                            {
                                                this.state.categories.map(
                                                    category =>
                                                        
                                                        <option  key={category.id} selected={this.state.categoryId==category.id} value ={category.id}>{category.name}</option>
                                                )
                                            }
                                        </select>
                                </div>
                                <div className="form-group">
                                    <label>Sales Price :</label>
                                    <input placeholder="Sales Price" name="sales" className="form-control"
                                    value={this.state.salesPrice} onChange={this.changeSalesPriceHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Purchase Price :</label>
                                    <input placeholder="Purchase Price" name="purchase" className="form-control"
                                    value={this.state.purchasePrice} onChange={this.changePurchasePriceHandler}/>
                                </div>

                                <button className="btn btn-success" onClick={this.updateProduct}>Save</button>
                                <button style={{marginLeft:"10px"}}  className="btn btn-danger" onClick={this.cancel.bind(this)} >Cancel</button>
                            </form>
                        </div>

                    </div>

                </div>


            </div>
        </div>
        );
    }
}

export default UpdateProductComponent;