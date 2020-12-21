import React, {Component} from 'react';
import LoginService from '../services/LoginService';

class LoginComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            username:'',
            password:'',
            checked:false
        }
        this.changeusernameHandler = this.changeusernameHandler.bind(this);
        this.changepasswordHandler = this.changepasswordHandler.bind(this);
        this.changeCheckedHandler=this.changeCheckedHandler.bind(this);
    }
    
    loginUser= (e) => {
        e.preventDefault();
        console.log(this.state.username + this.state.password);
        LoginService.getLogin(this.state.username,this.state.password).then((res)=>
        {
            
            if(res.status!='401'){
                localStorage.setItem("username",this.state.username);
                localStorage.setItem("password",this.state.password);
                if (this.state.checked === true) {
                    let remember = { username: this.state.username, password: this.state.password }
                    localStorage.setItem("remember", JSON.stringify(remember));
                }else{
                    localStorage.removeItem("remember");
                }
                this.props.history.push('/home');
            }else{
                console.log("Hatalı Giriş");
            }

        } 
        )

    }
    componentDidMount(){
        if(localStorage.getItem("remember") !== null){
                     
            const rememberMe=JSON.parse(localStorage.getItem("remember"));
            this.setState({username:rememberMe.username,password:rememberMe.password});}
    }
    loadCheckBox() {
      
        const checkbox = [];
        if(localStorage.getItem("remember") !== null){
                                    
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
            <div style={{backgroundColor:"#f9f9f9"}}>
                <div className="login-center">
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-center">Restaurant Automation Login</h3>
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
                                        {this.loadCheckBox()}
                                       <br/><button className="btn btn-success" onClick={this.loginUser}>Login</button>
                            
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