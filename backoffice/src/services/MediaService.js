import axios from 'axios';

const MEDIA_API_BASE_URL = "http://localhost:8082/media";
class MediaService{

    getMedias(){
        return axios.get(MEDIA_API_BASE_URL,{
            auth:{
                username:localStorage.getItem("username"),
                password:localStorage.getItem("password")
            }
        });
    }
    createMedia(media){
        return axios.post(MEDIA_API_BASE_URL,media,{
  
            auth:{
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }
        })
       
    }
  
}
export default new MediaService()