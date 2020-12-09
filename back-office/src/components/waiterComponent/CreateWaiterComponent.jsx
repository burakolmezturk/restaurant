import React, { Component } from 'react';
import WaiterService from '../../services/WaiterService';


class CreateWaiterComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            name:'',
            email:'',
            phone:'',
            age:0 
        }
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changeEmailHandler=this.changeEmailHandler.bind(this);
        this.changeEmailHandler=this.changeEmailHandler.bind(this);
        this.changeEmailHandler=this.changeEmailHandler.bind(this);

        }
        componentDidMount(){
            if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
                this.props.history.push('')
            }
        }
        saveWaiter = (e) => {
            e.preventDefault();
    
            let waiter={name:this.state.name,email:this.state.email,phone:this.state.phone,age:this.state.age};
    
                WaiterService.createWaiter(waiter).then(res =>{
                    this.props.history.push('/waiter');
    
                })
        }

        cancel(){
            this.props.history.push('/waiter')
        }
        changeNameHandler=(event) =>{
            this.setState({name:event.target.value});
        }
        changeEmailHandler=(event) =>{
            this.setState({email:event.target.value});
        }
        changeAgeHandler=(event) =>{
            this.setState({age:event.target.value});
        }
        changePhoneHandler=(event) =>{
            this.setState({phone:event.target.value});
        }
     

    render() {
        return (
            <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add Waiter</h3>
                        <div className = "card-body">
                            <form>
                                
                                <div className="form-group">
                                    <label>Waiter Name :</label>
                                    <input placeholder="Waiter Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Waiter Age :</label>
                                    <input placeholder="Waiter Age" name="name" className="form-control"
                                    value={this.state.age} onChange={this.changeAgeHandler} type="number"/>
                                </div>
                                <div className="form-group">
                                    <label>Waiter Email :</label>
                                    <input placeholder="Waiter Email" name="name" className="form-control"
                                    value={this.state.email} onChange={this.changeEmailHandler} type="email"/>
                                </div>
                                <div className="form-group">
                                    <label>Waiter Phone :</label>
                                    <input placeholder="Waiter Phone" name="name" className="form-control"
                                    value={this.state.phone} onChange={this.changePhoneHandler} type="number"/>
                                </div>
                               
                       

                                <button className="btn btn-success" onClick={this.saveWaiter}>Save</button>
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

export default CreateWaiterComponent;