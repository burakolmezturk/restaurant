import React, { Component } from 'react';
import '../../src/App.css';
import ProductService from '../services/ProductService';
import nextId from "react-id-generator";
import CategoryService from '../services/CategoryService';
import { get } from 'js-cookie';

class CartPageComponent extends Component {


    constructor(props) {


        super(props)
        this.state = {
            placeId: this.props.history.location.state?.placeId,
            tableId: this.props.history.location.state?.tableId,
            waiterId: this.props.history.location.state?.waiterId,
            products: [],
            categories: [],
            carts: [],
            cart: {
                cartid: 0,
                productId: 0,
                pdocutName: '',
                price: 0,
                piece: 1,
                totalPrice: 0,

            },
            totalCarts: 0

        }

    }
    async loadPage() {
        const res = await CategoryService.getCategory();
        await this.setState({ categories: res.data });
        const data = await ProductService.getProductsByCategory(this.state.categories[0].id);
        await this.setState({ products: data.data });
        await this.getCartFromStorage();

    }

    componentDidMount() {

        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }

        // CategoryService.getCategory().then((res) => {
        //     this.setState({ categories: res.data })                      
        // }).then((res2)=>ProductService.getProductsByCategory(this.state.categories[0].id).then((res) => {
        //     this.setState({ products: res.data })
        // }) );
        // this.getCartFromStorage();
        this.loadPage();
    }
    addCartToStorage(table, place, waiter) {
        if (this.state.carts.length > 0 && this.state.placeId != 0 && this.state.placeId != undefined) {
            let order = { place: place, table: table, waiter: waiter, totalCart: this.state.totalCarts, cart: this.state.carts };
            localStorage.removeItem(`${this.state.placeId}-${this.state.tableId}`);
            localStorage.setItem(`${this.state.placeId}-${this.state.tableId}`, JSON.stringify(order));
        } else {

        }
    }
    getCartFromStorage() {
        let order = [];

        if (localStorage.getItem(`${this.state.placeId}-${this.state.tableId}`) === null) {
            order = [];
        } else {
            order = JSON.parse(localStorage.getItem(`${this.state.placeId}-${this.state.tableId}`));
            this.setState({ carts: order.cart, totalCarts: order.totalCart })
        }
    }
    saleButton(cart) {
        console.log(cart)
        ProductService.postCart(cart).then((res) => {
            if (this.state.placeId != 0 && this.state.placeId != undefined) {
                localStorage.removeItem(`${this.state.placeId}-${this.state.tableId}`);
                this.props.history.push('/place')
            } else {
                window.location.reload(false);
            }

        });

    }

    increasePrice(cart) {
        console.log(cart)
        this.state.totalCarts += cart.price;
        cart.piece += 1;
        cart.totalPrice = cart.price * cart.piece;
        this.setState([{ ...this.state.carts, [cart.cartid]: cart }]);

    }

    decreasePrice(cart) {
        this.state.totalCarts -= cart.price;
        cart.piece -= 1;
        cart.totalPrice = cart.price * cart.piece;
        if (cart.piece == 0) {
            this.setState({ carts: this.state.carts.filter(carts => carts.cartid !== cart.cartid) });
        } else {
            this.setState([{ ...this.state.carts, [cart.cartid]: cart }]);
        }
    }

    addCarts(product) {
        this.state.totalCarts += product.salesPrice;

        if (this.state.carts.filter(carts => carts.productId == product.id).length > 0) {
            var cart = this.state.carts.filter(carts => carts.productId == product.id)
            cart[0].piece += 1
            cart[0].totalPrice = cart[0].price * cart[0].piece;
            this.setState([{ ...this.state.carts, [cart[0].id]: cart[0] }]);
        } else {

            this.setState({
                cart: {
                    cartid: nextId(),
                    productId: product.id,
                    productName: product.name,
                    price: product.salesPrice,
                    piece: 1,
                    totalPrice: product.salesPrice,
                    placeId: this.state.placeId,
                    tableId: this.state.tableId,
                    waiterId: this.state.waiterId
                }
            }, () => this.setState({ carts: [...this.state.carts, this.state.cart] }));
        }
    }

    getProducts(id) {

        ProductService.getProductsByCategory(id).then((res) => {
            this.setState({ products: res.data })
        });

    }

    render() {
        return (
            <html>
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
                                <a className="nav-link" href="/place">Tables</a>
                            </li>
                        </ul>

                        <form className="form-inline my-2 my-lg-0">
                            <a href="/place" onClick={() => this.addCartToStorage(this.state.tableId, this.state.placeId, this.state.waiterId)} className="navbar-toggler-icon"></a>

                        </form>
                    </div>
                </nav>
                <div className="container">

                    <div className="row">
                        <div className="col-md-2">
                            <div className="card card-body-2">

                                <div className="sidebar">

                                    <div className="list-group">
                                        <a className="list-group-item list-group-item-action " style={{ fontWeight: "bold", backgroundColor: "#868e96", color: "white" }}>Product Categories</a>
                                        {
                                            this.state.categories.map(categories =>
                                                 
                                                <a href="#" style={{ backgroundColor: "#f8f9fa" }} className="list-group-item list-group-item-action list-group-item-dark"
                                                    onClick={() => this.getProducts(categories.id)} > <img src={'data:image/png;base64,' + categories.image.fileContent} style={{width:"2rem",height:"2rem",borderRadius:"10px"}}></img> {categories.name} </a>

                                            )}
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div className="col-md-5">
                            <div className="card card-body-1">
                                <div className="scroll-1">
                                    <div className="row">

                                        {
                                            this.state.products.map(
                                                products =>
                                                    <div className="col-sm-6">
                                                        <div className="shadow-lg p-0 mb-2 bg-white rounded">
                                                            <div key={products.id} className="card">
                                                                <div className="card-body">
                                                                    <h5 className="card-title" style={{
                                                                        textAlign: "center",
                                                                        fontWeight: 'bold',
                                                                        fontSize: "25px"
                                                                    }}>{products.name}</h5>
                                                                    <p className="card-text"
                                                                        style={{ textAlign: "center" }}>{products.description}</p>
                                                                    <p className="card-text"
                                                                        style={{ textAlign: "center" }}>{products.salesPrice} ₺</p>

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
                            </div>
                        </div>
                        <div className="row">
                            <div className="card card-body-3">
                                <div className="col-md-5">
                                    <div className="scroll">
                                        <table className="table table-striped borderless">
                                            <thead>
                                                <tr>
                                                    <th>Increase</th>
                                                    <th>Name</th>
                                                    <th>Piece</th>
                                                    <th>Total</th>
                                                    <th>Decrease</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                {
                                                    this.state.carts.map(
                                                        cart =>
                                                            <tr key={cart.cartid}>
                                                                <td style={{ textAlign: "center" }}>
                                                                    <button className="btn btn-success"
                                                                        onClick={() => this.increasePrice(cart)}>+
                                                </button>
                                                                </td>

                                                                <td style={{ textAlign:"left" }}>{cart.productName}</td>
                                                                <td style={{ textAlign: "left" }}>{cart.piece}</td>

                                                                <td style={{ textAlign: "left" }}>{cart.totalPrice} ₺</td>

                                                                <td style={{ textAlign: "center" }}>
                                                                    <button className="btn btn-danger"
                                                                        onClick={() => this.decreasePrice(cart)}>-
                                                </button>
                                                                </td>
                                                            </tr>
                                                    )
                                                }
                                            </tbody>

                                        </table>

                                    </div>
                                </div>


                                <div className="card" style={{ backgroundColor: "#f9f9f9" }}>
                                    <a style={{ fontSize: "15px", fontWeight: "bold", marginLeft: "10px", color: "#dc3545" }}>Total Price :<a>{this.state.totalCarts} ₺</a></a>
                                </div>

                                <button className="btn" style={{ backgroundColor: "#868e96", color: "white" }}
                                    onClick={() => this.saleButton(this.state.carts)}>Payment
                                    </button>


                            </div>
                        </div>
                    </div>
                </div>
            </html>
        );
    }
}

export default CartPageComponent;