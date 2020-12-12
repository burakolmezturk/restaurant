
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import React, { useState } from 'react';
import './App.css';
import HeaderComponent from './components/HeaderComponent';
import LoginComponent from './components/loginComponent/LoginComponent';
import CreateProductComponent from './components/productComponent/CreateProductComponent';
import ListProductComponent from './components/productComponent/ListProductComponent';
import UpdateProductComponent from './components/productComponent/UpdateProductComponent';
import CreateUserComponent from './components/UserComponent/CreateUserComponent';
import ListUserComponent from './components/UserComponent/ListUserComponent';
import ListPlaceComponent from './components/placeComponent/ListPlaceComponent';
import CreatePlaceComponent from './components/placeComponent/CreatePlaceComponent';
import UpdatePlaceComponent from './components/placeComponent/UpdatePlaceComponent';
import UpdateUserComponent from './components/UserComponent/UpdateUserComponent';
import ListCategoryComponent from './components/categoryComponent/ListCategoryComponent';
import CreateCategoryComponent from './components/categoryComponent/CreateCategoryComponent';
import UpdateCategoryComponent from './components/categoryComponent/UpdateCategoryComponent';
import infoComponent from './components/infoComponent/infoComponent';
import ListWaiterComponent from './components/waiterComponent/ListWaiterComponent';
import CreateWaiterComponent from './components/waiterComponent/CreateWaiterComponent';
import UpdateWaiterComponent from './components/waiterComponent/UpdateWaiterComponent';
import CreateMediaComponent from './components/mediaComponent/CreateMediaComponent';
import CreateRoleComponent from './components/roleComponent/CreateRoleComponent';
import UpdateRoleComponent from './components/roleComponent/UpdateRoleComponent';
import ListRoleComponent from './components/roleComponent/ListRoleComponent';

import { loading, AppContext } from './ContextApi';



function App() {

  return (
    <AppContext.Provider value={loading}>
      <div>

        <HeaderComponent />
        <div className="container">
          <Router>
            <Switch>
              
              <Route path="/" exact component={LoginComponent}></Route>
              <Route path="/home" exact component={ListProductComponent}></Route>
              <Route path="/product" component={ListProductComponent}></Route>
              <Route path="/add-product" component={CreateProductComponent}></Route>
              <Route path="/update-product" component={UpdateProductComponent}></Route>
              <Route path="/user" component={ListUserComponent}></Route>
              <Route path="/add-user" component={CreateUserComponent}></Route>
              <Route path="/update-user/:id" component={UpdateUserComponent}></Route>
              <Route path="/category" component={ListCategoryComponent}></Route>
              <Route path="/add-category" component={CreateCategoryComponent}></Route>
              <Route path="/update-category" component={UpdateCategoryComponent}></Route>
              <Route path="/info" component={infoComponent}></Route>
              <Route path="/place" component={ListPlaceComponent}></Route>
              <Route path="/add-place" component={CreatePlaceComponent}></Route>
              <Route path="/update-place/:id" component={UpdatePlaceComponent}></Route>
              {/* <Route path ="/table" component ={ListTableComponent}></Route>
                  <Route path ="/add-table" component ={CreateTableComponent}></Route>
                  <Route path ="/update-table" component ={UpdateTableComponent}></Route> */}
              <Route path="/waiter" component={ListWaiterComponent}></Route>
              <Route path="/add-waiter" component={CreateWaiterComponent}></Route>
              <Route path="/update-waiter" component={UpdateWaiterComponent}></Route>
              <Route path="/media" component={CreateMediaComponent}></Route>
              <Route path="/role" component={ListRoleComponent}></Route>
              <Route path="/add-role" component={CreateRoleComponent}></Route>
              <Route path="/update-role/:id" component={UpdateRoleComponent}></Route>

            </Switch>


          </Router>
        </div>
      </div>
    </AppContext.Provider>

  );
}
export default App


