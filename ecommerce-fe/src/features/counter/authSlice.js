import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
import { userApi } from "../../api/userApi";

const initialState = {
  status_login: localStorage.getItem("access_token") ? true : false,
  loading: false,
};
export const userLogin = createAsyncThunk(
  "user/login",
  async (data, ThunkAPI) => {
    try {
      const response = await userApi.login(data);
      return response.data;
    } catch (err) {
      if (!err.response) {
        return ThunkAPI.rejectWithValue({ err_message: "Network Error" });
      }
      return ThunkAPI.rejectWithValue(err.response.data);
    }
  }
);
export const userRegister = createAsyncThunk(
  "user/register",
  async (data, ThunkAPI) => {
    console.log("run this" + JSON.stringify(data));
    try {
      const response = await userApi.register({
        username: data.username,
        password: data.password,
      });
      console.log("what is going on?");
      console.log(response);
      return response.data;
    } catch (err) {
      console.log("erro in here?" + JSON.stringify(err.response.data));
      if (!err.response) {
        return ThunkAPI.rejectWithValue({ err_message: "Network Error" });
      }
      return ThunkAPI.rejectWithValue(err.response.data);
    }
  }
);
export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    logout: (state) => {
      localStorage.removeItem("access_token");
      localStorage.removeItem("refresh_token");
      state.status_login = false;
    },
  },
  extraReducers: {
    [userLogin.pending]: (state, action) => {
      state.loading = true;
    },
    [userLogin.fulfilled]: (state, action) => {
      state.loading = false;
      state.status_login = true;
      localStorage.setItem("access_token", action.payload.access_token);
      localStorage.setItem("refresh_token", action.payload.refresh_token);
    },
    [userLogin.rejected]: (state, action) => {
      state.loading = false;
      console.log("fds" + action.payload.err_message);
      toast.error(action.payload.err_message);
    },
    [userRegister.pending]: (state, action) => {
      state.loading = true;
    },
    [userRegister.fulfilled]: (state, action) => {
      state.loading = false;
      toast.success("register success");
    },
    [userRegister.rejected]: (state, action) => {
      state.loading = false;
      toast.error(action.payload.err_message);
    },
  },
});

export const { login, logout } = authSlice.actions;

export default authSlice.reducer;
