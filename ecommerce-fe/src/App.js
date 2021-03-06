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
import { HomePage } from "./component/HomePage";
import { useSelector } from "react-redux";
import { ToastContainer } from "react-toastify";
import ProductPage from "./component/ProductPage";
import Footer from "./component/Footer";
import { getCartAction } from "./features/counter/cartSlice";
import { useDispatch } from "react-redux";

function App() {
  const status_login = useSelector((state) => state.auth.status_login);
  const dispatch = useDispatch();
  status_login && dispatch(getCartAction());
  return (
    <Fragment>
      <Router>
        <Navbar />
        <Switch>
          <Route path="/homePage">
            <HomePage />
          </Route>
          <Route path="/products/:id" component={ProductPage} />
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
            <Redirect to="/homePage" />
          )}
        </Switch>
        <Footer />
        <ToastContainer
          position="bottom-center"
          autoClose={3000}
          hideProgressBar={false}
          newestOnTop={false}
          closeOnClick
          rtl={false}
          pauseOnFocusLoss
          draggable
          pauseOnHover
          limit={1}
          theme="dark"
        />
      </Router>
    </Fragment>
  );
}

export default App;
