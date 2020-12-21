import { useEffect, useState} from 'react';
import { useHistory } from "react-router-dom";
import MediaService from '../../services/MediaService';
import WaiterService from '../../services/WaiterService';
import HeaderComponent from '../header/Header';
const CreateWaite = () => {

    const history = useHistory();

    const [waiter, setWaiter] = useState({
        name: '',
        email: '',
        phone: '',
        age: 0,
        medias: [],
        image: []
    });

    const { name, email, phone, age, medias, image } = waiter;

    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }

        getImages();

    }, []);

    async function getImages() {
        const res = await MediaService.getMedias()
        await setWaiter({ ...waiter, medias: res.data, image: res.data[0] });

    }

    const showImage = () => {

        const html = [];
        const images = image;

        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;

    }

    const cancel = () => {
        history.push('/waiter')
    }

    const onChange = (e) => {
        setWaiter({ ...waiter, [e.target.name]: e.target.value });
    }
    const onChangeImage = (e) => {
        setWaiter({ ...waiter, image: medias[e.target.value] });
    }

    const saveWaiter = async (e) => {
        
        e.preventDefault();
        await WaiterService.createWaiter(waiter);
        history.push('/waiter');
        
    }
    return (
        <>

        <div>
        <HeaderComponent/>
            <div className="container">
                <div className="row">
                    <div className="card col-md-6 offset-md-3 offset-md-3">
                        <h3 className="text-canter">Add Waiter</h3>
                        <div className="card-body">
                            <form>

                                <div className="form-group">
                                    <label>Waiter Name :</label>
                                    <input placeholder="Waiter Name" name="name" className="form-control"
                                        value={name} onChange={onChange} />
                                </div>
                                <div className="form-group">
                                    <label>Waiter Age :</label>
                                    <input placeholder="Waiter Age" name="age" className="form-control"
                                        value={age} onChange={onChange} type="number" />
                                </div>
                                <div className="form-group">
                                    <label>Waiter Email :</label>
                                    <input placeholder="Waiter Email" name="email" className="form-control"
                                        value={email} onChange={onChange} type="email" />
                                </div>
                                <div className="form-group">
                                    <label>Waiter Phone :</label>
                                    <input placeholder="Waiter Phone" name="phone" className="form-control"
                                        value={phone} onChange={onChange} type="number" />
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
                                <button className="btn btn-success" onClick={(e)=>saveWaiter(e)}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={()=>cancel} >Cancel</button>
                            </form>
                        </div>

                    </div>

                </div>


            </div>
        </div>
        </>)
}
export default CreateWaite;