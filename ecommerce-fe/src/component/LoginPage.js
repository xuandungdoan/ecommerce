import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { login, logout, userLogin } from "../features/counter/authSlice";
import LoginForm from "./LoginForm";

const LoginPage = () => {
  const status = useSelector((state) => state.auth.value);
  const dispatch = useDispatch();
  console.log("status: ", status);
  return (
    // <div>
    //   <h2>{status ? "true" : "false"}</h2>
    //   <button onClick={() => dispatch(login())}>login</button>
    //   <button onClick={() => dispatch(logout())}>logout</button>
    // </div>
    <div className="bg-gray-200 flex p-10 content-container w-screen overflow-hidden bg-login-page bg-cover justify-center items-center">
      <section className="bg-white rounded-lg max-w-sm px-7 py-3">
        <p className="text-2xl font-semibold">Login</p>
        <p className="text-gray-400  sm:text-sm">
          Doesn't have any account yet?
          <Link className="ml-1 text-indigo-700 underline" to="register">
            Sign up
          </Link>
        </p>
        <LoginForm
          onSubmit={(data) => {
            console.log("login form: " + JSON.stringify(data));
            dispatch(userLogin(data));
          }}
        />
      </section>
    </div>
  );
};

export default LoginPage;
