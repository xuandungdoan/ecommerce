import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
import { cartApi } from "../../api/cartApi";

const initialState = {
  cartList: {},
  loading: false,
};
export const getCartAction = createAsyncThunk(
  "cart/getCart",
  async (ThunkAPI) => {
    try {
      const response = await cartApi.getCart();
      return response.data;
    } catch (err) {
      if (!err.response) {
        return ThunkAPI.rejectWithValue({ err_message: "Network Error" });
      }
      return ThunkAPI.rejectWithValue(err.response.data);
    }
  }
);
export const addItemAction = createAsyncThunk(
  "cart/addToCart",
  async (item, ThunkAPI) => {
    try {
      const response = await cartApi.addItem(item);
      return response.data;
    } catch (err) {
      if (!err.response) {
        return ThunkAPI.rejectWithValue({ err_message: "Network Error" });
      }
      return ThunkAPI.rejectWithValue(err.response.data);
    }
  }
);

export const cartSlice = createSlice({
  name: "product",
  initialState,
  reducers: {
    addItem: (state, action) => {
      if (state.cartList) {
        let newCartList = state.cartList.cartDetailList;
        newCartList.push(action.payload);
        state.cartList.cartDetailList = newCartList;
      }
    },
  },
  extraReducers: {
    [getCartAction.pending]: (state, action) => {
      state.loading = true;
    },
    [getCartAction.fulfilled]: (state, action) => {
      state.loading = false;
      state.cartList = action.payload;
    },
    [getCartAction.rejected]: (state, action) => {
      state.loading = false;
      toast.error(action.payload.err_message);
    },
    [addItemAction.pending]: (state, action) => {
      state.loading = true;
    },
    [addItemAction.fulfilled]: (state, action) => {
      state.loading = false;
      state.cartList = action.payload;
    },
    [addItemAction.rejected]: (state, action) => {
      state.loading = false;
      toast.error(action.payload.err_message);
    },
  },
});
export const { addItem } = cartSlice.actions;
export default cartSlice.reducer;
