import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';


class ListCategoryComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            categories:[]
        }
        this.addCategory = this.addCategory.bind(this);
        this.deleteCategory=this.deleteCategory.bind(this);
        
    }
    componentDidMount(){
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        CategoryService.getCategory().then((res)=>
         this.setState({categories:res.data}))
    }
    addCategory(){      
            this.props.history.push(`add-category`);
                 
    }
    deleteCategory(categoryId){
        console.log(categoryId)
        CategoryService.deleteCategory(categoryId).then(res =>{
            this.setState({categories:this.state.categories.filter(category=>category.id!==categoryId)});      
    });
}
editCategory(id,imageId){
    console.log(imageId)
    this.props.history.push({
        pathname: '/update-category',
        state: {
            id: id,
            imageId: imageId

        },
    });

}

    render() {
        return (
            <div>
            <h2 className="text-center">Category List</h2>
               <div className="row">
                   <button   className="btn btn-primary" onClick={this.addCategory}>Add Category</button>
                   <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Category Name</th>
                                <th>Category Description</th>
                                <th>Category Image</th>
                                <th>Actions</th>
                            </tr>

                        </thead>

                        <tbody>
                            {
                                this.state.categories.map(
                                   category =>
                                   <tr key ={category.id} >
                                       <td >{category.name}</td>
                                       <td >{category.description}</td>
                                       <td><img src={'data:image/png;base64,' + category.image.fileContent} style={{borderRadius:"10px"}} width="50" /></td>
                                       <td >
                                           <button onClick={() => this.editCategory(category.id,category.image.id)} className="btn btn-info">Edit</button>
                                           <button style={{marginLeft:"10px"}} onClick={() => this.deleteCategory(category.id)} className="btn btn-danger" >Delete</button>                                  
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

export default ListCategoryComponent;