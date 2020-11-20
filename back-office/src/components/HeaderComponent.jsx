import React, {Component} from 'react';

class HeaderComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {

        }
    }
    render() {
        return (
            <div>
                <header>
                    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
                        <div><a href="/product" className="navbar-brand">List Product</a></div>
                        <div><a href="/user" className="navbar-brand">List User</a></div>
                        
                    </nav>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;