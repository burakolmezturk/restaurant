import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import MediaService from '../../services/MediaService';
import PlaceService from '../../services/PlaceService';
import Header from '../header/Header';
import { useFormik } from 'formik';

const validate = values => {
   
    const errors = {};
    if (!values.name) {
        console.log('geldi');
      errors.name = 'Required';
    } else if (values.name.length > 15) {
        console.log('geldi');
      errors.name = 'Must be 15 characters or less';
    }
  
    if (!values.tableCount) {
        console.log('geldi');
      errors.tableCount = 'Required';
    } else if (values.tableCount<1) {
        
      errors.tableCount = 'Must be big than 0';
      console.log(errors.tableCount);
    }
  
    return errors;
  };
const CreatePlace = () => {
    const history = useHistory();
    const formik = useFormik({
        initialValues: {
          name: '',
          tableCount: '',
          image: '',
        },
        validate,
        onSubmit: values => {
         savePlace(values);
        },
      });
    const [place, setPlace] = useState({
        name: '', tableCount: 0,image : ''
    });

    const [medias,setMedias] = useState([]);
    const { name, tableCount,image } = place;
   
    useEffect(()=>{
        getMedias();
    },[]);

    // const onChangeHandler=(e)=> {
    //     setPlace({ ...place, [e.target.name]: e.target.value });
    // }

    const savePlace=(place)=> {
        PlaceService.createPlace(place);
        history.push(`/place`);
    }
    
    const onChangeImage = (e) => {
        formik.values.image= medias[e.target.value];
        //setPlace({ ...place, image: medias[e.target.value] });
    }

    async function getMedias() {
        
        const res = await MediaService.getMedias();

        await setMedias(res.data);
        formik.values.image= res.data[0];
       // setPlace({ ...place, image: res.data[0] });

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
        <form onSubmit={formik.handleSubmit}>
            <div className="form-group">
                <label>Place Name :</label>
                <input placeholder="Place Name" name="name" className="form-control" id="name"
         type="text"
         onChange={formik.handleChange}
         value={formik.values.name}
                    // value={name} onChange={onChangeHandler} 
                    />
                    {formik.touched.name && formik.errors.name ? ( <div>{formik.errors.name}</div>) : null}
            </div>
            
            <div className="form-group">
                <label>Table Count :</label>
                <input placeholder="Table Count" name="tableCount" className="form-control"
                    // value={tableCount} onChange={onChangeHandler}
                    id="tableCount"
         type="number"
         onChange={formik.handleChange}
         value={formik.values.lastName}

                     />
            </div>
            {formik.touched.tableCount && formik.errors.tableCount ? (<div>{formik.errors.tableCount}</div>) : null}
            {getListBox()}
            <button type="submit" className="btn btn-success" >Save</button>
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