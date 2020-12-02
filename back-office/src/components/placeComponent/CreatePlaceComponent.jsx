import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import PlaceService from '../../services/PlaceService';
import UserService from '../../services/UserService';

class CreatePlaceComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            name:'',
            tableCount:0                      
        }
        this.changeNameHandler=this.changeNameHandler.bind(this);

        }
        componentDidMount(){
            if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
                this.props.history.push('')
            }
        }
        savePlace = (e) => {
            e.preventDefault();
    
            let place={name:this.state.name,tableCount:this.state.tableCount};
    
                PlaceService.createPlace(place).then(res =>{
                    this.props.history.push('/place');
    
                })
        }

        cancel(){
            this.props.history.push('/place')
        }
        changeNameHandler=(event) =>{
            this.setState({name:event.target.value});
        }
        changeTableCountHandler=(event)  =>{
            this.setState({tableCount:event.target.value});
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
                                    <label>Place Name :</label>
                                    <input placeholder="Place Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Table Count :</label>
                                    <input placeholder="Table Count" name="tableCount" className="form-control"
                                    value={this.state.tableCount} onChange={this.changeTableCountHandler}/>
                                </div>
                               
                       
                             <button className="btn btn-success" onClick={this.savePlace}>Save</button>
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

export default CreatePlaceComponent;