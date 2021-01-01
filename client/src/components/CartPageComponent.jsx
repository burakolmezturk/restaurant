import React, { Component } from 'react';
import '../../src/App.css';
import ProductService from '../services/ProductService';
import nextId from "react-id-generator";
import CategoryService from '../services/CategoryService';
import Loading from './Loading';
import InfiniteScroll from 'react-infinite-scroller';
import OrderService from '../services/OrderService';
import { FormCheck } from 'react-bootstrap';
import { PaymenTypes } from '../components/Enums';
class CartPageComponent extends Component {


    constructor(props) {


        super(props)
        this.state = {
            placeId: this.props.history.location.state?.placeId,
            tableId: this.props.history.location.state?.tableId,
            waiter: this.props.history.location.state?.waiter,
            customer: this.props.history.location.state?.customer,
            products: [],
            categories: [],
            carts: [],
            totalCarts: 0,
            loading: true,
            loadData: true,
            slice: 1,
            categoryId: 0,
            paymentType: PaymenTypes.CASH,
            cashCheckbox: true,
            creditCheckbox: false
        }
        this.handleChangeCash = this.handleChangeCash.bind(this);
        this.handleChangeCredit = this.handleChangeCredit.bind(this);
    }
    async loadPage() {
        const res = await CategoryService.getCategory();
        if (!res.data) {
            alert("Categories not found");
            return;
        }
        this.setState({ categories: res.data });

        const data = await ProductService.getProductSlice(this.state.categories[0].id, 0);
        if (!data.data) {
            alert("Products not found");
            return;
        }
        this.setState({ products: data.data.content, categoryId: this.state.categories[0].id });


        if (data.data.numberOfElements !== data.data.size) {
            this.setState({ loadData: false })
        }
        await this.getCartFromStorage();
        this.setState({ loading: false })

    }
    handleChangeCash(e) {


        if (e.target.checked == true) {
            this.setState({ cashCheckbox: true, creditCheckbox: false, paymentType: PaymenTypes.CASH });
            return;
        }
        this.setState({ cashCheckbox: false, creditCheckbox: true, paymentType: PaymenTypes.CREDITCARD })

    }

    handleChangeCredit(e) {

        if (e.target.checked == true) {
            this.setState({ creditCheckbox: true, cashCheckbox: false, paymentType: PaymenTypes.CREDITCARD });
            return;
        }

        this.setState({ creditCheckbox: false, cashCheckbox: true, paymentType: PaymenTypes.CASH })

    }

    componentDidMount() {
    
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }

