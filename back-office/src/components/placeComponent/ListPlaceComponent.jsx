import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import PlaceService from '../../services/PlaceService';


class ListPlaceComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            places:[]
        }
        this.addPlace = this.addPlace.bind(this);
        this.deletePlace=this.deletePlace.bind(this);
        
    }
    componentDidMount(){
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
         PlaceService.getPlaces().then((res)=>
         this.setState({places:res.data}))
    }
    addPlace(){      
            this.props.history.push(`add-place`);
                 
    }
    deletePlace(placeId){
        console.log(placeId)
        PlaceService.deletePlace(placeId).then(res =>{
            this.setState({places:this.state.places.filter(place=>place.id!==placeId)});      
    });
}
    editPlace(id){
        this.props.history.push(`/update-place/${id}`);
    }

    render() {
        return (
            <div>
            <h2 className="text-center">Place List</h2>
               <div className="row">
                   <button   className="btn btn-primary" onClick={this.addPlace}>Add Place</button>
                   <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Place Name</th>  
                                <th>Table Count</th>                              
                                <th>Actions</th>
                            </tr>

                        </thead>

                        <tbody>
                            {
                                this.state.places.map(
                                   place =>
                                   <tr key ={place.id} >
                                       <td >{place.name}</td>
                                       <td >{place.tableCount}</td>
                                       <td >
                                           <button onClick={() => this.editPlace(place.id)} className="btn btn-info">Edit</button>
                                           <button style={{marginLeft:"10px"}} onClick={() => this.deletePlace(place.id)} className="btn btn-danger" >Delete</button>                                  
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

export default ListPlaceComponent;