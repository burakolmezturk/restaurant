import React, { Component } from 'react';
import { ThemeProvider } from 'react-bootstrap';
import CategoryService from '../../services/CategoryService';
import ProductService from '../../services/ProductService';

class CreateProductComponent extends Component {
    constructor(props){
    super(props)
    this.state = {
        name:'',
        description:'',
        categoryId:0,
        salesPrice:0,
        purchasePrice:0,
        categories:[]     
    }
    
    this.changeNameHandler=this.changeNameHandler.bind(this);
    this.changeDescriptionHandler=this.changeDescriptionHandler.bind(this);
    this.changeCategoryHandler=this.changeCategoryHandler.bind(this);
    this.changeSalesHandler=this.changeSalesHandler.bind(this);
    this.changePurchaseHandler=this.changePurchaseHandler.bind(this);
  
    this.saveProduct=this.saveProduct.bind(this);
    }
    componentDidMount(){
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        CategoryService.getCategory().then((res)=>{
            this.setState({categories:res.data})
            
        }
        )
      
    }

    saveProduct = (e) => {
        e.preventDefault();
        
       
        
        let product={name:this.state.name,description:this.state.description,categoryId:document.getElementById('option').value,
            salesPrice:this.state.salesPrice,purchasePrice:this.state.purchasePrice};
            

            ProductService.createProduct(product,document.getElementById('option').value).then(res =>{
                this.props.history.push('/product');

            })
    }
    changeDescriptionHandler=(event) =>{
        this.setState({description:event.target.value});
    }
    changeNameHandler=(event) =>{
        this.setState({name:event.target.value});
    }
    changeCategoryHandler=(event) =>{
        this.setState({category:event.target.value});
    }
    changeSalesHandler=(event) =>{
        this.setState({salesPrice:event.target.value});
    } 
    changePurchaseHandler=(event) =>{
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
                        <h3 className="text-canter">Add Product</h3>
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
                                                        
                                                        <option key={category.id} value ={category.id}>{category.name}</option>
                                                )
                                            }
                                        </select>
                                </div>
                                <div className="form-group">
                                    <label>Sales Price :</label>
                                    <input placeholder="Sales Price" name="salesprice" className="form-control"
                                    value={this.state.salesPrice} onChange={this.changeSalesHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Purchase Price :</label>
                                    <input placeholder="Purchase Price" name="purchaseprice" className="form-control"
                                    value={this.state.purchasePrice} onChange={this.changePurchaseHandler}/>
                                </div>

                                <button className="btn btn-success" onClick={this.saveProduct}>Save</button>
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

export default CreateProductComponent;