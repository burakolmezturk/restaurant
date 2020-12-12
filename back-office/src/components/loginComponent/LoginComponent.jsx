import React, { Component } from 'react';
import LoginService from '../../services/LoginService';

class LoginComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            username: '',
            password: '',
            checked: false
        }
        this.changeusernameHandler = this.changeusernameHandler.bind(this);
        this.changepasswordHandler = this.changepasswordHandler.bind(this);
    }
    componentDidMount(){
        if(localStorage.getItem("rememberMe") !== null){
                     
            const rememberMe=JSON.parse(localStorage.getItem("rememberMe"));
            this.setState({username:rememberMe.username,password:rememberMe.password});}
    }
    loginUser = (e) => {
        e.preventDefault();

        LoginService.getLogin(this.state.username,this.state.password).then((res) => {

            if (res.status == '200') {
                localStorage.setItem("username", this.state.username);
                localStorage.setItem("password", this.state.password);

                if (this.state.checked === true) {
                    let remember = { username: this.state.username, password: this.state.password }
                    localStorage.setItem("rememberMe", JSON.stringify(remember));
                }else{
                    localStorage.removeItem("rememberMe");
                }

                this.props.history.push('/home')
            } else {
                console.log("Hatalı Giriş");
            }


        }
        )

    }

    loadCheckBox() {
      
        const checkbox = [];
        if(localStorage.getItem("rememberMe") !== null){
                                    
                checkbox.push(
                <label><input type="checkbox" defaultChecked="true" onChange={this.changeCheckedHandler}  />Remember Me</label>
                );
               
         
           
        }else{
            
            checkbox.push(
                <label><input type="checkbox" onChange={this.changeCheckedHandler} />Remember Me</label>
                );
        }
        return checkbox;

    }
    changeCheckedHandler = (event) => {
        this.setState({ checked: event.target.checked })
    }
    changeusernameHandler = (event) => {
        this.setState({ username: event.target.value })
    }
    changepasswordHandler = (event) => {
        this.setState({ password: event.target.value })
    }
    cancel() {
        this.props.history.push('/listuser');
    }
    render() {
        return (
            <div >
                <div className="login-center">
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Login</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label> Username </label>
                                            <input placeholder="username" name="username" className="form-control"
                                                value={this.state.username} onChange={this.changeusernameHandler} />
                                        </div>
                                        <div className="form-group">
                                            <label> Password </label>
                                            <input type="password" placeholder="password" name="password" className="form-control"
                                                value={this.state.password} onChange={this.changepasswordHandler} />
                                        </div>
                                        {this.loadCheckBox()}
                                        <br /> <button className="btn btn-success" onClick={this.loginUser}>Login</button>

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