import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
import './App.css';
import ContextProvider from './contextApi/ContextApi';
import LoginComponent from './components/login/Login';
import HeaderComponent from './components/header/Header';
import MediaComponent from './components/media/CreateMedia';
import CreateWaiterComponent from './components/waiter/CreateWaiter';
import ListWaiterComponent from './components/waiter/ListWaiter';
import EditWaiterComponent from './components/waiter/EditWaiter';
import ListPlace from "./components/place/ListPlaces";
import CreatePlace from "./components/place/CreatePlace";
import EditPlace from "./components/place/EditPlace";
import Info from "./components/info/Info";
import CreateCategory from "./components/category/CreateCategory";
import ListCategory from "./components/category/ListCategory";
import EditCategory from "./components/category/EditCategory";
import CreateProduct from "./components/product/CreateProduct";
import ListProduct from "./components/product/ListProduct";
import EditProduct from "./components/product/EditProduct";
import CreateRole from "./components/role/CreateRole";
import ListRole from "./components/role/ListRole";
import EditRole from "./components/role/EditRole";
import CreateUser from "./components/user/CreateUser";
import ListUser from "./components/user/ListUser";
import EditUser from "./components/user/EditUser";
import CreateCustomer from "./components/customer/CreateCustomer";
import ListCustomer from "./components/customer/ListCustomer";
import EditCustomer from "./components/customer/EditCustomer";
import 'react-notifications/lib/notifications.css';
import { NotificationContainer } from 'react-notifications';
function App() {
  return (
    <ContextProvider>
      <Router>
        <Switch>
        <Route path="/update-customer" component={EditCustomer}></Route>
          <Route path="/customers" component={ListCustomer}></Route>
          <Route path="/add-customer" component={CreateCustomer}></Route>
          <Route path="/update-user" component={EditUser}></Route>
          <Route path="/user" component={ListUser}></Route>
          <Route path="/add-user" component={CreateUser}></Route>
          <Route path="/update-role" component={EditRole}></Route>
          <Route path="/add-role" component={CreateRole}></Route>
          <Route path="/role" component={ListRole}></Route>
          <Route path="/update-product" component={EditProduct}></Route>
          <Route path="/product" component={ListProduct}></Route>
          <Route path="/add-product" component={CreateProduct}></Route>
          <Route path="/update-category" component={EditCategory}></Route>
          <Route path="/category" component={ListCategory}></Route>
          <Route path="/add-category" component={CreateCategory}></Route>
          <Route path="/info" component={Info}></Route>
          <Route path="/update-place" component={EditPlace}></Route>
          <Route path="/place" component={ListPlace}></Route>
          <Route path="/add-place" component={CreatePlace}></Route>
          <Route path="/media" component={MediaComponent}></Route>
          <Route path="/waiter" component={ListWaiterComponent}></Route>
          <Route path="/add-waiter" component={CreateWaiterComponent}></Route>
          <Route path="/update-waiter" component={EditWaiterComponent}></Route>
          <Route path="/" component={LoginComponent}></Route>
          <Route path="/login" component={LoginComponent}></Route>
          
        </Switch>

        <NotificationContainer />
      </Router>

    </ContextProvider>
  );
}

export default App;
