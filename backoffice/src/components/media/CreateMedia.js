import { useEffect, useState, useRef } from 'react';
import MediaService from '../../services/MediaService';
import Header from '../header/Header';
import Loading from '../loading/Loading';

const CreateMedia = () => {

    const ref = useRef();
    const [loading, setLoading] = useState(true);
    const [media, setMedia] = useState({
        name: '', selectedFile: null, medias: []
    })

    const { name, selectedFile, medias } = media;

    const uploadMedia = async (e) => {

        const data = new FormData();

        data.append('file', selectedFile);
        data.append('fileName', name);

        await MediaService.createMedia(data).then(res => {
            if (res.status = '200') {
                ref.current.value = '';
                setMedia({ ...media, selectedFile: null, name: '' });
            }
        });



    }

    const onNameChange = (e) => {

        setMedia({ ...media, name: e.target.value });

    }
    const onFileChange = (e) => {

        setMedia({ ...media, selectedFile: e.target.files[0] });
    }

    useEffect(() => {

        getMedias();

    }, [selectedFile]);

    async function getMedias() {
        const res = await MediaService.getMedias();
        setMedia({ ...media, medias: res.data });
        setLoading(false);
    }

    return (
        <>
            {loading == true ? <Loading /> :
                <div>
                    <Header />
                    <div className="container">
                        <div className="row">
                            <div className="col-4">
                                <div className="card" style={{ height: "40rem" }}>
                                    <h4 style={{ paddingLeft: 20 }}>Media Add</h4>
                                    <div className="card-body">

                                        <div className="form-group">
                                            <label>File :</label>
                                            <input type="file" name="selectedFile" ref={ref} style={{ paddingTop: 20 }} onChange={(e) => onFileChange(e)} />
                                        </div>
                                        <div className="form-group">
                                            <label>File Name :</label>
                                            <input placeholder="File Name" name="name" className="form-control"
                                                value={name} onChange={onNameChange} />
                                        </div>
                                        <button className="btn btn-success" onClick={uploadMedia}>Save</button>
                                    </div>
                                </div>
                            </div>
                            <div className="col-8">
                                <div className="card" style={{ height: "40rem", overflow: "auto" }}>
                                    <h4 style={{ paddingLeft: 20 }}>Media List</h4>
                                    <div className="row">

                                        {
                                            medias.map(
                                                media =>
                                                    <div className="card-body col-4">
                                                        <img src={'data:image/png;base64,' + media.fileContent} width="150" />
                                                    </div>
                                            )
                                        }

                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            }
        </>
    )

}
export default CreateMedia;