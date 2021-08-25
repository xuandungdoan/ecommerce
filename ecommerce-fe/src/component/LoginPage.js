import React from "react";
import { useSelector, useDispatch } from "react-redux";
import { Link } from "react-router-dom";
import { login, logout } from "../features/counter/authSlice";

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
      <section className="bg-white rounded-lg max-w-sm h-96 px-4 pt-5">
        <p className="text-2xl font-semibold">Login</p>
        <p className="text-gray-400  sm:text-sm">
          Doesn't have any account yet?
          <Link className="ml-1 text-indigo-700 underline" to="register">
            Sign up
          </Link>
        </p>
        <form>
          <div className="mt-3">
            <label
              class="block text-gray-700 text-sm font-semibold mb-2"
              for="username"
            >
              Username
            </label>
            <input
              class="shadow appearance-none border border-gray-600 rounded w-full py-2 px-3 text-gray-700 leading-tight "
              id="username"
              type="text"
              placeholder="Username"
            />
          </div>
          <div className="mt-3">
            <label
              class="block text-gray-700 text-sm mb-2 flex font-semibold justify-between"
              for="password"
            >
              Password
              <a href="" className="text-indigo-700 underline font-normal">
                Forgot password
              </a>
            </label>
            <input
              class="shadow appearance-none border border-gray-600 rounded w-full py-2 px-3 text-gray-700 leading-tight "
              id="password"
              type="text"
              placeholder="Password"
            />
          </div>
          <div className="flex mt-3">
            <label className="flex items-center">
              <input type="checkbox" className="form-checkbox" />
              <span className="ml-2 text-gray-400 text-sm font-semibold ">
                Remember me
              </span>
            </label>
          </div>
          <button
            className="mt-5 w-full bg-blue-500 hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded focus:outline-none focus:shadow-outline mb-7"
            type="button"
            type="submit"
          >
            Sign In
          </button>
        </form>
      </section>
    </div>
  );
};

export default LoginPage;
