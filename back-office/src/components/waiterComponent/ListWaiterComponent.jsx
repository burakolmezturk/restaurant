import React, { Component } from 'react';
import WaiterService from '../../services/WaiterService';


class ListWaiterComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            waiters: []
        }
        this.addWaiter = this.addWaiter.bind(this);
        this.deleteWaiter = this.deleteWaiter.bind(this);

    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        WaiterService.getWaiters().then((res) =>
            this.setState({ waiters: res.data }))
    }
    addWaiter() {
        this.props.history.push(`add-waiter`);

    }
    deleteWaiter(waiterId) {

        WaiterService.deleteWaiter(waiterId).then(res => {
            this.setState({ waiters: this.state.waiters.filter(waiter => waiter.id !== waiterId) });
        });
    }
 
    editWaiter(id,imageId){
        console.log(imageId)
        this.props.history.push({
            pathname: '/update-waiter',
            state: {
                id: id,
                imageId: imageId
    
            },
        });
    
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Waiter List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={this.addWaiter}>Add Waiter</button>
                    <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Waiter Name</th>
                                <th>Waiter Age</th>
                                <th>Waiter Email</th>
                                <th>Waiter Phone</th>
                                <th>Waiter Image</th>
                                <th>Actions</th>
                            </tr>

                        </thead>

                        <tbody>
                            {
                                this.state.waiters.map(
                                    waiter =>
                                        <tr key={waiter.id} >
                                            <td >{waiter.name}</td>
                                            <td >{waiter.age}</td>
                                            <td >{waiter.email}</td>
                                            <td >{waiter.phone}</td>
                                            <td><img src={'data:image/png;base64,' + waiter.image.fileContent} style={{borderRadius:"10px"}} width="50" /></td>
                                            <td >
                                                <button onClick={() => this.editWaiter(waiter.id,waiter.image.id)} className="btn btn-info">Edit</button>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteWaiter(waiter.id)} className="btn btn-danger" >Delete</button>
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

export default ListWaiterComponent;