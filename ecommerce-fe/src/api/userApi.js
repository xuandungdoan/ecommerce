import axios from "axios";

export const userApi = {
  register: async (data) => {
    try {
      const res = await axios.post("http://localhost:8080/user/register", data);
      return res.payload;
    } catch (error) {
      alert(error);
    }
  },
  login: async (data) => {
    return await axios.post("http://localhost:8080/login", data);
  },
};
