import axios from "axios";
const httpInstance =  axios.create({
    baseURL: 'http://localhost:3000/',
    timeout: 5000
})

// axios請求攔截器
httpInstance.interceptors.request.use(config => {
  return config
}, e => Promise.reject(e))

// axios回應式攔截器
httpInstance.interceptors.response.use(res => res.data, e => {

  return Promise.reject(e)
})

export default httpInstance;

