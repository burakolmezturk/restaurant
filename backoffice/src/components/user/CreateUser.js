import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import RoleService from '../../services/RoleService';
import UserService from '../../services/UserService';
import Header from '../header/Header';

const CreateUser = () => {

    const history = useHistory();

    const [user, setUser] = useState({
        userName: '',
        password: '',
        email:''
    });

    const { userName, password , email } = user
    const [roleId] = useState([]);
    const [roles, setRoles] = useState([]);
    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }

        getRoles();
    }, []);
    async function getRoles() {
        const res = await RoleService.getRoles();
        setRoles(res.data);
    }
    const saveUser = async (e) => {
        e.preventDefault();

        let user = { userName: userName, password: password, rolesId: roleId ,enabled:true , email:email};

        await UserService.createUser(user);
        history.push('/user');
    }
    const onChangeHandler = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    }

    const changeMultiCate = (id) => {
        if (roleId.includes(id) !== true) {
            roleId.push(id);
        } else {
            for (let i = 0; i < roleId.length; i++) {
                if (id === roleId[i]) {
                    roleId.splice(i, 1);
                }
            }
        }
    }
    return (
        <div>
            <Header />
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add User</h3>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label>Username :</label>
                                    <input placeholder="Username" name="userName" className="form-control"
                                        value={userName} onChange={onChangeHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Password :</label>
                                    <input placeholder="Password" name="password" className="form-control"
                                        value={password} onChange={onChangeHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Email :</label>
                                    <input type="email" placeholder="Email" name="email" className="form-control"
                                        value={email} onChange={onChangeHandler} />
                                </div>
                                <div className="form-group">
                                    <div className="row col-md-12">
                                        <div className="checkbox" style={{ height: "10rem", width: "80rem", overflow: "auto" }}>
                                            {
                                                roles.map(
                                                    role =>
                                                        <div className="row col-md -12">
                                                            <label><input type="checkbox" value="" onClick={() => changeMultiCate(role.id)} />{role.name}</label>
                                                        </div>
                                                )
                                            }
                                        </div>
                                    </div>
                                </div>
                                <button className="btn btn-success" onClick={(e) => saveUser(e)}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push('/user')} >Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>)
}
export default CreateUser;