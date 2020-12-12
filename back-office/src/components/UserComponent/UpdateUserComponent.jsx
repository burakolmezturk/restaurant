import React, { Component } from 'react';
import UserService from '../../services/UserService';
import RoleService from '../../services/RoleService';
class UpdateUserComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: this.props.match.params.id,
            username: '',
            password: '',
            email:'',
            roleId: [],
            roles: []

        }

        this.changeUsernameHandler = this.changeUsernameHandler.bind(this);
        this.changePasswordHandler = this.changePasswordHandler.bind(this);
        this.changeEmailHandler = this.changeEmailHandler.bind(this);
        this.changeRoleHandler = this.changeRoleHandler.bind(this);
        this.updateUser = this.updateUser.bind(this);
    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        RoleService.getRoles().then((res)=>{
            this.setState({roles:res.data});
        })
        UserService.getUserById(this.state.id).then((res) => {
            let user = res.data;
            console.log(user)
            this.setState({
                id: user.id,
                username: user.userName,
                password: user.password,
                email:user.email,
                roleId: user.rolesId,

            });

        });
    }
    changeMultiCate(id) {
        if (this.state.roleId.includes(id) !== true) {
            this.state.roleId.push(id);
            
        } else {
            for (let i = 0; i < this.state.roleId.length; i++) {
                if (id === this.state.roleId[i]) {
                    this.state.roleId.splice(i, 1);
                    
                }
            }
        }
    }
    updateUser = (e) => {
        e.preventDefault();

        let user = { id: this.state.id, userName: this.state.username, password: this.state.password, rolesId: this.state.roleId,enabled:true,email:this.state.email };

        UserService.updateUser(user).then(res => {
            this.props.history.push('/user');

        })
    }
    changeIdHandler = (event) => {
        this.setState({ id: event.target.value });
    }
    changeUsernameHandler = (event) => {
        this.setState({ username: event.target.value });
    }
    changePasswordHandler = (event) => {
        this.setState({ password: event.target.value });
        
    }
    changeEmailHandler = (event) => {
        this.setState({ email: event.target.value });
    }
    changeRoleHandler = (event) => {
        this.setState({ role: event.target.value });
    }

    cancel() {
        this.props.history.push('/user')
    }
    checkBox() {

        const multiSelect = [];
        let info = false;
        this.state.roles.forEach(role => {
            this.state.roleId.forEach(id => {
                if (id === role.id) {
                    multiSelect.push(
                        <div className="row col-md -12">
                            <label><input type="checkbox" onChange={() => this.changeMultiCate(role.id)} defaultChecked="true" />{role.name}</label>
                        </div>)
                    info = true;
                }
            });


            if (info === false) {

                multiSelect.push(
                    <div className="row col-md -12">
                        <label><input type="checkbox" onChange={() => this.changeMultiCate(role.id)} />{role.name}</label>
                    </div>)
                info = true;

            }
            info = false;

        });
        return multiSelect;
    }


    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-canter">Edit User</h3>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label>Username :</label>
                                        <input placeholder="Username" name="name" className="form-control"
                                            value={this.state.username} onChange={this.changeUsernameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Password :</label>
                                        <input type="password" placeholder="Password" name="password" className="form-control"
                                            value={this.state.password} onChange={this.changePasswordHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Email :</label>
                                        <input type="email" placeholder="Email" name="email" className="form-control"
                                            value={this.state.email} onChange={this.changeEmailHandler} />
                                    </div>
                                    <div className="form-group">
                                        <div className="row col-md-12">
                                            <div className="checkbox" style={{ height: "10rem", width: "80rem", overflow: "auto" }}>
                                                {
                                                    this.checkBox()
                                                }
                                            </div>

                                        </div>
                                    </div>

                                    <button className="btn btn-success" onClick={this.updateUser}>Save</button>
                                    <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={this.cancel.bind(this)} >Cancel</button>
                                </form>
                            </div>

                        </div>

                    </div>


                </div>
            </div>
        );
    }
}

export default UpdateUserComponent;