import React, { Component } from 'react';
import RoleService from '../../services/RoleService';


class ListRoleComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            roles: []
        }
        this.addRole= this.addRole.bind(this);
        this.deleteRole = this.deleteRole.bind(this);

    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        RoleService.getRoles().then((res) =>
            this.setState({ roles: res.data }))
    }
    addRole() {
        this.props.history.push(`add-role`);

    }
    deleteRole(roleDTO) {
       
        RoleService.deleteRole(roleDTO).then(res => {
            this.setState({ roles: this.state.roles.filter(role => role.id !== roleDTO.id) });
        });
    }
    editRole(id) {
        this.props.history.push(`/update-role/${id}`);
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Role List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addRole}>Add Role</button>
                    <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Role Name</th>
                                <th>Actions</th>
                            </tr>

                        </thead>

                        <tbody>
                            {
                                this.state.roles.map(
                                    role =>
                                        <tr key={role.id} >
                                            <td >{role.name}</td>
                                            <td >
                                                <button onClick={() => this.editRole(role.id)} className="btn btn-info">Edit</button>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteRole(role)} className="btn btn-danger" >Delete</button>
                                            </td>
                                        </tr>
                                )
                            }

                        </tbody>
                    </table>

                </div>
            </div>

        );
    }
}

export default ListRoleComponent;