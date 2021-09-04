import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
import { userApi } from "../../api/userApi";

const initialState = {
  status_login: localStorage.getItem("access_token") ? true : false,
  loading: false,
};
export const userLoginAction = createAsyncThunk(
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
export const userRegisterAction = createAsyncThunk(
  "user/register",
  async (data, ThunkAPI) => {
    try {
      const response = await userApi.register({
        username: data.username,
        password: data.password,
      });
      return response.data;
    } catch (err) {
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
    [userLoginAction.pending]: (state, action) => {
      state.loading = true;
    },
    [userLoginAction.fulfilled]: (state, action) => {
      state.loading = false;
      state.status_login = true;
      localStorage.setItem("access_token", action.payload.access_token);
      localStorage.setItem("refresh_token", action.payload.refresh_token);
    },
    [userLoginAction.rejected]: (state, action) => {
      state.loading = false;
      toast.error(action.payload.err_message);
    },
    [userRegisterAction.pending]: (state, action) => {
      state.loading = true;
    },
    [userRegisterAction.fulfilled]: (state, action) => {
      state.loading = false;
      toast.success("register success");
    },
    [userRegisterAction.rejected]: (state, action) => {
      state.loading = false;
      toast.error(action.payload.err_message);
    },
  },
});

export const { logout } = authSlice.actions;

export default authSlice.reducer;
