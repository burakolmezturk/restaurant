import React, { Component } from 'react';
import UserService from '../../services/UserService';

class ListUserComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            users: []

        }
        this.addUser = this.addUser.bind(this);
        this.deleteUser = this.deleteUser.bind(this);

    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        UserService.getUsers().then((res) => {
            let user = res.data;
            this.setState({ users: user })

        })
    }
    addUser() {
        this.props.history.push(`add-user`);

    }
    deleteUser(userId) {
        UserService.deleteUser(userId).then(res => {
            this.setState({ users: this.state.users.filter(user => user.id !== userId) });
        });
    }
    editUser(id) {
        this.props.history.push(`/update-user/${id}`);
    }



    render() {
        return (
            <div>
                <h2 className="text-center">User List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addUser}>Add User</button>
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
                                this.state.users.map(
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
                                                <button onClick={() => this.editUser(user.id)} className="btn btn-info">Edit</button>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteUser(user.id)} className="btn btn-danger" >Delete</button>
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

export default ListUserComponent;