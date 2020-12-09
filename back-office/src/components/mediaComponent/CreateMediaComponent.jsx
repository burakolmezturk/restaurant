import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import MediaService from '../../services/MediaService';
import UserService from '../../services/UserService';

class CreateMediaComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            selectedFile: '',
            media: []
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeFileHandler = this.changeFileHandler.bind(this);

    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        this.getMedias();
    }
    getMedias() {
        MediaService.getMedias().then(res => {
            this.setState({ media: res.data });
        })
    }


    uploadMedia = async (e) => {


        const data = new FormData();

        data.append('file', this.state.selectedFile);
        data.append('fileName', this.state.name);



        await MediaService.createMedia(data);

        window.location.reload();

    }

    changeNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }
    changeFileHandler = (event) => {
        this.setState({ selectedFile: event.target.files[0] });
    }


    render() {
        return (

            <div className="container">
                <div className="row">
                    <div className="col-4">
                        <div className="card" style={{ height: "40rem" }}>
                            <h4 style={{ paddingLeft: 20 }}>Media Add</h4>
                            <div className="card-body">

                                <div className="form-group">
                                    <label>File :</label>
                                    <input type="file" name="file" style={{ paddingTop: 20 }} onChange={this.changeFileHandler} />
                                </div>
                                <div className="form-group">
                                    <label>File Name :</label>
                                    <input placeholder="File Name" name="name" className="form-control"
                                        value={this.state.name} onChange={this.changeNameHandler} />
                                </div>
                                <button className="btn btn-success" onClick={this.uploadMedia}>Save</button>
                            </div>
                        </div>
                    </div>
                    <div className="col-8">
                        <div className="card" style={{ height: "40rem", overflow: "auto" }}>
                            <h4 style={{ paddingLeft: 20 }}>Media List</h4>
                            <div className="row">

                                {
                                    this.state.media.map(
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

        );
    }
}

export default CreateMediaComponent;