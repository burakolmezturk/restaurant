import React, { Component } from 'react';
import '../../src/App.css';


class HomePageComponent extends Component {

	logOut= (e) => {
        e.preventDefault();
        localStorage.clear();
        this.props.history.push('')
    }


    render() {
        return (
    
  <div className="social-box">
     
    <div className="container">
		
     	<div className="row">			 
			    <div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}  >
                        
                        <div className="box-btn">
						    <a href="/action-customer"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Cart </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}>
                        
                        <div className="box-btn">
						    <a href="/place"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Tables </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	  
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}>
                        
                        <div className="box-btn">
						    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Reports </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 	 
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}>
                        
                        <div className="box-btn">
						    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Products </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}>
                        
                        <div className="box-btn">
						    <a href="/product"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Users </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}>
                        
                        <div className="box-btn">
						    <a href="/customers"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Customer </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}>
                        
                        <div className="box-btn">
						    <a href="#"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Empty </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box"style={{backgroundColor:"#5ac18e"}}>
                        
                        <div className="box-btn">
						    <a href="#"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Empty </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 
				<div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box" style={{backgroundColor:"#ff6666"}}>
                        
                        <div className="box-btn">
						    <a onClick={this.logOut}><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Logout </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div></a>
						</div>						
					 </div>
				</div>	 	 									
		
		</div>		
    </div>
</div>

        );
    }
}

export default HomePageComponent;