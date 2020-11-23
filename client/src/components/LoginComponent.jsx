import React, {Component} from 'react';
import LoginService from '../services/LoginService';

class LoginComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            username:'',
            password:''
        }
        this.changeusernameHandler = this.changeusernameHandler.bind(this);
        this.changepasswordHandler = this.changepasswordHandler.bind(this);
    }
    loginUser= (e) => {
        e.preventDefault();
        console.log(this.state.username + this.state.password);
        LoginService.getLogin(this.state.username,'{noop}'+this.state.password).then((res)=>
        {
            
            if(res.data!=''){
                localStorage.setItem("username",this.state.username);
                localStorage.setItem("password",this.state.password);
                this.props.history.push('/cart')
            }else{
                console.log("Hatalı Giriş");
            }

        } 
        )

    }
    changeusernameHandler=(event) =>{
        this.setState({username: event.target.value})
    }
    changepasswordHandler=(event) =>{
        this.setState({password: event.target.value})
    }
    cancel(){
        this.props.history.push('/listuser');
    }
    render()
{
        return (
            <div>
                <div>
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Login</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Username </label>
                                            <input placeholder="username" name="username" className="form-control"
                                                   value={this.state.username} onChange={this.changeusernameHandler}/>
                                        </div>
                                        <div className="form-group">
                                            <label> Password </label>
                                            <input type="password" placeholder="password" name="password" className="form-control"
                                                   value={this.state.password} onChange={this.changepasswordHandler}/>
                                        </div>
                                        <button className="btn btn-success" onClick={this.loginUser}>Login</button>
                            
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default LoginComponent;