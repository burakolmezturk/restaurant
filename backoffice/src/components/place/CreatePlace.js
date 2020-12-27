import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import MediaService from '../../services/MediaService';
import PlaceService from '../../services/PlaceService';
import Header from '../header/Header';

const CreatePlace = () => {
    const history = useHistory();
    const [place, setPlace] = useState({
        name: '', tableCount: 0,image : ''
    });

    const [medias,setMedias] = useState([]);
    const { name, tableCount,image } = place;
   
    useEffect(()=>{
        getMedias();
    },[]);

    const onChangeHandler=(e)=> {
        setPlace({ ...place, [e.target.name]: e.target.value });
    }

    const savePlace=()=> {
        PlaceService.createPlace(place);
        history.push(`/place`);
    }
    
    const onChangeImage = (e) => {
        setPlace({ ...place, image: medias[e.target.value] });
    }

    async function getMedias() {
        
        const res = await MediaService.getMedias();

        await setMedias(res.data);

        setPlace({ ...place, image: res.data[0] });

    }

    const showImage =  () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }

    const getListBox = ()=>{
        return(
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

    </div>)
    }

    const getForm = () =>{
        return(
        <form>
            <div className="form-group">
                <label>Place Name :</label>
                <input placeholder="Place Name" name="name" className="form-control"
                    value={name} onChange={onChangeHandler} />
            </div>
            <div className="form-group">
                <label>Table Count :</label>
                <input placeholder="Table Count" name="tableCount" className="form-control"
                    value={tableCount} onChange={onChangeHandler} />
            </div>
            {getListBox()}
            <button className="btn btn-success" onClick={()=>savePlace()}>Save</button>
            <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={()=>history.push(`/place`)} >Cancel</button>
        </form>)
    }

    return (
        <div>
            <Header />
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add Category</h3>
                        <div className="card-body">
                            {getForm()}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        )
}
export default CreatePlace;