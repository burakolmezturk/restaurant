import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import RoleService from '../../services/RoleService';
import Header from '../header/Header';
const CreateRole = () => {

    const history = useHistory();

    const [name, setName] = useState('');


    const changeNameHandler = (e) => {
        setName(e.target.value);
    }
    const saveRole = async (e) => {

        e.preventDefault();
        
        const role = { name: name };
        const res = await RoleService.createRole(role);
        if(res.status=='200'){
            history.push(`/role`);
        }

    }

    return (
        <div>
            <Header/>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add Role</h3>
                        <div className="card-body">
                            <form>

                                <div className="form-group">
                                    <label>Role Name :</label>
                                    <input placeholder="Role Name" name="name" className="form-control"
                                        value={name} onChange={changeNameHandler} />
                                </div>


                                <button className="btn btn-success" onClick={saveRole}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/role`)} >Cancel</button>
                            </form>
                        </div>

                    </div>

                </div>


            </div>
        </div>);
}
export default CreateRole;