import React, { Component } from 'react';

import PlacesService from '../services/PlacesService';



class ListPlaceComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            places: []
        }
    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        PlacesService.getPlaces().then((res) =>
            this.setState({ places: res.data }))
    }



    getTables(tableCount, placeId) {
        console.log(tableCount);
        console.log(placeId);

        this.props.history.push({
            pathname: '/table',
            state: {
                placeId: placeId,
                tableCount: tableCount

            },
        });

    }

    render() {
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


                        {
                            this.state.places.map(
                                place =>
                                    <div id={place.tableCount} className="col-lg-4 col-xs-12 text-center">
                                        <div className="box" style={{ backgroundColor: "#adb5bd" }}>

                                            <div class="box-btn">
                                                <a onClick={()=>this.getTables(place.tableCount,place.id)}><i className="fa fa-behance fa-3x" aria-hidden="true"></i>
                                                    <div className="box-title">
                                                        <h3 className="box-text1">{place.name}</h3>
                                                    </div>
                                                    <div className="box-text">
                                                        <span></span>
                                                    </div></a>
                                            </div>
                                        </div>
                                    </div>
                            )
                        }




                    </div>
                </div>
                </div>
            </div>

        );
    }
}

export default ListPlaceComponent;