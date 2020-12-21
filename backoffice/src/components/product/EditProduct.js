import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import CategoryService from '../../services/CategoryService';
import MediaService from '../../services/MediaService';
import ProductService from '../../services/ProductService';
import Header from '../header/Header';
import Loading from '../loading/Loading';

const EditProduct = () => {
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [product, setProduct] = useState({
        id: history.location.state?.id,
        name: '',
        description: '',
        categoryId: history.location.state?.catId,
        salesPrice: 0,
        purchasePrice: 0,
        imageId: history.location.state?.imageId,
        image: []
    });
    const { id, name, description, categoryId, salesPrice, purchasePrice, image, imageId } = product;

    const [categoryIdList, setCategoryIdList] = useState([]);
    const [categories, setCategories] = useState([]);
    const [medias, setMedias] = useState([]);

    const changeMultiCate = (id) => {

        if (categoryIdList.includes(id) !== true) {
            categoryIdList.push(id);

        } else {
            for (let i = 0; i < categoryIdList.length; i++) {
                if (id === categoryIdList[i]) {
                    categoryIdList.splice(i, 1);
                }
            }
        }
    }
    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }

        getData();
    },[]);

    const showImage = () => {
        const html = [];
        const images = image
        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;
    }
    async function getData() {

        const resMedia = await MediaService.getMedias();
        await setMedias(resMedia.data);

        const resProd = await ProductService.getProductById(id);
        await setProduct({
            ...product,
            name: resProd.data.name,
            description: resProd.data.description,
            salesPrice: resProd.data.salesPrice,
            purchasePrice: resProd.data.purchasePrice,
            image: resProd.data.image

        });
        await setCategoryIdList(resProd.data.categoryIdList);
        const resCat = await CategoryService.getCategory();
      await  setCategories(resCat.data);
      setLoading(false);


    }

    const onChangeHandler = (e) => { 
        console.log('girdi')   
        setProduct({ ...product, [e.target.name]: e.target.value })
    }

    const onChangeImage = (e) => {
        setProduct({ ...product, image: medias[e.target.value] });
    }
    const updateProduct = async (e) => {
        e.preventDefault();
        let product = {
            id: id, name: name, description: description,
            salesPrice: salesPrice, purchasePrice: purchasePrice, categoryIdList: categoryIdList, image: image
        };
        await ProductService.updateProduct(product,0);
        history.push(`/product`);
    }
    const checkBox = () => {

        const multiSelect = [];
        let info = false;
        categories.forEach(category => {
            categoryIdList.forEach(id => {
                if (id === category.id) {
                    multiSelect.push(
                        <div className="row col-md -12">
                            <label><input type="checkbox" onChange={() => changeMultiCate(category.id)} defaultChecked="true" />{category.name}</label>
                        </div>)
                    info = true;
                }
            });


            if (info === false) {

                multiSelect.push(
                    <div className="row col-md -12">
                        <label><input type="checkbox" onChange={() => changeMultiCate(category.id)} />{category.name}</label>
                    </div>)
                info = true;

            }
            info = false;

        });
        return multiSelect;
    }
    return (
        <div>
            {loading==true? <Loading/> :
           <div>
            <Header />
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Product</h3>
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
                                    <div className="row col-md-12">
                                        <label>Product categories :</label>
                                        <div className="checkbox  form-control" style={{ height: "6rem", width: "80rem", overflow: "auto" }}>
                                            {checkBox()}
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

                                                    <option key={media.id} selected={imageId == media.id} value={index}>{media.fileName}</option>
                                            )
                                        }
                                    </select>
                                    {showImage()}
                                </div>
                                <button className="btn btn-success" onClick={(e) => updateProduct(e)}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/product`)} >Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            }
        </div>);
}
export default EditProduct;