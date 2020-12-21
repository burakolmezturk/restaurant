import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import ProductService from '../../services/ProductService';
import MediaService from '../../services/MediaService';
import Header from '../header/Header';
import CategoryService from '../../services/CategoryService';


const CreateProduct = () => {
    const history = useHistory();
    const [product, setProduct] = useState({
        name: '',
        description: '',
        categoryId: 0,
        salesPrice: 0,
        purchasePrice: 0,
        image: []
    });
    const { name, description, categoryId, salesPrice, purchasePrice, image } = product;

    const [multiCategories, setMultiCategories] = useState([]);
    const [medias, setMedias] = useState([]);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }

        getMedias();
        getCategories();

    }, []);

    async function getMedias() {

        const res = await MediaService.getMedias();

        await setMedias(res.data);

        setProduct({ ...product, image: res.data[0] });

    }
    async function getCategories(){
        const res = await CategoryService.getCategory();
        setCategories(res.data);
    }

    const onChangeHandler = (e) => {
        setProduct({ ...product, [e.target.name]: e.target.value })
    }

    const onChangeImage = (e) => {
        setProduct({ ...product, image: medias[e.target.value] });
    }
    const changeMultiCate = (id) => {
        if (multiCategories.includes(id) !== true) {
            multiCategories.push(id);
        } else {
            for (let i = 0; i < multiCategories.length; i++) {
                if (id === multiCategories[i]) {
                    multiCategories.splice(i, 1);
                }
            }
        }
    }
    const saveProduct = async (e) => {
        e.preventDefault();

        let product = {
            name: name, description: description, category: multiCategories,
            salesPrice: salesPrice, purchasePrice: purchasePrice,image:image
        };

       await ProductService.createProduct(product);
       history.push('/product');
    }
    const showImage =  () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }

    return (<div>
        <Header />
        <div className="container">
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-canter">Add Product</h3>
                    <div className="card-body">
                        <form>
                            <div className="form-group">
                                <label>Product Name :</label>
                                <input placeholder="Product Name" name="name" className="form-control"
                                    value={name} onChange={onChangeHandler} />
                            </div>
                            <div className="form-group">
                                <label>Product Description :</label>
                                <input placeholder="Product Description" name="description" className="form-control"
                                    value={description} onChange={onChangeHandler} />
                            </div>
                            <div className="form-group">
                                <label>Product Category :</label>
                                <div className="row col-md-12">
                                    <div className="checkbox" style={{ height: "10rem", width: "80rem", overflow: "auto" }}>
                                        {
                                            categories.map(
                                                category =>
                                                    <div className="row col-md -12">
                                                        <label><input type="checkbox" value="" onClick={() => changeMultiCate(category.id)} />{category.name}</label>
                                                    </div>
                                            )
                                        }
                                    </div>
                                </div>
                            </div>
                            <div className="form-group">
                                <label>Sales Price :</label>
                                <input placeholder="Sales Price" name="salesPrice" className="form-control"
                                    value={salesPrice} onChange={onChangeHandler} />
                            </div>
                            <div className="form-group">
                                <label>Purchase Price :</label>
                                <input placeholder="Purchase Price" name="purchasePrice" className="form-control"
                                    value={purchasePrice} onChange={onChangeHandler} />
                            </div>
                            <div className="form-group">
                                <label>Product Image :</label>
                                <select
                                    className="form-control" id="option" onChange={onChangeImage} >
                                    {
                                        medias.map(
                                            (media, index) =>

                                                <option key={media.id} value={index}>{media.fileName}</option>
                                        )
                                    }
                                </select>
                                {showImage()}
                            </div>
                            <button className="btn btn-success" onClick={(e) => saveProduct(e)}>Save</button>
                            <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/product`)} >Cancel</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>);
}
export default CreateProduct;