import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import RoleService from '../../services/RoleService';
import UserService from '../../services/UserService';
import Header from '../header/Header';
import Loading from '../loading/Loading';

const EditUser = () => {
    const history = useHistory();
    const [user, setUser] = useState({
        id: history.location.state?.id,
        userName: '',
        password: '',
        email: ''
    });
    const [roleId, setRoleId] = useState([]);
    const [loading, setLoading] = useState(true);
    const [roles, setRoles] = useState([]);

    const { id, userName, password, email } = user;

    async function getData() {


        const resUsers = await UserService.getUserById(id);
        await setUser({
            ...user,
            userName: resUsers.data.userName,
            password: resUsers.data.password,
            email: resUsers.data.email,
            enabled: resUsers.data.enabled,

        });

        setRoleId(resUsers.data.rolesId);

        const res = await RoleService.getRoles();
        await setRoles(res.data);
        setLoading(false);
    }
    useEffect(() => {

        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }
        getData();
    }, []);

    const onChangeHandler = (e) => {
        setUser({ ...user, [e.target.name]: e.target.value });
    }

    const changeMultiRole = (id) => {
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

    const updateUser = (e) => {
        e.preventDefault();

        let user = { id: id, userName: userName, password: password, rolesId: roleId, enabled: true, email: email };

        UserService.updateUser(user).then(res => {
            this.props.history.push('/user');

        })
    }

    const checkBox = () => {
        console.log(roleId);
        const multiSelect = [];
        let info = false;
        roles.forEach(role => {
            roleId.forEach(id => {
                if (id === role.id) {
                    multiSelect.push(
                        <div className="row col-md -12">
                            <label><input type="checkbox" onChange={() => changeMultiRole(role.id)} defaultChecked="true" />{role.name}</label>
                        </div>)
                    info = true;
                }
            });


            if (info === false) {

                multiSelect.push(
                    <div className="row col-md -12">
                        <label><input type="checkbox" onChange={() => changeMultiRole(role.id)} />{role.name}</label>
                    </div>)
                info = true;

            }
            info = false;

        });
        return multiSelect;
    }
    return (
        <div>
            {loading == true ? <Loading/> :
                <div>
                    <Header />
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-canter">Edit User</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label>Username :</label>
                                            <input placeholder="Username" name="name" className="form-control"
                                                value={userName} onChange={onChangeHandler} />
                                        </div>
                                        <div className="form-group">
                                            <label>Password :</label>
                                            <input type="password" placeholder="Password" name="password" className="form-control"
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
                                                        checkBox()
                                                    }
                                                </div>

                                            </div>
                                        </div>
                                        <button className="btn btn-success" onClick={(e) => updateUser(e)}>Save</button>
                                        <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/user`)} >Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
        </div>)
}
export default EditUser;