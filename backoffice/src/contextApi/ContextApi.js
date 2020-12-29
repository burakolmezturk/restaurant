import { createContext, useState } from 'react';
import { useHistory } from "react-router-dom";
import axios from 'axios';


export const Context = createContext();

const ContextProvider = (props) => {

    let history = useHistory();

    const [user, setUser] = useState({
        username: '', password: ''
    });
    const [language,setLanguage] = useState('en');
    const { username, password } = user;
    
    const login = async (username, password) => {
        const res = await axios.get("http://localhost:8080/user/login", {
            auth: {
                username: username,
                password: password
            }

        });
        if (res.status == '200') {

            setUser({ username: username, password:password});

            // history.push("/category");

        } else {
            console.log("hatalı giriş");
        }

        return res;
    }
    return (

        <Context.Provider value={{ user,language,setLanguage, login }}>
            {props.children}
        </Context.Provider>
    )

}
export default ContextProvider;