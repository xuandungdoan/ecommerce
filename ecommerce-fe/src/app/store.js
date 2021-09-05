import { configureStore } from "@reduxjs/toolkit";
import authReducer from "../features/counter/authSlice";
import productReducer from "../features/counter/productSlice";
import cartReducer from "../features/counter/cartSlice";

export const store = configureStore({
  reducer: {
    auth: authReducer,
    product: productReducer,
    cart: cartReducer,
  },
});
