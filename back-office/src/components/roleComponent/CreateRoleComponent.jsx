import React, { Component } from 'react';

import RoleService from '../../services/RoleService';


class CreateRoleComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            name:'',                              
        }
        this.changeNameHandler=this.changeNameHandler.bind(this);

        }
        componentDidMount(){
            if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
                this.props.history.push('')
            }
        }
        saveRole = (e) => {
            e.preventDefault();
    
            let role={name:this.state.name};
    
                RoleService.createRole(role).then(res =>{
                    this.props.history.push('/role');
    
                })
        }

        cancel(){
            this.props.history.push('/role')
        }
        changeNameHandler=(event) =>{
            this.setState({name:event.target.value});
        }


    render() {
        return (
            <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add Category</h3>
                        <div className = "card-body">
                            <form>
                                
                                <div className="form-group">
                                    <label>Role Name :</label>
                                    <input placeholder="Role Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>

                                                    
                             <button className="btn btn-success" onClick={this.saveRole}>Save</button>
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

export default CreateRoleComponent;