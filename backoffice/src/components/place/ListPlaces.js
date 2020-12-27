import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import PlaceService from '../../services/PlaceService';
import Header from '../header/Header';
import Loading from '../loading/Loading';
const ListPlace = () => {

    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [places, setPlaces] = useState([]);

    useEffect(() => {
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            history.push('')
        }
        getPlaces();
    },[])

    async function getPlaces() {
        const res = await PlaceService.getPlaces();
        
        await setPlaces(res.data);
        setLoading(false);
    }
   
    const editPlace = (id,imageId) => {
        history.push({
            pathname: '/update-place',
            state: {
                id: id,
                imageId:imageId
            }
        });
    }
    async function deletePlace(placeId) {
     const res =  await PlaceService.deletePlace(placeId);
     if (res.status=='200'){
         setPlaces(places.filter(place=>place.id!==placeId));
     }
    }

    const getTable = () =>{
        return(
        <table className="table table-striped table bordered">
        <thead>
            <tr>
                <th>Place Name</th>
                <th>Table Count</th>
                <th>Actions</th>
                <th>Image</th>
            </tr>

        </thead>

        <tbody>
            {
                places.map(
                    place =>
                        <tr key={place.id} >
                            <td >{place.name}</td>
                            <td >{place.tableCount}</td>
                            <td><img src={'data:image/png;base64,' + place.image.fileContent} style={{ borderRadius: "10px" }} width="50" /></td>
                            <td >
                                <button onClick={() => editPlace(place.id,place.image.id)} className="btn btn-info">Edit</button>
                                <button style={{ marginLeft: "10px" }} onClick={() => deletePlace(place.id)} className="btn btn-danger" >Delete</button>
                            </td>
                        </tr>
                )
            }

        </tbody>
    </table>)
    }
    
    if(loading==true){
        return (<Loading/>);
    }

    return (
           <div>
            <Header />
            <div className="container">
                <h2 className="text-center">Place List</h2>
                <div className="row">
                    <button className="btn btn-primary" onClick={()=>history.push(`add-place`)}>Add Place</button>
                     {getTable()}
                </div>
            </div>
            </div>
    )
}
export default ListPlace;