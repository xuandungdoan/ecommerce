import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { userRegisterAction } from "../features/counter/authSlice";
import RegisterForm from "./RegisterForm";
import { Loading } from "./ReuseComponent";

const RegisterPage = () => {
  const auth = useSelector((state) => state.auth);
  const dispatch = useDispatch();
  return (
    <div className="bg-gray-200 flex p-10 content-container w-screen overflow-hidden bg-login-page bg-cover justify-center items-center">
      {auth.loading && <Loading />}
      <section className="bg-white rounded-lg max-w-sm px-7 py-3">
        <p className="text-2xl font-semibold">Register</p>
        <p className="text-gray-400  sm:text-sm">
          Already had your own account?
          <Link className="ml-1 text-indigo-700 underline" to="login">
            Sign in
          </Link>
        </p>
        <RegisterForm onSubmit={(e) => dispatch(userRegisterAction(e))} />
      </section>
    </div>
  );
};

export default RegisterPage;
