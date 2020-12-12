import React, { Component } from 'react';
import UserService from '../../services/UserService';
import RoleService from '../../services/RoleService';
class CreateUserComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            userName:'',
            password:'',
            roleId:[],
            roles:[]
        }
        this.changeUserNameHandler=this.changeUserNameHandler.bind(this);
        this.changePasswordHandler=this.changePasswordHandler.bind(this);
        
        }
        componentDidMount(){
            if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
                this.props.history.push('')
            }
            RoleService.getRoles().then((res)=>{
            this.setState({roles:res.data})
            console.log(res.data);
            }
            
           );
        }
        saveUser = (e) => {
            e.preventDefault();
    
            let user={userName:this.state.userName,password:this.state.password,rolesId:this.state.roleId};
    
                UserService.createUser(user).then(res =>{
                    this.props.history.push('/user');
    
                })
        }

        cancel(){
            this.props.history.push('/user')
        }
        changeUserNameHandler=(event) =>{
            this.setState({userName:event.target.value});
        }
        changePasswordHandler=(event) =>{
            this.setState({password:event.target.value});
        }
        changeMultiCate(id) {
            if (this.state.roleId.includes(id) !== true) {
                this.state.roleId.push(id);
                console.log(this.state.roleId)
            } else {
                for (let i = 0; i < this.state.roleId.length; i++) {
                    if (id === this.state.roleId[i]) {
                        this.state.roleId.splice(i, 1);
                        console.log(this.state.roleId)
                    }
                }
            }
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
                                        <div className="row col-md-12">
                                            <div className="checkbox" style={{ height: "10rem", width: "80rem", overflow: "auto" }}>
                                                {
                                                    this.state.roles.map(
                                                        role =>
                                                            <div className="row col-md -12">
                                                                <label><input type="checkbox" value="" onClick={() => this.changeMultiCate(role.id)} />{role.name}</label>
                                                            </div>
                                                    )
                                                }
                                            </div>

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