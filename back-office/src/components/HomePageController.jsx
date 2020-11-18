import React, { Component } from 'react';
import '../../src/App.css';


class HomePageController extends Component {



    render() {
        return (
    <div>    
        <div >
            <header >
                <nav className="navbar navbar-expand-xl navbar-dark bg-dark">
                    <div>
                        <a href="/home" className="navbar-brand">Restaurant Automation Operation Application</a>
                    </div>
                </nav>
            </header>
        </div>    
  <div className="social-box">
     
    <div className="container">
     	<div className="row">			 
			    <div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box">
                        <i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Product Management </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div>
                        <div class="box-btn">
						    <a href="/product">Go</a>
						</div>						
					 </div>
				</div>	 
                <div  className="col-lg-4 col-xs-12 text-center">
					<div  className="box">
                        <i className="fa fa-behance fa-3x" aria-hidden="true"></i>
						<div className="box-title">
							<h3 className="box-text1">Staff Management </h3>
						</div>
						<div className="box-text">
							<span></span>
						</div>
                        <div class="box-btn">
						    <a href="#">Go</a>
						</div>						
					 </div>
				</div>	 									
		
		</div>		
    </div>
</div>
</div>
        );
    }
}

export default HomePageController;