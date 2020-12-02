import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import PlaceService from '../../services/PlaceService';
import TableService from '../../services/TableService';
import UserService from '../../services/UserService';

class CreateTableComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            name:'',
            places:[]                      
        }
        this.changeNameHandler=this.changeNameHandler.bind(this);

        }
        componentDidMount(){
            if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
                this.props.history.push('')
            }
            PlaceService.getPlaces().then((res)=>{
                this.setState({places:res.data})
            })
        }
        saveTable = (e) => {
            e.preventDefault();
    
            let table={name:this.state.name};
    console.log(table)
                TableService.createTable(table,document.getElementById('option').value).then(res =>{
                    this.props.history.push('/table');
    
                })
        }

        cancel(){
            this.props.history.push('/table')
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
                        <h3 className="text-canter">Add Table</h3>
                        <div className = "card-body">
                            <form>
                                
                                <div className="form-group">
                                    <label>Table Name :</label>
                                    <input tableholder="table Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                <label>Table Place :</label>
                                <select   
                                                className="form-control" id="option">
                                            {
                                                this.state.places.map(
                                                    place =>
                                                        
                                                        <option key={place.id} value ={place.id}>{place.name}</option>
                                                )
                                            }
                                        </select>
                                </div>
                       
                             <button className="btn btn-success" onClick={this.saveTable}>Save</button>
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

export default CreateTableComponent;