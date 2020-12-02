import React, { Component } from 'react';
import PlacesService from '../services/PlacesService';



class ListTableComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tableCount: this.props.history.location.state?.tableCount,
            placeId: this.props.history.location.state?.placeId

        }
    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }

        // PlacesService.getPlaces().then((res)=>
        //this.setState({places:res.data}))
        
    }


    order(tableId) {

        this.props.history.push({
            pathname: '/cart',
            state: {
                tableId: tableId,
                placeId: this.state.placeId

            },
        });

    }

    render() {
        const tables = [];
        for (let index = 1; index <= this.state.tableCount; index++) {
            tables.push(<div id="1" className="col-lg-4 col-xs-12 text-center">
                <div className="box" style={{ backgroundColor: "#adb5bd" }}>

                    <div class="box-btn">
                        <a onClick={()=>this.order(index)}><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                            <div className="box-title">
                                <h3 className="box-text1">{index}</h3>
                            </div>
                            <div className="box-text">
                                <span></span>
                            </div></a>
                    </div>
                </div>
            </div>)

        }
        return (
            <div>
                <nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
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
                        <div className="row">

                            {tables}
                        </div>
                    </div>
                </div>
            </div>

        );
    }
}

export default ListTableComponent;