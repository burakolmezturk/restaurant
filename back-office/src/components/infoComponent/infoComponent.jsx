import React, { Component } from 'react';
import CategoryService from '../../services/CategoryService';
import InfoServiceCopy from '../../services/InfoService copy';


class infoComponent extends Component {
    constructor(props){
        super(props)
        this.state={
            info:[]
        }
        
        
    }
    componentDidMount(){
        if(localStorage.getItem("username")==null && localStorage.getItem("password")==null){
            this.props.history.push('')
        }
        InfoServiceCopy.getInfo().then((res)=>
         this.setState({info:res.data}))
    }
 

    render() {
        return (
            <div>
            <h2 className="text-center">Properties Info</h2>
               <div className="row">
                   
                   <table className="table table-striped table bordered">
                        <thead>
                            <tr>
                                <th>Key</th>
                                <th>Value</th>
                              
                            </tr>

                        </thead>

                        <tbody>
                            {
                                this.state.info.map(
                                   info =>
                                   <tr >
                                       <td >{info.key}</td>
                                       <td >{info.value}</td>
                                       
                                   </tr>   
                                )
                            }

                        </tbody>
                   </table>
                   
               </div>
            </div>
               
        );
    }
}

export default infoComponent;