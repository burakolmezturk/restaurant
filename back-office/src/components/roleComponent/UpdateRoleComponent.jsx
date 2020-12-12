import React, { Component } from 'react';
import RoleService from '../../services/RoleService';



class UpdateRoleComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            id:this.props.match.params.id,            
            name:'',    
        }
               
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.updateRole=this.updateRole.bind(this);
    }
    componentDidMount(){
        
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        RoleService.getRoleById(this.state.id).then((res) =>{
            let role = res.data;
        
            this.setState({id:role.id,
                name:role.name          
            });
        });
        
    }
    updateRole = (e) => {
        e.preventDefault();
    
        let role={id:this.state.id,name:this.state.name};
    
            RoleService.updateRole(role).then(res =>{
                this.props.history.push('/role');
    
            })
    }
    changeIdHandler=(event) =>{
        this.setState({id:event.target.value});
    }
    changeNameHandler=(event) =>{
        this.setState({name:event.target.value});
    }
     
    cancel(){
        this.props.history.push('/role')
    }

    render() {
        return (
           <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Role</h3>
                        <div className = "card-body">
                            <form>

                            <div className="form-group">
                                    <label>Role Name :</label>
                                    <input placeholder="Role Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                               
                               

                                <button className="btn btn-success" onClick={this.updateRole}>Save</button>
                                <button style={{marginLeft:"10px"}}  className="btn btn-danger" onClick={this.cancel.bind(this)} >Cancel</button>
                            </form>
                        </div>

                    </div>

                </div>


            </div>
        </div>
        );
    }
}

export default UpdateRoleComponent;