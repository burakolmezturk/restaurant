import React, { Component } from 'react';
import PlaceService from '../../services/PlaceService';
import TableService from '../../services/TableService';

class UpdateTableComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            id:this.props.history.location.state?.id,
            name:'',
            placeId:this.props.history.location.state?.placeId,
            places:[]
           
        }
               
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.updateTable=this.updateTable.bind(this);
    }
    componentDidMount(){
        
        TableService.getTableById(this.state.id).then((res) =>{
           
            let table = res.data;
            this.setState({id:table.id,
                name:table.name,                
                description:table.description,
                salesPrice:table.salesPrice,
                purchasePrice:table.purchasePrice
            
            });
        });
        
        PlaceService.getPlaces().then((res)=>{
            this.setState({places:res.data})
            
        }
        )
    }
    updateTable = (e) => {
        e.preventDefault();
    
        let table={id:this.state.id,name:this.state.name};
    
            TableService.updateTable(table,document.getElementById('option').value).then(res =>{
                this.props.history.push('/table');
    
            })
    }
    changeIdHandler=(event) =>{
        this.setState({id:event.target.value});
    }
    changeNameHandler=(event) =>{
        this.setState({name:event.target.value});
    }
    cancel(){
        this.props.history.push('/Table')
    }

    render() {
        return (
           <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Table</h3>
                        <div className = "card-body">
                            <form>

                                <div className="form-group">
                                    <label>Table Name :</label>
                                    <input placeholder="Table Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>                              
                                <div className="form-group">
                                <select   
                                                className="form-control" id="option">
                                            {
                                                this.state.places.map(
                                                    place =>
                                                        
                                                        <option  key={place.id} selected={this.state.placeId==place.id} value ={place.id}>{place.name}</option>
                                                )
                                            }
                                        </select>
                                </div>
                              

                                <button className="btn btn-success" onClick={this.updateTable}>Save</button>
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

export default UpdateTableComponent;