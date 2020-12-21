import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import RoleService from '../../services/RoleService';
import Header from '../header/Header';
import Loading from '../loading/Loading';


const ListRole = () => {

    const history = useHistory();
    const [roles, setRoles] = useState([]);
    const [loading, setLoading] = useState(true);
    useEffect(() => {

        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }

        getRoles();

    }, []);
    async function getRoles() {
        const res = await RoleService.getRoles();
        await setRoles(res.data);
        setLoading(false);
    }

    const editRole = (id) => {
        history.push({
            pathname: '/update-role',
            state: {
                id: id
            }
        });
    }
    const deleteRole = async (roleDTO) => {
        await RoleService.deleteRole(roleDTO);
        setRoles(roles.filter(role => role.id !== roleDTO.id))
    }
    return (
        <>
            {loading == true ? <Loading/> :
                <div>
                    <Header />
                    <div className="container">

                        <h2 className="text-center">Role List</h2>
                        <div className="row">
                            <button className="btn btn-primary" onClick={() => history.push('/add-role')}>Add Role</button>
                            <table className="table table-striped table bordered">
                                <thead>
                                    <tr>
                                        <th>Role Name</th>
                                        <th>Actions</th>
                                    </tr>

                                </thead>

                                <tbody>
                                    {
                                        roles.map(
                                            role =>
                                                <tr key={role.id} >
                                                    <td >{role.name}</td>
                                                    <td >
                                                        <button onClick={() => editRole(role.id)} className="btn btn-info">Edit</button>
                                                        <button style={{ marginLeft: "10px" }} onClick={() => deleteRole(role)} className="btn btn-danger" >Delete</button>
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
        </>);
}
export default ListRole;