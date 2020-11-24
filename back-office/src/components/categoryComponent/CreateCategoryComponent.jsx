import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import UserService from '../../services/UserService';

class CreateCategoryComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            name:'',
            description:'',
            image:'',  
        }
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changeDescriptionHandler=this.changeDescriptionHandler.bind(this);
        this.changeImageHandler=this.changeImageHandler.bind(this);

        }
        componentDidMount(){
            if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
                this.props.history.push('')
            }
        }
        saveCategory = (e) => {
            e.preventDefault();
    
            let category={name:this.state.name,description:this.state.description,image:this.state.image};
    
                CategoryService.createCategory(category).then(res =>{
                    this.props.history.push('/category');
    
                })
        }

        cancel(){
            this.props.history.push('/category')
        }
        changeNameHandler=(event) =>{
            this.setState({name:event.target.value});
        }
        changeDescriptionHandler=(event) =>{
            this.setState({description:event.target.value});
        }
        changeImageHandler=(event) =>{
            
            this.setState({image:event.target.value});
           
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
                                    <label>Category Name :</label>
                                    <input placeholder="Category Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Category Description :</label>
                                    <input placeholder="Category Description" name="description" className="form-control"
                                    value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                </div>
                                
                                <div className="form-group">
                                    <label>Category Image :</label>
                                    <input placeholder="Category Image" name="image" className="form-control"
                                    value={this.state.image} onChange={this.changeImageHandler}/>
                                </div>
                       

                                <button className="btn btn-success" onClick={this.saveCategory}>Save</button>
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

export default CreateCategoryComponent;