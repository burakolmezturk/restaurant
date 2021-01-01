import React, { Component } from 'react';
import PlacesService from '../services/PlacesService';
import WaiterService from '../services/WaiterService';



class ListTableComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tableCount: this.props.history.location.state?.tableCount,
            placeId: this.props.history.location.state?.placeId,
            tableIndex: 0,
            waiters: [],
            cart: []

        }
    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }

        WaiterService.getWaiters().then((res) => {
            this.setState({ waiters: res.data });
        });
        // PlacesService.getPlaces().then((res)=>
        //this.setState({places:res.data}))

    }

    setTable(tableIndex) {
        this.setState({ tableIndex: tableIndex });

    }
    order(tableId, waiter) {

        this.props.history.push({
            pathname: '/cart',
            state: {
                tableId: tableId,
                placeId: this.state.placeId,
                waiter: waiter
            },
        });

    }

    render() {
        const tables = [];
        for (let index = 1; index <= this.state.tableCount; index++) {
            if (localStorage.getItem(`${this.state.placeId}-${index}`) !== null) {
                const res = JSON.parse(localStorage.getItem(`${this.state.placeId}-${index}`));

                tables.push(<div id="tables" className="col-lg-4 col-xs-12 text-center">
                    <div className="box" style={{ backgroundColor: "#ff6666", border: "2px solid red" }}>

                        <div className="box-btn">
                            <a onClick={() => this.setTable(index)} data-toggle="modal" data-target="#exampleModal"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                <div className="box-title">
                                    <h3 className="box-text1">{index}</h3>
                                </div>
                                <div className="box-text">
                                    <span >Item Count : {res.cart.length}</span>
                                </div></a>
                        </div>
                    </div>
                </div>)
            } else {

                tables.push(<div id="tables" className="col-lg-4 col-xs-12 text-center">
                    <div className="box" style={{ backgroundColor: "#5ac18e" }}>

                        <div className="box-btn">
                            <a onClick={() => this.setTable(index)} data-toggle="modal" data-target="#exampleModal"><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                <div className="box-title">
                                    <h3 className="box-text1">{index}</h3>
                                </div>
                                <div className="box-text">
                                    <span style={{ visibility: "hidden" }}>hiddenText</span>
                                </div></a>
                        </div>
                    </div>
                </div>)
            }



        }
        return (


            <div>
                <div className="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div className="modal-dialog" role="document">
                        <div className="modal-content">
                            <div className="modal-header">
                                <h5 className="modal-title" id="exampleModalLabel">Waiters</h5>
                                <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div className="modal-body">
                                <table className="table table-striped table bordered">
                                    <thead>
                                        <tr>
                                            <th>Waiter Image</th>
                                            <th>Waiter Name</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {
                                            this.state.waiters.map(
                                                waiter =>
                                                    <tr key={waiter.id}>
                                                        <td><img src={'data:image/png;base64,' + waiter.image.fileContent} style={{borderRadius:"30px"}} width="50" /></td>
                                                        <td>{waiter.name}</td>
                                                        <td><button onClick={() => this.order(this.state.tableIndex, waiter)} className="btn btn-info" data-dismiss="modal">Select</button></td>
                                                    </tr>
                                            )
                                        }
                                    </tbody>
                                </table>
                            </div>
                            <div className="modal-footer">
                                <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>

                            </div>
                        </div>
                    </div>
                </div>
                <nav className="navbar navbar-expand-lg  navbar-dark bg-dark">
                    <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                        aria-label="Toggle navigation">
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                        <a className="navbar-brand" href="#">Restaurant Automation</a>

                        <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
                            <li className="nav-item">

                            </li>
                        </ul>

                        <form className="form-inline my-2 my-lg-0">
                            <a href="/home" className="navbar-toggler-icon"></a>

                        </form>
                    </div>
                </nav>
                <div className="social-box">
                    <div className="container">
                        <h2 className="text-center">Table List</h2>

                        <div className="row" style={{ overflow: "auto", height: "37rem" }}>

                            {tables}

                        </div>
                    </div>
                </div>

            </div>

        );
    }
}

export default ListTableComponent;