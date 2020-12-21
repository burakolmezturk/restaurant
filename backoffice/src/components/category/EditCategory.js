import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import CategoryService from '../../services/CategoryService';
import MediaService from '../../services/MediaService';
import Header from '../header/Header';
import Loading from '../loading/Loading';

const EditCategory = () => {
    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const [category, setCategory] = useState({

        id: history.location.state?.id,
        name: '',
        description: '',
        imageId: history.location.state?.imageId,
        image: []

    });

    const { id, name, description, image, imageId } = category;

    const [medias, setMedias] = useState([]);

    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }   
        getData();

    }, []);

    async function getData(){

        const res = await CategoryService.getCategoryById(id);
        const resMedias = await MediaService.getMedias();
       
        await setMedias(resMedias.data);
        await setCategory({
            ...category,
            name: res.data.name,
            description: res.data.description,
            image: res.data.image,
            imageId: history.location.state?.imageId
        });
        setLoading(false);

              
    }
 
    const showImage = () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }
    const onChangeHandler = (e) => {

        setCategory({ ...category, [e.target.name]: e.target.value })
    }

    const onChangeImage = (e) => {
        setCategory({ ...category, image: medias[e.target.value] });
    }

    const updateCategory = async (e) => {
        e.preventDefault();
        await CategoryService.updateCategory(category);
        history.push(`/category`);
    }

    return (
        <div>
            {loading==true? <Loading/> :
           <div>
            <Header/>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Edit Category</h3>
                        <div className="card-body">
                            <form>
                                <div className="form-group">
                                    <label>Name :</label>
                                    <input placeholder="Name" name="name" className="form-control"
                                        value={name} onChange={onChangeHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Description :</label>
                                    <input placeholder="Category" name="description" className="form-control"
                                        value={description} onChange={onChangeHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Category Image :</label>
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

                                <button className="btn btn-success" onClick={(e) => updateCategory(e)}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/category`)} >Cancel</button>
                            </form>
                        </div>

                    </div>

                </div>

                </div>
            </div>
            }
        </div>)
}
export default EditCategory;