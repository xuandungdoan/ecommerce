import axios from "axios";

export const userApi = {
  register: async (data) => {
    return await axios.post("http://localhost:8080/user/register", data);
  },
  login: async (data) => {
    return await axios.post("http://localhost:8080/login", data);
  },
};
