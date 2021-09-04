import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { userLoginAction } from "../features/counter/authSlice";
import LoginForm from "./LoginForm";
import "react-toastify/dist/ReactToastify.css";
import { Loading } from "./ReuseComponent";

const LoginPage = () => {
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();

  return (
    <div className="bg-gray-200 flex p-10 content-container w-screen overflow-hidden bg-login-page bg-cover justify-center items-center">
      {auth.loading && <Loading />}
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
            dispatch(userLoginAction(data));
          }}
        />
      </section>
    </div>
  );
};

export default LoginPage;
