import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import CategoryService from '../../services/CategoryService';
import MediaService from '../../services/MediaService';
import Header from '../header/Header';
const CreateCategory = () => {

    const history = useHistory();

    const [category, setCategory] = useState({
        name: '',
        description: '',
        image: []
    });

    const [medias, setMedias] = useState([]);

    const { name, description, image } = category;

    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }
        
        getMedias();

    }, []);
    
    async function getMedias() {
        
        const res = await MediaService.getMedias();

        await setMedias(res.data);

        setCategory({ ...category, image: res.data[0] });

    }

    const onChangeHandler = (e) => {
        setCategory({ ...category, [e.target.name]: e.target.value })
    }
   
    const onChangeImage = (e) => {
        setCategory({ ...category, image: medias[e.target.value] });
    }

    const saveCategory = async (e) => {
        e.preventDefault();
        await CategoryService.createCategory(category);
        history.push(`/category`);
    }

    const showImage =  () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }
    return (

        <div>
            <Header />
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add Category</h3>
                        <div className="card-body">
                            <form>

                                <div className="form-group">
                                    <label>Category Name :</label>
                                    <input placeholder="Category Name" name="name" className="form-control"
                                        value={name} onChange={onChangeHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Category Description :</label>
                                    <input placeholder="Category Description" name="description" className="form-control"
                                        value={description} onChange={onChangeHandler} />
                                </div>

                                <div className="form-group">
                                    <label>Category Image :</label>
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
                                <button className="btn btn-success" onClick={(e) => saveCategory(e)}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/category`)} >Cancel</button>
                            </form>
                        </div>

                    </div>

                </div>


            </div>
        </div>)
}
export default CreateCategory;