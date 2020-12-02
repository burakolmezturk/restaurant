import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import PlaceService from '../../services/PlaceService';
import TableService from '../../services/TableService';


class ListTableComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            tables:[],
            places:[]
        }
        this.addTable = this.addTable.bind(this);
        this.deleteTable=this.deleteTable.bind(this);
        
    }
    componentDidMount(){
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        PlaceService.getPlaces().then((res)=>
         this.setState({places:res.data}))
    }
    addTable(){      
            this.props.history.push(`add-table`);
                 
    }
    deleteTable(tableId){
       
        TableService.deleteTable(tableId).then(res =>{
            this.setState({tables:this.state.tables.filter(table=>table.id!==tableId)});      
    });
}
editTable(id,placeId){
    this.props.history.push({
  pathname: '/update-table',
  state: {
    id: id,
    placeId:placeId

  },
});

}

    render() {
        return (
            <div>
            <h2 className="text-center">Table List</h2>
               <div className="row">
                   <button   className="btn btn-primary" onClick={this.addTable}>Add Table</button>
                   <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Place Name</th>  
                                <th>Table Name</th>                                
                                <th>Actions</th>
                            </tr>

                        </thead>

                       
                        {
                                this.state.places.map(
                                    place =>
                                        <tbody>
                                            {
                                                place.restaurantTableSet.map(
                                                    table =>
                                                        <tr key={table.id}>
                                                            <td>{place.name}</td>
                                                            <td>{table.name}</td>
                                                            <td>
                                                                <button onClick={() => this.editTable(table.id,place.id)}
                                                                        className="btn btn-info"> Update
                                                                </button>
                                                                <button style={{marginLeft: "6px"}} onClick={() => this.deleteProduct(table.id)}
                                                                        className="btn btn-danger"> Delete
                                                                </button>
                                                            </td>
                                                        </tr>
                                                )
                                            }
                                        </tbody>
                                )
                            }

                        
                   </table>
                   
               </div>
            </div>
               
        );
    }
}

export default ListTableComponent;