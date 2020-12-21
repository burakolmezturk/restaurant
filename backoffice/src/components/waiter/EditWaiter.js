import { useEffect, useState, useRef } from 'react';
import { useHistory } from "react-router-dom";
import MediaService from '../../services/MediaService';
import WaiterService from '../../services/WaiterService';
import HeaderComponent from '../header/Header';
import Loading from '../loading/Loading';

const EditWaiter = () => {
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [waiter, setWaiter] = useState({
        id: history.location.state?.id,
        name: '',
        email: '',
        phone: '',
        age: 0,
        imageId: history.location.state?.imageId,
        medias: [],
        image: []
    });

    const [medias, setMedias] = useState([]);

    const { id, name, email, phone, age, imageId, image } = waiter;


    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }

        getData(id);

    }, []);

    async function getData(id) {

        const media = await MediaService.getMedias();
        if (media.status == '200') {
            setMedias(media.data);
        }

        const res = await WaiterService.getWaiterById(id);
        if (res.status == '200') {
            const info = res.data;
            setWaiter({
                ...waiter,
                id: info.id,
                name: info.name,
                email: info.email,
                phone: info.phone,
                age: info.age,
                image: info.image,

            });

        }
        setLoading(false);

    }
    const showImage = () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }

    async function updateWaiter(e) {
        e.preventDefault();

        let waiter = { id: id, name: name, email: email, phone: phone, age: age, image: image };

        await WaiterService.updateWaiter(waiter)
        history.push('/waiter');


    }
    const cancel = (e) => {
        e.preventDefault();
        history.push('/waiter')
    }

    const onChangeInputs = (e) => {
        setWaiter({ ...waiter, [e.target.name]: e.target.value });
    }

    const onChangeImage = (e) => {
        setWaiter({ ...waiter, image: medias[e.target.value] });
    }

    return (
        <div>
            {loading == true ? <Loading /> :
                <div>
                    <HeaderComponent />
                    <div className="container">
                        <div className="row">
                            <div className="card col-md-6 offset-md-3 offset-md-3">
                                <h3 className="text-canter">Edit Waiter</h3>
                                <div className="card-body">
                                    <form>
                                        <div className="form-group">
                                            <label>Waiter Name :</label>
                                            <input placeholder="Name" name="name" className="form-control"
                                                value={name} onChange={onChangeInputs} />
                                        </div>
                                        <div className="form-group">
                                            <label>Waiter Age :</label>
                                            <input placeholder="Age" name="age" className="form-control"
                                                value={age} onChange={onChangeInputs} type="number" />
                                        </div>
                                        <div className="form-group">
                                            <label>Waiter Email :</label>
                                            <input placeholder="Email" name="email" className="form-control"
                                                value={email} onChange={onChangeInputs} type="email" />
                                        </div>
                                        <div className="form-group">
                                            <label>Waiter Phone :</label>
                                            <input placeholder="name" name="name" className="form-control"
                                                value={phone} onChange={onChangeInputs} type="number" />
                                        </div>
                                        <div className="form-group">
                                            <label>Waiter Image :</label>
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
                                        <button type="submit" className="btn btn-success" onClick={(e) => updateWaiter(e)}>Save</button>
                                        <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={(e) => cancel(e)} >Cancel</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            }
        </div>
    )
}
export default EditWaiter;
