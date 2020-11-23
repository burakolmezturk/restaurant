
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import './App.css';
import CartPageComponent from './components/CartPageComponent';
import LoginComponent from './components/LoginComponent';


function App() {
  return (
    <div>
      <Router>    


           
           <Switch> 
                  <Route path ="/" exact component ={LoginComponent}></Route>
                  <Route path ="/cart" exact component ={CartPageComponent}></Route>
                  
            </Switch>
          


      </Router>
  </div>
  );
}

export default App;
