import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import UserService from '../../services/UserService';

class UpdateCategoryComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            id:this.props.match.params.id,            
            name:'',
            description:'',
            image:'',
           
        }
               
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changeDescriptionHandler=this.changeDescriptionHandler.bind(this);
        this.changeImageHandler=this.changeImageHandler.bind(this);
        this.updateCategory=this.updateCategory.bind(this);
    }
    componentDidMount(){
        console.log(this.state.id);
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        CategoryService.getCategoryById(this.state.id).then((res) =>{
            let category = res.data;
            console.log(category);
            this.setState({id:category.id,
                name:category.name,
                description:category.description,
                image:category.image,
           
            });
        });
        
    }
    updateCategory = (e) => {
        e.preventDefault();
    
        let category={id:this.state.id,name:this.state.name,description:this.state.description,image:this.state.image};
    
            CategoryService.updateCategory(category).then(res =>{
                this.props.history.push('/category');
    
            })
    }
    changeIdHandler=(event) =>{
        this.setState({id:event.target.value});
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
      
    cancel(){
        this.props.history.push('/category')
    }

    render() {
        return (
           <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Category</h3>
                        <div className = "card-body">
                            <form>

                            <div className="form-group">
                                    <label>Name :</label>
                                    <input placeholder="name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Description :</label>
                                    <input placeholder="category" name="password" className="form-control"
                                    value={this.state.description} onChange={this.changeDescriptionHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Image :</label>
                                    <input placeholder="image" name="category" className="form-control"
                                    value={this.state.image} onChange={this.changeImageHandler}/>
                                </div>

                                <button className="btn btn-success" onClick={this.updateCategory}>Save</button>
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

export default UpdateCategoryComponent;