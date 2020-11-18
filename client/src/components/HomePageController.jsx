import React, { Component } from 'react';
import '../../src/App.css';
import ProductService from '../services/ProductService';


class HomePageController extends Component {
	constructor(props){
        super(props)
        this.state={
            products:[]
        }       

    }

	componentDidMount(){
		ProductService.getProduct().then((res)=>{
		 this.setState({products:res.data})   
	 });
	 }

    render() {
        return (
			<div>    
        <div >
            <header >
                <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                    <div>
                        <a href="/home" className="navbar-brand">Restaurant Automation</a>
                    </div>
                </nav>
            </header>
        </div>    
			<div className="row">
			{
				this.state.products.map(
					products =>
			<div className="col-sm-4">
				<div class="shadow-lg p-0 mb-2 bg-white rounded">
			      <div className="card" >		
					<div className="card-body">					
				  		<h5 className="card-title" style={{textAlign:"center",fontWeight: 'bold',fontSize:"25px"}}>{products.name}</h5>
				  		<p className="card-text" style={{textAlign:"center"}}>{products.description}</p>
				  		<p className="card-text" style={{textAlign:"center"}}>{products.salesPrice} ₺</p>
				  						
					</div>	
					<a href="#" className="btn btn-secondary">Add To Cart</a>
			 	 </div>
			 	</div>
			</div>
				)
	       }
		  </div>
		  <div>
		  	<footer class="page-footer font-small special-color-dark pt-4">

             	<div class="footer-copyright text-center py-3">Restaurant Automation               	
            	</div>


			</footer>
		  </div>
		  </div>
        );
    }
}

export default HomePageController;