import { useEffect, useState } from 'react';
import InfoService from '../../services/InfoService'
import { useHistory } from "react-router-dom";
import Header from '../header/Header';
import Loading from '../loading/Loading';

const Info = () => {

    const [loading, setLoading] = useState(true);
    const history = useHistory();
    const [info, setInfo] = useState([]);
    const [profile, setProfile] = useState([]);
    const [beanNames, setBeanNames] = useState([]);

    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }
        getData();

    }, []);
    async function getData() {
        const resInfo = await InfoService.getInfo();
        setInfo(resInfo.data);
        const resProfile = await InfoService.getProfileInfo();
        setProfile(resProfile.data);
        const resBeans = await InfoService.getBeanNames();
        setBeanNames(resBeans.data);
        setLoading(false);

    }

    return (
        <>
            {loading == true ? <Loading /> :
                <div>
                    <Header />
                    <div className="container">
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
                                        info.map(
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
                        <h2 className="text-center">Profile Info</h2>
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
                                        profile.map(
                                            profile =>
                                                <tr >
                                                    <td >{profile.key}</td>
                                                    <td >{profile.value}</td>

                                                </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>
                        <h2 className="text-center">Beans Name List</h2>
                        <div className="row" style={{ overflow: "auto", height: "35rem" }}>
                            <table className="table table-striped table bordered" >
                                <thead>
                                    <tr>
                                        <th>Bean Name</th>


                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        beanNames.map(
                                            beanName =>
                                                <tr >
                                                    <td >{beanName}</td>
                                                </tr>
                                        )
                                    }
                                </tbody>
                            </table>
                        </div>

                    </div>
                </div>
            }
        </>
    )
}
export default Info;