import React, { Component } from 'react';
import UserService from '../../services/UserService';

class CreateUserComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            username:'',
            password:'',
            role:'',  
        }
        this.changeUserNameHandler=this.changeUserNameHandler.bind(this);
        this.changePasswordHandler=this.changePasswordHandler.bind(this);
        this.changeRoleHandler=this.changeRoleHandler.bind(this);

        }
        componentDidMount(){
            if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
                this.props.history.push('')
            }
        }
        saveUser = (e) => {
            e.preventDefault();
    
            let user={username:this.state.username,password:this.state.password};
    
                UserService.createUser(user,this.state.role).then(res =>{
                    this.props.history.push('/user');
    
                })
        }

        cancel(){
            this.props.history.push('/user')
        }
        changeUserNameHandler=(event) =>{
            this.setState({username:event.target.value});
        }
        changePasswordHandler=(event) =>{
            this.setState({password:event.target.value});
        }
        changeRoleHandler=(event) =>{
            
            this.setState({role:event.target.value});
            console.log(this.state.role);
        }

    render() {
        return (
            <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add User</h3>
                        <div className = "card-body">
                            <form>
                                
                                <div className="form-group">
                                    <label>Username :</label>
                                    <input placeholder="Username" name="name" className="form-control"
                                    value={this.state.username} onChange={this.changeUserNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Password :</label>
                                    <input placeholder="Password" name="password" className="form-control"
                                    value={this.state.password} onChange={this.changePasswordHandler}/>
                                </div>
                                
                                <div className="form-group">
                                        <label> Role :</label>
                                        <div className="form-check form-check-inline">
                                            <input className="form-check-input" type="checkbox" id="inlineCheckbox1"
                                                   value="ADMIN" onChange={this.changeRoleHandler}/>
                                                <label className="form-check-label" htmlFor="inlineCheckbox1">ADMIN</label>
                                        </div>
                                        <div className="form-check form-check-inline">
                                            <input className="form-check-input" type="checkbox" id="inlineCheckbox2"
                                                   value="USER" onChange={this.changeRoleHandler}/>
                                                <label className="form-check-label" htmlFor="inlineCheckbox2">USER</label>
                                        </div>
                                      

                                 </div>
                       

                                <button className="btn btn-success" onClick={this.saveUser}>Save</button>
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

export default CreateUserComponent;