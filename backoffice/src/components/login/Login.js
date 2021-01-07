import { useContext, useEffect, useState } from 'react';
import { Context } from '../../contextApi/ContextApi';
import { useHistory } from "react-router-dom";
import LoginService from "../../services/LoginService"

const Login = () => {
    const history = useHistory();
    const { login } = useContext(Context);

    const [user, setUser] = useState({

        username: '', password: ''
    })
    const [checked, setChecked] = useState(false);
    const { username, password } = user;

    const onChange = (e) => {

        setUser({ ...user, [e.target.name]: e.target.value });
    }
    const loginApi =  async (e) => { 
        e.preventDefault();
        console.log('girdi1');
       const res = await login(username, password);
       
       console.log(res)
       
       if(res.status=='200'){
           
            if (checked === true) {
                let remember = { username: username, password: password }
                localStorage.setItem("rememberMe", JSON.stringify(remember));
            } else {
                localStorage.removeItem("rememberMe");
            }
            history.push(`/product`)
        }


        e.preventDefault();
    }
    const changeCheckedHandler = (event) => {
        setChecked(event.target.checked);
    }
    useEffect(() => {
        if (localStorage.getItem("rememberMe") !== null) {

            const rememberMe = JSON.parse(localStorage.getItem("rememberMe"));
            setUser({ ...user, username: rememberMe.username, password: rememberMe.password });
        }
    }, [])
    const loadCheckBox = () => {

        const checkbox = [];
        if (localStorage.getItem("rememberMe") !== null) {

            checkbox.push(
                <label><input type="checkbox" defaultChecked="true" onChange={changeCheckedHandler} />Remember Me</label>
            );



        } else {

            checkbox.push(
                <label><input type="checkbox" onChange={changeCheckedHandler} />Remember Me</label>
            );
        }
        return checkbox;

    }

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
                                            value={username} onChange={(e) => onChange(e)} />
                                    </div>
                                    <div className="form-group">
                                        <label> Password </label>
                                        <input type="password" placeholder="password" name="password" className="form-control"
                                            value={password} onChange={(e) => onChange(e)} />
                                    </div>
                                    {loadCheckBox()}
                                    <br /> <button className="btn btn-success" onClick={loginApi}>Login</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default Login;