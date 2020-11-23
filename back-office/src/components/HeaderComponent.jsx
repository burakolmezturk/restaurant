import React, {Component} from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }
    logOut= (e)=>{
        localStorage.clear();
        this.props.history.push('');
    }
    render() {
        return (
            
                   <nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
            <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false"
                        aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"></span>
                </button>
                <div className="collapse navbar-collapse" id="navbarTogglerDemo01">
                    <a className="navbar-brand" href="#">Admin Control</a>
                    <ul className="navbar-nav mr-auto mt-2 mt-lg-0">
                        <li className="nav-item active">
                            <a className="nav-link" href="/product" >Products<span className="sr-only">(current)</span></a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/user">Users</a>
                        </li>
                        <li className="nav-item">
                            <a className="nav-link" href="/user">Categories</a>
                        </li>        
                        <li className="nav-item">
                            <a className="nav-link" href="/user">Reports</a>
                        </li>            
                        <li className="nav-item">
                            <a className="nav-link" href="/user">Authorities</a>
                        </li>             
                    </ul>
                    <form className="form-inline my-2 my-lg-0">

                            <button className="btn btn-outline-success my-2 my-sm-0" type="submit" onClick={this.logOut}>Logout</button>
                    </form>
                </div>
            </nav>
            
        );
    }
}

export default HeaderComponent;