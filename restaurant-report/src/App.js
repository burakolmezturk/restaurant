
import './App.css';
import Report from './Report';
import { BrowserRouter as Router, Route, Switch } from "react-router-dom"
function App() {
  return (
      <Router>
        <Switch>
          <Route path="/" component={Report}></Route>       
        </Switch>
      </Router>
  );
}

export default App;
