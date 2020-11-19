import React, {Component} from 'react';
import '../../src/App.css';
import ProductService from '../services/ProductService';
import nextId from "react-id-generator";

class HomePageController extends Component {


    constructor(props) {
        super(props)
        this.state = {
            products: [],
            categories: [],
            carts: [],
            cart: {
                cartid: 0,
                id: 0,
                name: '',
                salesPrice: 0,
                piece: 1,
                total: 0
            },
            totalCarts: 0

        }

    }

    componentDidMount() {

        ProductService.getCategories().then((res) => {
            this.setState({categories: res.data})
        });

//		ProductService.getProduct().then((res)=>{
//		 this.setState({products:res.data})   
//	 });
    }

    increasePrice(cart) {

        this.state.totalCarts += cart.salesPrice;
        cart.piece += 1;
        cart.total = cart.salesPrice * cart.piece;
        //this.setState({carts: this.state.carts.filter(carts => carts.cartid == cart.cartid)});

        this.setState([{...this.state.carts, [cart.cartid]: cart}]);

    }

    decreasePrice(cart) {
        this.state.totalCarts -= cart.salesPrice;
        cart.piece -= 1;
        cart.total = cart.salesPrice * cart.piece;
        if (cart.piece == 0) {
            this.setState({carts: this.state.carts.filter(carts => carts.cartid !== cart.cartid)});
        } else {
            this.setState([{...this.state.carts, [cart.cartid]: cart}]);
        }

        //this.setState({carts: this.state.carts.filter(carts => carts.id == cart.id)});

    }

    addCarts(product) {
        this.state.totalCarts += product.salesPrice;

        if (this.state.carts.filter(carts => carts.id == product.id).length > 0) {
            var cart = this.state.carts.filter(carts => carts.id == product.id)
            cart[0].piece += 1
            cart[0].total =  cart[0].salesPrice *  cart[0].piece;
            this.setState([{...this.state.carts, [cart[0].id]: cart[0]}]);
        }else {

            this.setState({
                cart: {
                    cartid: nextId(),
                    id: product.id,
                    name: product.name,
                    salesPrice: product.salesPrice,
                    piece: 1,
                    total: product.salesPrice
                }
            }, () => this.setState({carts: [...this.state.carts, this.state.cart]}));

        }
    }

    getProducts(name) {

        ProductService.getProductsByCategory(name).then((res) => {
            this.setState({products: res.data})
        });

    }

    render() {
        return (
            <html>


            <div>
                <header>
                    <nav className="navbar navbar-expand-md navbar-dark bg-dark">
                        <div>
                            <a href="/home" className="navbar-brand" style={{textAlign: "center"}}>Restaurant
                                Automation</a>
                        </div>
                    </nav>
                </header>

            </div>
            <div className="container">
                <div className="row">
                    <div className="col-2">
                        <div className="sidebar">

                            <ul className="list-unstyled components">
                                <p style={{fontWeight: 'bold', fontSize: "15px", marginTop: "10px"}}>Product
                                    Categories</p>
                                {
                                    this.state.categories.map(categories =>
                                        <li>
                                            <button className="btn btn-secondary" style={{marginTop: "10px"}}
                                                    onClick={() => this.getProducts(categories.name)}>{categories.name}</button>
                                        </li>
                                    )}
                            </ul>
                        </div>
                    </div>
                    <div className="col-5">
                        <div className="row">
                            {
                                this.state.products.map(
                                    products =>
                                        <div className="col-sm-4">
                                            <div className="shadow-lg p-0 mb-2 bg-white rounded">
                                                <div key={products.id} className="card">
                                                    <div className="card-body">
                                                        <h5 className="card-title" style={{
                                                            textAlign: "center",
                                                            fontWeight: 'bold',
                                                            fontSize: "25px"
                                                        }}>{products.name}</h5>
                                                        <p className="card-text"
                                                           style={{textAlign: "center"}}>{products.description}</p>
                                                        <p className="card-text"
                                                           style={{textAlign: "center"}}>{products.salesPrice} ₺</p>

                                                    </div>
                                                    <button className="btn btn-secondary"
                                                            onClick={() => this.addCarts(products)}>Add To Carts
                                                    </button>

                                                </div>
                                            </div>
                                        </div>
                                )
                            }
                        </div>
                    </div>
                    <div className="col-5">
                        <table className="table table-striped table-bordered">
                            <thead>
                            <tr>
                                <th>Increase</th>
                                <th>Piece</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Decrease</th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.carts.map(
                                    cart =>
                                        <tr key={cart.cartid}>
                                            <td>
                                                <button className="btn btn-success"
                                                        onClick={() => this.increasePrice(cart)}>+
                                                </button>
                                            </td>
                                            <td>{cart.piece}</td>
                                            <td>{cart.name}</td>
                                            <td>{cart.salesPrice} ₺</td>
                                            <td>{cart.total}</td>

                                            <td>
                                                <button className="btn btn-danger"
                                                        onClick={() => this.decreasePrice(cart)}>-
                                                </button>
                                            </td>
                                        </tr>
                                )
                            }
                            </tbody>
                            <thead>
                            <tr>
                                <th></th>
                                <th></th>
                                <th>Total</th>
                                <th>{this.state.totalCarts} ₺</th>
                                <th>
                                    <button className="btn btn-outline-danger"
                                            onClick={() => this.saleButton()}>Payment
                                    </button>
                                </th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>

            </html>
        );
    }
}

export default HomePageController;