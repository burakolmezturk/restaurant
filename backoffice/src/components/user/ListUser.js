import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import RoleService from '../../services/RoleService';
import UserService from '../../services/UserService';
import Header from '../header/Header';
import Loading from '../loading/Loading';
const ListUser = () => {

    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [users, setUsers] = useState([]);

    async function getUsers() {
        const res = await UserService.getUsers();
        await setUsers(res.data);
        setLoading(false);
    }
    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('');
        }
        getUsers();
    }, []);
    const editUser = (id) => {
        history.push({
            pathname: '/update-user',
            state: {
                id: id
            }
        });
    }
    const deleteUser = async (id) => {
        await UserService.deleteUser(id);
         setUsers(users.filter(user => user.id !== id));
        
    }
    return (
        <>
            {loading == true ? <Loading /> :
                <div>
                    <Header />
                    <div className="container">
                        <h2 className="text-center">User List</h2>
                        <div className="row">
                            <button className="btn btn-primary" onClick={() => history.push(`/add-user`)}>Add User</button>
                            <table className="table table-striped table bordered">
                                <thead>
                                    <tr>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>Role</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        users.map(
                                            user =>
                                                <tr key={user.id} >
                                                    <td >{user.userName}</td>
                                                    <td >{user.email}</td>
                                                    <td>
                                                        {
                                                            user.rolesList.map(
                                                                role =>
                                                                    <a href="#">{role.name} </a>
                                                            )
                                                        }
                                                    </td>
                                                    <td >
                                                        <button onClick={() => editUser(user.id)} className="btn btn-info">Edit</button>
                                                        <button style={{ marginLeft: "10px" }} onClick={() => deleteUser(user.id)} className="btn btn-danger" >Delete</button>
                                                    </td>
                                                </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            }
        </>
    )
}
export default ListUser;