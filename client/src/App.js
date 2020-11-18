
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import './App.css';
import HomePageController from './components/HomePageController';


function App() {
  return (
    <div>
      <Router>    
        <div className="container">          
          <div className="container">
           
           <Switch> 
                  <Route path ="/" exact component ={HomePageController}></Route>
                  <Route path ="/home" exact component ={HomePageController}></Route>
                  
            </Switch>
          
        </div>
      </div>
      </Router>
  </div>
  );
}

export default App;
