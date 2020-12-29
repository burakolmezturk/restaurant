import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import MediaService from '../../services/MediaService';
import PlaceService from '../../services/PlaceService';
import Loading from '../loading/Loading';


const EditPlace = () => {

    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [place, setPlace] = useState({
        id: history.location.state?.id,
        name: '',
        tableCount: 0,
        imageId: history.location.state?.imageId,
        image: []
    });

    const { id, name, tableCount, image, imageId } = place;
    const [medias, setMedias] = useState([]);

    const onChangeHandler = (e) => {
        setPlace({ ...place, [e.target.name]: e.target.value });
    }

    const editPlace = () => {
        PlaceService.updatePlace(place);
        history.push(`/place`);
    }
    useEffect(() => {

        getData();

    }, []);

    const onChangeImage = (e) => {
        setPlace({ ...place, image: medias[e.target.value] });
    }

    const getMedias = () => {
        return (
            <div className="form-group">
                <label>Place Image :</label>
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
        )
    }

    const showImage = () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }

    async function getData() {

        const res = await PlaceService.getPlaceById(id);
        const resMedias = await MediaService.getMedias();

        await setMedias(resMedias.data);
        await setPlace({
            ...place,
            name: res.data.name,
            tableCount: res.data.tableCount,
            image: res.data.image,
            imageId: history.location.state?.imageId
        });
        setLoading(false);

    }
    if (loading == true) {
        return (
            <Loading />
        )
    }

    return (

        <div className="container">
            <div className="row">
                <div className="card col-md-6 offset-md-3 offset-md-3">
                    <h3 className="text-canter">Edit Place</h3>
                    <div className="card-body">
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
                            {getMedias()}
                            <button className="btn btn-success" onClick={() => editPlace()}>Save</button>
                            <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/place`)} >Cancel</button>
                        </form>
                    </div>

                </div>

            </div>
        </div>


    )
}
export default EditPlace;