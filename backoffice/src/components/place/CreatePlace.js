import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import PlaceService from '../../services/PlaceService';
import Header from '../header/Header';

const CreatePlace = () => {
    const history = useHistory();
    const [place, setPlace] = useState({
        name: '', tableCount: 0
    })
    const { name, tableCount } = place;

    const onChangeHandler=(e)=> {
        setPlace({ ...place, [e.target.name]: e.target.value });
    }
    const savePlace=()=> {
        PlaceService.createPlace(place);
        history.push(`/place`);
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
                                    <label>Place Name :</label>
                                    <input placeholder="Place Name" name="name" className="form-control"
                                        value={name} onChange={onChangeHandler} />
                                </div>
                                <div className="form-group">
                                    <label>Table Count :</label>
                                    <input placeholder="Table Count" name="tableCount" className="form-control"
                                        value={tableCount} onChange={onChangeHandler} />
                                </div>


                                <button className="btn btn-success" onClick={()=>savePlace()}>Save</button>
                                <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={()=>history.push(`/place`)} >Cancel</button>
                            </form>
                        </div>

                    </div>

                </div>


            </div>
        </div>)
}
export default CreatePlace;