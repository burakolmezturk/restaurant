import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import CategoryService from '../../services/CategoryService';
import MediaService from '../../services/MediaService';
import Header from '../header/Header';
import Loading from '../loading/Loading';

const ListCategory = () => {
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [categories, setCategories] = useState([]);

    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }
        getCategories();
    },[]);

    async function getCategories() {
        const res = await CategoryService.getCategory();
        await setCategories(res.data);
        setLoading(false);


    }
    const deleteCategory = (categoryId) => {
        setCategories(categories.filter(category => category.id !== categoryId));

    }
    const editCategory = (id, imageId) => {
        history.push({
            pathname: '/update-category',
            state: {
                id: id,
                imageId: imageId

            },
        });

    }

    return (
        
        <div>
            {loading==true? <Loading/> :
           <div>
            <Header />
            <div className="container">
                <h2 className="text-center">Category List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={() => history.push(`add-category`)}>Add Category</button>
                    </div>
                    <div className="row"  style={{ overflow: "auto", height: "35rem" }}>
                    <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Category Name</th>
                                <th>Category Description</th>
                                <th>Category Image</th>
                                <th>Actions</th>
                            </tr>

                        </thead>
                        <tbody>
                            {
                                categories.map(
                                    category =>
                                        <tr key={category.id} >
                                            <td >{category.name}</td>
                                            <td >{category.description}</td>
                                            <td><img src={'data:image/png;base64,' + category.image.fileContent} style={{ borderRadius: "10px" }} width="50" /></td>
                                            <td >
                                                <button onClick={() => editCategory(category.id, category.image.id)} className="btn btn-info">Edit</button>
                                                <button style={{ marginLeft: "10px" }} onClick={() => deleteCategory(category.id)} className="btn btn-danger" >Delete</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                </div>
            </div>
            }
        </div>)
}
export default ListCategory;