import { useEffect, useState } from 'react';
import { useHistory } from "react-router-dom";
import WaiterService from '../../services/WaiterService';
import Header from '../header/Header';
import Loading from '../loading/Loading';

const ListWaiter = () => {
    const history = useHistory();
    const [loading, setLoading] = useState(true);
    const [waiters, setWaiters] = useState([]);

    const addWaiter = () => {
        history.push('add-waiter')
    }

    const deleteWaiter = async (waiterId) => {
        await WaiterService.deleteWaiter(waiterId)
        setWaiters(waiters.filter(waiter => waiter.id !== waiterId));

    }
    const editWaiter = (id, imageId) => {
        history.push({
            pathname: '/update-waiter',
            state: {
                id: id,
                imageId: imageId
            }
        });
    }

    useEffect(() => {
        if (localStorage.getItem("username") == null && localStorage.getItem("password") == null) {
            history.push('')
        }
        getData();
    }, []);
    async function getData() {
        const res = await WaiterService.getWaiters();
        await setWaiters(res.data);
        setLoading(false);
    }

    return (
        <>
            {loading == true ? <Loading/> :
                <div>
                    <Header />
                    <div className="container">
                        <h2 className="text-center">Waiter List</h2>
                        <div className="row">
                            <button className="btn btn-primary" onClick={addWaiter}>Add Waiter</button>
                            <table className="table table-striped table bordered">
                                <thead>
                                    <tr>
                                        <th>Waiter Name</th>
                                        <th>Waiter Age</th>
                                        <th>Waiter Email</th>
                                        <th>Waiter Phone</th>
                                        <th>Waiter Image</th>
                                        <th>Actions</th>
                                    </tr>

                                </thead>
                                <tbody>
                                    {
                                        waiters.map(
                                            waiter =>
                                                <tr key={waiter.id} >
                                                    <td >{waiter.name}</td>
                                                    <td >{waiter.age}</td>
                                                    <td >{waiter.email}</td>
                                                    <td >{waiter.phone}</td>
                                                    <td><img src={'data:image/png;base64,' + waiter.image.fileContent} style={{ borderRadius: "10px" }} width="50" /></td>
                                                    <td >
                                                        <button onClick={() => editWaiter(waiter.id, waiter.image.id)} className="btn btn-info">Edit</button>
                                                        <button style={{ marginLeft: "10px" }} onClick={() => deleteWaiter(waiter.id)} className="btn btn-danger" >Delete</button>
                                                    </td>
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
export default ListWaiter;