import { addValidator } from "redux-form-validators";

export const Loading = () => (
  <div className="m-auto absolute inset-0  bg-gray-500 bg-opacity-75">
    <div className="m-auto absolute inset-0 animate-spin w-20 h-20 loader-spin "></div>
  </div>
);
export const renderField = ({
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
        type={label == "Password" || label == "Confirm" ? "password" : type}
      />
      {touched &&
        ((error && (
          <span className="flex items-center font-medium tracking-wide text-red-500 text-xs mt-1 ml-1 ">
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
export const passwordValidator = addValidator({
  defaultMessage: "Min is 8 and at least 1 character and 1 digit",
  validator: function (options, value, allValues) {
    return /^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d@$!%*#?&^_-]{8,}$/.test(value);
  },
});
