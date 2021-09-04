import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/counter/authSlice";
import productReducer from "../features/counter/productSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    product: productReducer,
  },
});
