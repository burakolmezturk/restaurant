import { useEffect, useState,useContext } from 'react';
import { useHistory } from "react-router-dom";
import ProductService from '../../services/ProductService';
import Header from '../header/Header';
import Loading from '../loading/Loading';
import { Context } from '../../contextApi/ContextApi';

const ListProduct = () => {
    const { user } = useContext(Context);
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [products, setProducts] = useState([]);


    useEffect(() => {
        
        if (user.username == '' && user.password == '') {
            history.push('');
        }

        getProducts();
    },[]);
    async function getProducts() {
        
        const res = await ProductService.getProduct();
        await setProducts(res.data);
        setLoading(false);
        
    }

    const editProduct = (id, catId, imageId) => {
        history.push({
            pathname: '/update-product',
            state: {
                id: id,
                catId: catId,
                imageId: imageId
            }
        });
    }

    const deleteProduct = async (id) => {
        await ProductService.deleteProduct(id);
        setProducts(products.filter(products => products.id !== id));
    }

    return (
        <div>
            {loading == true ? <Loading /> :
                <div>
                    <Header />
                    <div className="container">
                        <h2 className="text-center">Product List</h2>
                        <div className="row">
                            <button className="btn btn-primary" onClick={() => history.push(`add-product`)}>Add Product</button>
                            <button style={{ marginLeft: "6px" }} className="btn btn-primary" >All Products</button>
                            <table className="table table-striped table-bordered">
                                <thead>
                                    <tr>
                                        <th>Category</th>
                                        <th>Name</th>
                                        <th>Description</th>
                                        <th>Price</th>
                                        <th>Image</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                {
                                    products.map(
                                        product =>
                                            <tbody>
                                                {
                                                    <tr key={product.id}>
                                                        <td >
                                                            {
                                                            product.categories.map( category=>
                                                                <a  href="#">{category.name} </a>
                                                                )
                                                            }
                                                        </td>
                                                        <td>{product.name}</td>
                                                        <td>{product.description}</td>
                                                        <td>{product.salesPrice}</td>
                                                        <td style={{ textAlign: "center" }} ><img src={'data:image/png;base64,' + product.image.fileContent} style={{ borderRadius: "10px" }} width="50" /></td>
                                                        <td>
                                                            <button onClick={() => editProduct(product.id, product.categoryId, product.image.id)}
                                                                className="btn btn-info"> Update
                                                                </button>
                                                            <button style={{ marginLeft: "6px" }} onClick={() => deleteProduct(product.id)}
                                                                className="btn btn-danger"> Delete
                                                                </button>
                                                        </td>
                                                    </tr>
                                                }
                                            </tbody>
                                    )
                                }
                            </table>
                        </div>
                    </div>
                </div>
            }
        </div>);
}
export default ListProduct;