        this.loadPage();
    }

    addCartToStorage() {
       const {tableId} = this.state;
       const {placeId} = this.state;

        if (this.state.carts.length > 0 && !placeId) {

            let order = { place: placeId, table: tableId, waiter: this.state.waiter.id, totalCart: this.state.totalCarts, cart: this.state.carts };
            localStorage.setItem(`${placeId}-${tableId}`, JSON.stringify(order));
        } 

    }

    getCartFromStorage() {
        let order = [];

        if (localStorage.getItem(`${this.state.placeId}-${this.state.tableId}`) === null) {
            order = [];
            return;
        }
        order = JSON.parse(localStorage.getItem(`${this.state.placeId}-${this.state.tableId}`));
        this.setState({ carts: order.cart, totalCarts: order.totalCart })

    }
    
    saleButton() {
        const cart = {
            waiter: this.state.waiter,
            customer: this.state.customer,
            orderItems: this.state.carts,
            paymentType: this.state.paymentType
        }
        console.log(cart);
        OrderService.addOrder(cart).then((res) => {
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
        let filterProduct = this.state.carts.filter(carts => carts.productId == product.id);
        if (filterProduct.length > 0) {

            filterProduct[0].piece += 1
            filterProduct[0].totalPrice = filterProduct[0].price * filterProduct[0].piece;
            this.setState([{ ...this.state.carts, [filterProduct[0].id]: filterProduct[0] }]);
            return;
        }
        const cart = {
            cartid: nextId(),
            product: product,
            productId: product.id,
            productName: product.name,
            price: product.salesPrice,
            piece: 1,
            totalPrice: product.salesPrice,

        }
        this.setState({ carts: [...this.state.carts, cart] });
    };

    getProducts(id) {

        ProductService.getProductSlice(id, 0).then((res) => {
            //this.state.products.push(res.data.content)
            console.log(id);
            this.setState({ slice: 0 })
            this.setState({ categoryId: id });
            const resdata = res.data.content
            this.setState({ products: resdata })
            if (res.data.numberOfElements != res.data.size) {
                this.setState({ slice: 0 })
                this.setState({ loadData: false })
            } else {
                this.setState({ loadData: true })
                this.setState({ slice: this.state.slice + 1 })
            }
        });


    }
    getProductsCategory(id) {
        console.log(this.state.slice)
        ProductService.getProductSlice(this.state.categoryId, this.state.slice).then((res) => {
            //this.state.products.push(res.data.content)

            const resdata = res.data.content
            this.setState({ products: [...this.state.products, ...resdata] })
            if (res.data.numberOfElements != res.data.size) {

                this.setState({ loadData: false })
            } else {
                this.setState({ loadData: true })
                this.setState({ slice: this.state.slice + 1 })
            }
        });


    }

    getModal() {
        return (
            <div className="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div className="modal-dialog" role="document">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h5 className="modal-title" id="exampleModalLabel">Payment Type</h5>
                            <button type="button" className="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div className="modal-body">
                            <FormCheck
                                label="Cash"
                                checked={this.state.cashCheckbox}
                                onChange={this.handleChangeCash} />
                            <FormCheck
                                label="Credit"
                                checked={this.state.creditCheckbox}
                                onChange={this.handleChangeCredit} />
                        </div>
                        <div className="modal-footer">
                            <button type="button" onClick={() => this.saleButton()} className="btn btn-secondary" data-dismiss="modal" >Payment</button>
                            <button type="button" className="btn btn-secondary" data-dismiss="modal">Close</button>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
    getNavbar() {
        return (<nav class="navbar navbar-expand-lg  navbar-dark bg-dark">
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
                    <a href="/place" onClick={() => this.addCartToStorage()} className="navbar-toggler-icon"></a>

                </form>
            </div>
        </nav>)
    }
    getCategoryList() {
        return (<div className="card card-body-2">

            <div className="sidebar">

                <div className="list-group">
                    <a className="list-group-item list-group-item-action " style={{ fontWeight: "bold", backgroundColor: "#868e96", color: "white" }}>Product Categories</a>
                    <div style={{ overflow: "auto", height: "33.5rem" }}>
                        {
                            this.state.categories.map(categories =>

                                <a href="#" style={{ backgroundColor: "#f8f9fa", textAlign: "left" }} className="list-group-item list-group-item-action list-group-item-dark"
                                    onClick={() => this.getProducts(categories.id)} > <img src={'data:image/png;base64,' + categories.image.fileContent} style={{ width: "2rem", height: "2rem", borderRadius: "10px" }}></img> {categories.name} </a>


                            )}
                    </div>
                </div>
            </div>

        </div>)
    }
    getProducts() {
        return (
            <div className="card card-body-1">
                <div className="scroll-1">
                    <InfiniteScroll
                        pageStart={this.state.slice}
                        loadMore={this.getProductsCategory.bind(this)}
                        hasMore={this.state.loadData}
                        useWindow={false}
                    >
                        <div className="row">
                            {
                                this.state.products.map(
                                    products =>
                                        <div className="col-sm-6">
                                            <div className="shadow-lg p-0 mb-2 bg-white rounded">
                                                <div key={products.id} className="card">
                                                    <a style={{ textAlign: "center" }}> <img style={{ borderRadius: "3px" }} src={'data:image/png;base64,' + products.image.fileContent} width="100" height="100" /></a>
                                                    <div className="card-body">

                                                        <h5 className="card-title" style={{
                                                            textAlign: "center",
                                                            fontWeight: 'bold',
                                                            fontSize: "18px"
                                                        }}>{products.name}</h5>
                                                        <p className="card-text"
                                                            style={{ fontSize: '13px', textAlign: "center" }}>{products.description}</p>
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
                    </InfiniteScroll>
                </div>
            </div>
        )
    }
    getOrders() {
        return (
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

                                                <td style={{ textAlign: "left" }}>{cart.productName}</td>
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
                    data-toggle="modal" data-target="#exampleModal">Payment
                                    </button>
            </div>
        )
    }

    render() {
        const loading = this.state.loading;
        return (
            <html>
                {loading == true ? <Loading /> :
                    <div>
                        {this.getModal()}
                        {this.getNavbar()}
                        <div className="container">

                            <div className="row">
                                <div className="col-md-2">
                                    {this.getCategoryList()}
                                </div>
                                <div className="col-md-5">
                                    {this.getProducts()}
                                </div>
                                <div className="row">
                                    {this.getOrders()}
                                </div>
                            </div>
                        </div>
                    </div>
                }
            </html>
        );
    }
}

export default CartPageComponent;