
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import './App.css';
import CartPageComponent from './components/CartPageComponent';
import HomePageComponent from './components/HomePageComponent';
import ListPlaceComponent from './components/ListPlaceComponent';
import ListTableComponent from './components/ListTableComponent';
import LoginComponent from './components/LoginComponent';


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
                  
            </Switch>
          


      </Router>
  </div>
  );
}

export default App;
