import axios from "axios";

const config = {
  headers: {
    "Content-type": "application/json",
    Authorization: `Bearer ${localStorage.getItem("refresh_token")}`,
  },
};
export const cartApi = {
  getCart: async () => {
    return await axios.get("http://localhost:8080/cart", config);
  },
  addItem: async (item) => {
    return await axios.post("http://localhost:8080/cart/add", item, config);
  },
};
