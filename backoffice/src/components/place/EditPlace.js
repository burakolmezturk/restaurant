import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import PlaceService from '../../services/PlaceService';
import Loading from '../loading/Loading';


const EditPlace = () => {

    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [place, setPlace] = useState({
        id: history.location.state?.id,
        name: '',
        tableCount: 0
    });

    const { id, name, tableCount } = place;

    const onChangeHandler = (e) => {
        setPlace({ ...place, [e.target.name]: e.target.value });
    }

    const editPlace = () => {
        PlaceService.updatePlace(place);
        history.push(`/place`);
    }
    useEffect(() => {
        console.log('girdi'+id);
        getPlaceById();
    }, [])

    async function getPlaceById() {
        const res = await PlaceService.getPlaceById(id);
       await setPlace({
            ...place,
            name: res.data.name,
            tableCount: res.data.tableCount
        });
        setLoading(false);
    }
    return (
        <div>
            {loading==true? <Loading/> :
           <div>
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


                                <button className="btn btn-success" onClick={() => editPlace()}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={() => history.push(`/place`)} >Cancel</button>
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
export default EditPlace;