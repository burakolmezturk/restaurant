
import {BrowserRouter as Router,Route,Switch} from 'react-router-dom';
import './App.css';
import HomePageController from './components/HomePageController';
import CreateProductComponent from './components/productComponent/CreateProductComponent';
import ListProductComponent from './components/productComponent/ListProductComponent';
import UpdateProductComponent from './components/productComponent/UpdateProductComponent';

function App() {
  return (
    <div>
      <Router>    
        <div className="container">          
          <div className="container">
           
           <Switch> 
                  <Route path ="/" exact component ={HomePageController}></Route>
                  <Route path ="/home" exact component ={HomePageController}></Route>
                  <Route path ="/product" component ={ListProductComponent}></Route>
                  <Route path ="/add-product" component ={CreateProductComponent}></Route>
                  <Route path ="/update-product/:id" component ={UpdateProductComponent}></Route>
                  
            </Switch>
          
        </div>
      </div>
      </Router>
  </div>
  );
}

export default App;
