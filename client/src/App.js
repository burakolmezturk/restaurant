
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import './App.css';
import CartPageComponent from './components/CartPageComponent';
import HomePageComponent from './components/HomePageComponent';
import ListPlaceComponent from './components/ListPlaceComponent';
import ListTableComponent from './components/ListTableComponent';
import LoginComponent from './components/LoginComponent';
import ListCustomer from './components/customer/ListCustomer';
import CreateCustomer from './components/customer/CreateCustomer';
import EditCustomer from './components/customer/EditCustomer';
import ActionCustomer from './components/customer/ActionCustomer';


function App() {
  return (
    <div>
      <Router>        
           <Switch> 
                  <Route path ="/home" exact component ={HomePageComponent}></Route>
                  <Route path ="/" exact component ={LoginComponent}></Route>
                  <Route path ="/cart" exact component ={CartPageComponent}></Route>
                  <Route path ="/table" exact component ={ListTableComponent}></Route>
                  <Route path ="/place" exact component ={ListPlaceComponent}></Route>
                  <Route path ="/customers" exact component ={ListCustomer}></Route>
                  <Route path ="/add-customer" exact component ={CreateCustomer}></Route>
                  <Route path ="/update-customer" exact component ={EditCustomer}></Route>
                  <Route path ="/action-customer" exact component ={ActionCustomer}></Route>                 
            </Switch>      
      </Router>
  </div>
  );
}

export default App;
