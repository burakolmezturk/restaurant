import React, { Component } from 'react';
import { ThemeProvider } from 'react-bootstrap';
import CategoryService from '../../services/CategoryService';
import ProductService from '../../services/ProductService';
import MediaService from '../../services/MediaService';
class CreateProductComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            description: '',
            categoryId: 0,
            salesPrice: 0,
            purchasePrice: 0,
            categories: [],
            multiCategories: [],
            medias: [],
            image:[]
        }

        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeCategoryHandler = this.changeCategoryHandler.bind(this);
        this.changeSalesHandler = this.changeSalesHandler.bind(this);
        this.changePurchaseHandler = this.changePurchaseHandler.bind(this);
        this.changeImageSelect=this.changeImageSelect.bind(this);
        this.saveProduct = this.saveProduct.bind(this);
    }

    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        CategoryService.getCategory().then((res) => {
            this.setState({ categories: res.data })
        })
        this.getMedias();

    }
    changeMultiCate(id) {
        if (this.state.multiCategories.includes(id) !== true) {
            this.state.multiCategories.push(id);
            console.log(this.state.multiCategories)
        } else {
            for (let i = 0; i < this.state.multiCategories.length; i++) {
                if (id === this.state.multiCategories[i]) {
                    this.state.multiCategories.splice(i, 1);
                    console.log(this.state.multiCategories)
                }
            }
        }
    }
    saveProduct = (e) => {
        e.preventDefault();



        let product = {
            name: this.state.name, description: this.state.description, category: this.state.multiCategories,
            salesPrice: this.state.salesPrice, purchasePrice: this.state.purchasePrice,image:this.state.image
        };


        ProductService.createProduct(product).then(res => {
            this.props.history.push('/product');

        })
    }
    changeDescriptionHandler = (event) => {
        this.setState({ description: event.target.value });
    }
    changeNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }
    changeCategoryHandler = (event) => {
        this.setState({ category: event.target.value });
    }
    changeSalesHandler = (event) => {
        this.setState({ salesPrice: event.target.value });
    }
    changePurchaseHandler = (event) => {
        this.setState({ purchasePrice: event.target.value });
    }
    changeImageSelect=(event) =>{
        this.setState({image:this.state.medias[event.target.value]});
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
            this.setState({image:this.state.medias[0]})

        })
    }


    cancel() {
        this.props.history.push('/product')
    }
    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-canter">Add Product</h3>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label>Product Name :</label>
                                        <input placeholder="Product Name" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Product Description :</label>
                                        <input placeholder="Product Description" name="name" className="form-control"
                                            value={this.state.description} onChange={this.changeDescriptionHandler} />
                                    </div>

                                    <div className="form-group">
                                        <div className="row col-md-12">
                                            <div className="checkbox" style={{ height: "10rem", width: "80rem", overflow: "auto" }}>
                                                {
                                                    this.state.categories.map(
                                                        category =>
                                                            <div className="row col-md -12">
                                                                <label><input type="checkbox" value="" onClick={() => this.changeMultiCate(category.id)} />{category.name}</label>
                                                            </div>
                                                    )
                                                }
                                            </div>

                                        </div>
                                    </div>
                                    <div className="form-group">
                                        <label>Sales Price :</label>
                                        <input placeholder="Sales Price" name="salesprice" className="form-control"
                                            value={this.state.salesPrice} onChange={this.changeSalesHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Purchase Price :</label>
                                        <input placeholder="Purchase Price" name="purchaseprice" className="form-control"
                                            value={this.state.purchasePrice} onChange={this.changePurchaseHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Product Image :</label>
                                        <select
                                            className="form-control" id="option" onChange={this.changeImageSelect} > 
                                            {
                                                this.state.medias.map(
                                                    (media,index) =>

                                                        <option key={media.id} value={index}>{media.fileName}</option>
                                                )
                                            }
                                        </select>
                                        {this.showImage()}

                                    </div>

                                    <button className="btn btn-success" onClick={this.saveProduct}>Save</button>
                                    <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={this.cancel.bind(this)} >Cancel</button>
                                </form>
                            </div>

                        </div>

                    </div>


                </div>
            </div>
        );
    }
}

export default CreateProductComponent;