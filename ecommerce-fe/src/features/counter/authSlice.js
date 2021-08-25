import { createSlice } from "@reduxjs/toolkit";

const initialState = {
  value: localStorage.getItem("loginState")
    ? localStorage.getItem("loginState")
    : false,
};

export const authSlice = createSlice({
  name: "auth",
  initialState,
  reducers: {
    login: (state) => {
      localStorage.setItem("loginState", true);
      state.value = true;
    },
    logout: (state) => {
      localStorage.setItem("loginState", false);
      state.value = false;
    },
  },
});

export const { login, logout } = authSlice.actions;

export default authSlice.reducer;
