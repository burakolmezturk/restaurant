
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import './App.css';
import CartPageComponent from './components/CartPageComponent';
import HomePageComponent from './components/HomePageComponent';
import LoginComponent from './components/LoginComponent';


function App() {
  return (
    <div>
      <Router>    


           
           <Switch> 
                  <Route path ="/home" exact component ={HomePageComponent}></Route>
                  <Route path ="/" exact component ={LoginComponent}></Route>
                  <Route path ="/cart" exact component ={CartPageComponent}></Route>
                  
            </Switch>
          


      </Router>
  </div>
  );
}

export default App;
