import { Fragment } from "react";
import {
  Redirect,
  Route,
  BrowserRouter as Router,
  Switch,
} from "react-router-dom";
import LoginPage from "./component/LoginPage";
import Navbar from "./component/Navbar";
import { Counter } from "./features/counter/Counter";

function App() {
  return (
    // <div className="App">
    //   <LoginPage />
    // </div>
    <Fragment>
      <Router>
        <Navbar />
        <Switch>
          <Route path="/login">
            <LoginPage />
          </Route>

          <Route path="/register">
            <LoginPage />
          </Route>

          <Route path="/dashboard">
            <Counter />
          </Route>

          <Redirect to="/login" />
        </Switch>
      </Router>
    </Fragment>
  );
}

export default App;
