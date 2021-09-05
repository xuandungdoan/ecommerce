import { createAsyncThunk, createSlice } from "@reduxjs/toolkit";
import { toast } from "react-toastify";
import { productApi } from "../../api/productApi";

const initialState = {
  products: [],
  productDetail: {},
  loading: false,
};
export const getProductsAction = createAsyncThunk(
  "product/getAll",
  async (page, ThunkAPI) => {
    try {
      const response = await productApi.getProducts(page);
      return response.data;
    } catch (err) {
      if (!err.response) {
        return ThunkAPI.rejectWithValue({ err_message: "Network Error" });
      }
      return ThunkAPI.rejectWithValue(err.response.data);
    }
  }
);
export const getProductDetailAction = createAsyncThunk(
  "product/getDetail",
  async (id, ThunkAPI) => {
    try {
      const response = await productApi.getProductDetail(id);
      return response.data;
    } catch (err) {
      if (!err.response) {
        return ThunkAPI.rejectWithValue({ err_message: "Network Error" });
      }
      return ThunkAPI.rejectWithValue(err.response.data);
    }
  }
);

export const productSlice = createSlice({
  name: "product",
  initialState,
  reducers: {},
  extraReducers: {
    [getProductsAction.pending]: (state, action) => {
      state.loading = true;
    },
    [getProductsAction.fulfilled]: (state, action) => {
      state.loading = false;
      state.products = action.payload.content;
    },
    [getProductsAction.rejected]: (state, action) => {
      state.loading = false;
      toast.error(action.payload.err_message);
    },
    [getProductDetailAction.pending]: (state, action) => {
      state.loading = true;
    },
    [getProductDetailAction.fulfilled]: (state, action) => {
      state.loading = false;
      state.productDetail = action.payload;
    },
    [getProductDetailAction.rejected]: (state, action) => {
      state.loading = false;
      toast.error(action.payload.err_message);
    },
  },
});

export default productSlice.reducer;
