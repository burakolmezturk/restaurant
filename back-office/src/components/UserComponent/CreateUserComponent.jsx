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
        saveUser = (e) => {
            e.preventDefault();
    
            let user={username:this.state.username,password:this.state.password,role:this.state.role,
              };
    
                UserService.createUser(user).then(res =>{
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
                                    <label>Role :</label>
                                    <input placeholder="Role" name="category" className="form-control"
                                    value={this.state.role} onChange={this.changeRoleHandler}/>
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