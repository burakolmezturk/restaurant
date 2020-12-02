import React, { Component } from 'react';
import PlaceService from '../../services/PlaceService';


class UpdatePlaceComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            id:this.props.match.params.id,            
            name:'',
            tableCount:0           
        }
               
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changeTableCountHandler=this.changeTableCountHandler.bind(this);
        this.updatePlace=this.updatePlace.bind(this);
    }
    componentDidMount(){
        
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        PlaceService.getPlaceById(this.state.id).then((res) =>{
            let place = res.data;
        
            this.setState({id:place.id,
                name:place.name          
            });
        });
        
    }
    updatePlace = (e) => {
        e.preventDefault();
    
        let place={id:this.state.id,name:this.state.name};
    
            PlaceService.updatePlace(place).then(res =>{
                this.props.history.push('/place');
    
            })
    }
    changeIdHandler=(event) =>{
        this.setState({id:event.target.value});
    }
    changeNameHandler=(event) =>{
        this.setState({name:event.target.value});
    }
    changeTableCountHandler=(event) =>{
        this.setState({tableCount:event.target.value});
    }
     
    cancel(){
        this.props.history.push('/place')
    }

    render() {
        return (
           <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Place</h3>
                        <div className = "card-body">
                            <form>

                            <div className="form-group">
                                    <label>Place Name :</label>
                                    <input placeholder="Place Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Table Count :</label>
                                    <input placeholder="Table Count" name="tableCount" className="form-control"
                                    value={this.state.tableCount} onChange={this.changeTableCountHandler}/>
                                </div>
                               

                                <button className="btn btn-success" onClick={this.updatePlace}>Save</button>
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

export default UpdatePlaceComponent;