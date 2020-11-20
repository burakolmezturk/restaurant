import React, { Component } from 'react';
import UserService from '../../services/UserService';

class ListUserComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            users:[]
        }
        this.addUser = this.addUser.bind(this);
        this.deleteUser=this.deleteUser.bind(this);
        
    }
    componentDidMount(){
        UserService.getUsers().then((res)=>
         this.setState({users:res.data}))
    }
    addUser(){      
            this.props.history.push(`add-user`);
                 
    }
    deleteUser(userId){
        UserService.deleteUser(userId).then(res =>{
            this.setState({users:this.state.users.filter(user=>user.id!==userId)});      
    });
}
    editUser(id){
        this.props.history.push(`/update-user/${id}`);
    }

    render() {
        return (
            <div>
            <h2 className="text-center">User List</h2>
               <div className="row">
                   <div className="row">
                      
                   </div>
                   <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Username</th>
                                <th>Role</th>
                                <th>Actions</th>
                            </tr>

                        </thead>

                        <tbody>
                            {
                                this.state.users.map(
                                   user =>
                                   <tr key ={user.id} >
                                       <td>{user.username}</td>
                                       <td>{user.role}</td>
                                       <td>
                                           <button onClick={() => this.editUser(user.id)} className="btn btn-info">Edit</button>
                                           <button style={{marginLeft:"10px"}} onClick={() => this.deleteUser(user.id)} className="btn btn-danger" >Delete</button>                                  
                                       </td>
                                   </tr>   
                                )
                            }

                        </tbody>
                   </table>
                   <button   className="btn btn-primary" onClick={this.addUser}>Add User</button>
               </div>
            </div>
               
        );
    }
}

export default ListUserComponent;