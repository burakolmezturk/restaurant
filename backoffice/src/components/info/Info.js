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
        await setProfile(resProfile.data);
        setLoading(false);
        
    }

    return (
        <>
          {loading==true? <Loading/> :
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
                    <h2 className="text-center">Profile Info</h2>
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
            </div>
            </div>
            }
        </>
        )
}
export default Info;