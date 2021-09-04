import axios from "axios";

export const productApi = {
  getProducts: async (page) => {
    return await axios.get(
      `http://localhost:8080/product?page=${page}&size=10`
    );
  },
  getProductDetail: async (id) => {
    return await axios.get(`http://localhost:8080/product/${id}`);
  },
};
