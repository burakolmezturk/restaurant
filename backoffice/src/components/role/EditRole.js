import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import RoleService from '../../services/RoleService';
import Header from '../header/Header';
import Loading from '../loading/Loading';

const EditRole = () => {

    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [role, setRole] = useState({
        id: history.location.state?.id,
        name: ''
    });
    const { id, name } = role;

    const changeNameHandler = (e) => {
        setRole({ ...role, name: e.target.value });
    }
    const updateRole = async (e) => {
        e.preventDefault();
        await RoleService.updateRole(role);
        history.push(`/role`);
    }
    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }
        getRoleById();
    }, []);

    async function getRoleById() {
        const res = await RoleService.getRoleById(id);
        await setRole({ ...role, name: res.data.name });
        setLoading(false);
    }
    return (<div>
        {loading == true ? <Loading/> :
            <div>
                <Header />
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-canter">Edit Role</h3>
                            <div className="card-body">
                                <form>
                                    <div className="form-group">
                                        <label>Role Name :</label>
                                        <input placeholder="Role Name" name="name" className="form-control"
                                            value={name} onChange={changeNameHandler} />
                                    </div>
                                    <button className="btn btn-success" onClick={(e) => updateRole(e)}>Save</button>
                                    <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/role`)} >Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        }
    </div>)
}
export default EditRole;