import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { userApi } from "../../api/userApi";

const initialState = {
  status_login: localStorage.getItem("access_token") ? true : false,
  loading: false,
  err: "",
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
      console.log(JSON.stringify(action));
      state.err = action.payload;
    },
  },
});

export const { login, logout } = authSlice.actions;

export default authSlice.reducer;
