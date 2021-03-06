import React from "react";
import { Form, Field } from "react-final-form";
import { required, length } from "redux-form-validators";
import { passwordValidator, renderField } from "./ReuseComponent";

const composeValidators =
  (...validators) =>
  (value) =>
    validators.reduce(
      (error, validator) => error || validator(value),
      undefined
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
            validate={composeValidators(required(), passwordValidator())}
          />
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
