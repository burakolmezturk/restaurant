
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import './App.css';
import HeaderComponent from './components/HeaderComponent';
import LoginComponent from './components/loginComponent/LoginComponent';
import CreateProductComponent from './components/productComponent/CreateProductComponent';
import ListProductComponent from './components/productComponent/ListProductComponent';
import UpdateProductComponent from './components/productComponent/UpdateProductComponent';
import CreateUserComponent from './components/UserComponent/CreateUserComponent';
import ListUserComponent from './components/UserComponent/ListUserComponent';
import UpdateUserComponent from './components/UserComponent/UpdateUserComponent';
import LoginService from './services/LoginService';

function App() {
  return (
    
    <div>
        <HeaderComponent/> 
        <div className="container">
         <Router>                  
           <Switch> 
                  <Route path ="/" exact component ={LoginComponent}></Route>
                  <Route path ="/home" exact component ={ListProductComponent}></Route>
                  <Route path ="/product" component ={ListProductComponent}></Route>
                  <Route path ="/add-product" component ={CreateProductComponent}></Route>
                  <Route path ="/update-product/:id" component ={UpdateProductComponent}></Route>
                  <Route path ="/user" component ={ListUserComponent}></Route>
                  <Route path ="/add-user" component ={CreateUserComponent}></Route>
                  <Route path ="/update-user/:id" component ={UpdateUserComponent}></Route>
                  
            </Switch>
          

      </Router>
      </div> 
  </div>
  );
}

export default App;
