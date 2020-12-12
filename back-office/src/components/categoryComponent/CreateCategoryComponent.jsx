import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import MediaService from '../../services/MediaService';
class CreateCategoryComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: '',
            description: '',
            medias: [],
            image:[]
        }
        this.changeNameHandler = this.changeNameHandler.bind(this);
        this.changeDescriptionHandler = this.changeDescriptionHandler.bind(this);
        this.changeImageSelect=this.changeImageSelect.bind(this);


    }
    componentDidMount() {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            this.props.history.push('')
        }
        
         this.getMedias();
        
    }

    saveCategory = (e) => {
        e.preventDefault();

        let category = { name: this.state.name, description: this.state.description, image: this.state.image };

        CategoryService.createCategory(category).then(res => {
            this.props.history.push('/category');

        })
    }
    showImage() {
        const html = [];
        const images = this.state.image
        html.push(<img src={'data:image/png;base64,' + images.fileContent} width="50" />)
        return html;
    }
    getMedias() {
        MediaService.getMedias().then(res => {
            this.setState({ medias: res.data });
            this.setState({image:this.state.medias[0]})
        })
    }
    cancel() {
        this.props.history.push('/category')
    }
    changeNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }
    changeDescriptionHandler = (event) => {
        this.setState({ description: event.target.value });
    }
    changeImageSelect=(event) =>{
        this.setState({image:this.state.medias[event.target.value]});
    }


    render() {
        return (
            <div>
                <div className="container">
                    <div className="row">
                        <div className="card col-md-6 offset-md-3 offset-md-3">
                            <h3 className="text-canter">Add Category</h3>
                            <div className="card-body">
                                <form>

                                    <div className="form-group">
                                        <label>Category Name :</label>
                                        <input placeholder="Category Name" name="name" className="form-control"
                                            value={this.state.name} onChange={this.changeNameHandler} />
                                    </div>
                                    <div className="form-group">
                                        <label>Category Description :</label>
                                        <input placeholder="Category Description" name="description" className="form-control"
                                            value={this.state.description} onChange={this.changeDescriptionHandler} />
                                    </div>

                                    <div className="form-group">
                                        <label>Category Image :</label>
                                        <select
                                            className="form-control" id="option" onChange={this.changeImageSelect} > 
                                            {
                                                this.state.medias.map(
                                                    (media,index) =>

                                                        <option key={media.id} value={index}>{media.fileName}</option>
                                                )
                                            }
                                        </select>
                                        {this.showImage()}

                                    </div>


                                    <button className="btn btn-success" onClick={this.saveCategory}>Save</button>
                                    <button style={{ marginLeft: "10px" }} className="btn btn-danger" onClick={this.cancel.bind(this)} >Cancel</button>
                                </form>
                            </div>

                        </div>

                    </div>


                </div>
            </div>
        );
    }
}

export default CreateCategoryComponent;