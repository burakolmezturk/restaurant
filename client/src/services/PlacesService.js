import axios from 'axios';

const PLACE_API_BASE_URL = "http://localhost:8080/place/";
class PlaceService{

    getPlaces(){
        return axios.get(PLACE_API_BASE_URL+"list",{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createPlace(place){
        return axios.post(PLACE_API_BASE_URL+"add",place,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    getPlaceById(placeId){        
        return axios.get(PLACE_API_BASE_URL+placeId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    updatePlace(place){
        return axios.put(PLACE_API_BASE_URL+"edit",place,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    deletePlace(placeId){
        return axios.delete(PLACE_API_BASE_URL+"delete/"+placeId,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }

}
export default new PlaceService()