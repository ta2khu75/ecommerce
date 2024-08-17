import axios from "axios";
import { store } from "../redux/store";
const instance = axios.create({
  baseURL: "http://localhost:8080/api/v1/",
});
// Add a request interceptor
instance.interceptors.request.use(
  function (config) {
    const account = store.getState().account;
      config.headers.Authorization = "Bearer " + account.token;
    // Do something before request is sent
    // console.log(store.getState().accessToken.value);
    return config;
  },
  function (error) {
    // Do something with request error
    return Promise.reject(error);
  }
);
// instance.defaults.headers.common={'Authorization': `bearer ${token}`}
// Add a response interceptor
instance.interceptors.response.use(
  function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data

    return response?.data;
  },
  function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    
    return error?.response?.data ?? Promise.reject(error);
  }
);
export default instance;
