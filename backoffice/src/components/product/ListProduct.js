import { useEffect, useState, useContext } from 'react';
import { useHistory } from "react-router-dom";
import ProductService from '../../services/ProductService';
import Header from '../header/Header';
import Loading from '../loading/Loading';
import { Context } from '../../contextApi/ContextApi';
import Pagination from "react-js-pagination";
const ListProduct = () => {
    const { user } = useContext(Context);
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [products, setProducts] = useState([]);
    const [activePage, setActivePage] = useState(0);
    const [totalElemnts, setTotalElemnts] = useState();

    useEffect(() => {

        if (user.username == '' && user.password == '') {
            history.push('');
        }

        getProducts();
    }, []);
    async function getProducts() {
        console.log(activePage);
        const res = await ProductService.getProductPage(activePage);
        await setTotalElemnts(res.data.totalElements);
        await setProducts(res.data.content);
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
    const onChangePage = async (e) => {

        await setActivePage(e - 1);
        await setLoading(true);
        const res = await ProductService.getProductPage(e - 1);
        await setProducts(res.data.content);
        setLoading(false);

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
                        </div>
                        <div className="row">
                            <table className="table table-striped table-bordered" >
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
                                                                product.categories.map(category =>
                                                                    <a href="#">{category.name} </a>
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
                        <div className="row" style={{marginLeft:"20rem"}}>
                        <Pagination 
                            itemClass="page-item"
                            linkClass="page-link"
                            activePage={activePage + 1}
                            itemsCountPerPage={10}
                            totalItemsCount={totalElemnts}
                            pageRangeDisplayed={10}
                            onChange={onChangePage} />
                            </div>
                    </div>

                </div>
            }

        </div>);
}
export default ListProduct;