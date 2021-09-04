import { Fragment } from "react";
import {
  Redirect,
  Route,
  BrowserRouter as Router,
  Switch,
} from "react-router-dom";
import LoginPage from "./component/LoginPage";
import RegisterPage from "./component/RegisterPage";
import Navbar from "./component/Navbar";
import { Counter } from "./features/counter/Counter";
import { useSelector } from "react-redux";

function App() {
  const status_login = useSelector((state) => state.auth.status_login);
  return (
    // <div className="App">
    //   <LoginPage />
    // </div>
    <Fragment>
      <Router>
        <Navbar />
        <Switch>
          <Route path="/dashboard">
            <Counter />
          </Route>
          {!status_login ? (
            <Fragment>
              <Route path="/login">
                <LoginPage />
              </Route>
              <Route path="/register">
                <RegisterPage />
              </Route>
              <Redirect to="/login" />
            </Fragment>
          ) : (
            <Redirect to="/dashboard" />
          )}
        </Switch>
      </Router>
    </Fragment>
  );
}

export default App;
