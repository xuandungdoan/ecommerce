import React from "react";
import { Form, Field } from "react-final-form";
import { required, length } from "redux-form-validators";

const composeValidators =
  (...validators) =>
  (value) =>
    validators.reduce(
      (error, validator) => error || validator(value),
      undefined
    );

const renderField = ({
  input,
  label,
  type,
  meta: { touched, error, warning },
}) => (
  <div className="mt-3">
    <label className="block text-gray-700 text-sm font-semibold mb-2">
      {label}
    </label>
    <div>
      <input
        {...input}
        className="shadow appearance-none border border-gray-600 rounded w-full py-2 px-3 text-gray-700 leading-tight "
        placeholder={label}
        type={type}
      />
      {touched &&
        ((error && (
          <span className="flex items-center font-medium tracking-wide text-red-500 text-xs mt-1 ml-1">
            {error}
          </span>
        )) ||
          (warning && (
            <span class="flex items-center font-medium tracking-wide text-red-500 text-xs mt-1 ml-1">
              {warning}
            </span>
          )))}
    </div>
  </div>
);

const LoginForm = (props) => {
  const { onSubmit } = props;
  return (
    <Form onSubmit={onSubmit}>
      {({ handleSubmit, pristine, reset, submitting }) => (
        <form onSubmit={handleSubmit}>
          <Field
            name="username"
            type="text"
            component={renderField}
            label="Username"
            validate={composeValidators(required(), length({ max: 15 }))}
          />
          <Field
            name="password"
            type="password"
            component={renderField}
            label="Password"
            validate={composeValidators(required(), length({ max: 15 }))}
          />
          <label className="flex items-center mt-3">
            <Field
              className="form-checkbox"
              name="remembered"
              component="input"
              type="checkbox"
            />
            <span className="ml-2 text-gray-400 text-sm font-semibold ">
              Remember me
            </span>
          </label>
          <div>
            <button
              type="submit"
              disabled={submitting}
              className="mt-5 w-full bg-blue-500 hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded focus:outline-none focus:shadow-outline mb-7"
            >
              Sign In
            </button>
          </div>
        </form>
      )}
    </Form>
  );
};

export default LoginForm;
