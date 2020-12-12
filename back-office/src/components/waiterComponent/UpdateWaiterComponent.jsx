import React, { Component } from 'react';

import MediaService from '../../services/MediaService';
import WaiterService from '../../services/WaiterService';

class UpdateWaiterComponent extends Component {
    constructor(props){
        super(props)
        this.state = {
            id:this.props.history.location.state?.id,           
            name:'', 
            email:'',
            phone:'',
            age:0,
            imageId:this.props.history.location.state?.imageId,
            medias: [],
            image:[]    
        }
               
        this.changeNameHandler=this.changeNameHandler.bind(this);
        this.changeEmailHandler=this.changeEmailHandler.bind(this);
        this.changePhoneHandler=this.changePhoneHandler.bind(this);
        this.changeAgeHandler=this.changeAgeHandler.bind(this);
        this.changeImageSelect=this.changeImageSelect.bind(this);
        this.updateWaiter=this.updateWaiter.bind(this);
    }
    componentDidMount(){
        console.log(this.state.id);
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        WaiterService.getWaiterById(this.state.id).then((res) =>{
            let waiter = res.data;   
            this.setState({id:waiter.id,
                name:waiter.name,
                email:waiter.email,
                age:waiter.age,
                phone:waiter.phone,
                image:waiter.image
            });
        });
        this.getMedias();
        
    }
    updateWaiter = (e) => {
        e.preventDefault();
    
        let waiter={id:this.state.id,name:this.state.name,email:this.state.email,phone:this.state.phone,age:this.state.age,image:this.state.image};
    
            WaiterService.updateWaiter(waiter).then(res =>{
                this.props.history.push('/waiter');
    
            })
    }
    showImage() {
        const html = [];
        const images = this.state.image
        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;
    }
    getMedias() {
        MediaService.getMedias().then(res => {
            this.setState({ medias: res.data });
            
        })
    }
    changeIdHandler=(event) =>{
        this.setState({id:event.target.value});
    }
    changeNameHandler=(event) =>{
        this.setState({name:event.target.value});
    }
    changeEmailHandler=(event) =>{
        this.setState({email:event.target.value});
    }
    changeAgeHandler=(event) =>{
        this.setState({age:event.target.value});
    }
    changePhoneHandler=(event) =>{
        this.setState({phone:event.target.value});
    }
    changeImageSelect=(event) =>{
        this.setState({image:this.state.medias[event.target.value]});
    }
    
    cancel(){
        this.props.history.push('/waiter')
    }

    render() {
        return (
           <div>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Waiter</h3>
                        <div className = "card-body">
                            <form>

                            <div className="form-group">
                                    <label>Waiter Name :</label>
                                    <input placeholder="Name" name="name" className="form-control"
                                    value={this.state.name} onChange={this.changeNameHandler}/>
                                </div>
                                <div className="form-group">
                                    <label>Waiter Age :</label>
                                    <input placeholder="Age" name="age" className="form-control"
                                    value={this.state.age} onChange={this.changeAgeHandler} type="number" />
                                </div>
                                <div className="form-group">
                                    <label>Waiter Email :</label>
                                    <input placeholder="Email" name="email" className="form-control"
                                    value={this.state.email} onChange={this.changeEmailHandler} type="email"/>
                                </div>
                                <div className="form-group">
                                    <label>Waiter Phone :</label>
                                    <input placeholder="name" name="name" className="form-control"
                                    value={this.state.phone} onChange={this.changePhoneHandler} type="number"/>
                                </div>
                                <div className="form-group">
                                        <label>Category Image :</label>
                                        <select
                                            className="form-control" id="option" onChange={this.changeImageSelect} > 
                                            {
                                                this.state.medias.map(
                                                    (media,index) =>

                                                        <option key={media.id} selected={this.state.imageId==media.id}  value={index}>{media.fileName}</option>
                                                )
                                            }
                                        </select>
                                        {this.showImage()}

                                    </div>
                               

                                <button type="submit" className="btn btn-success" onClick={this.updateWaiter}>Save</button>
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

export default UpdateWaiterComponent;