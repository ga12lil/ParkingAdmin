import axios from 'axios'
import { ElMessage } from 'element-plus'

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: { 'Content-Type': 'application/json' }
})


api.interceptors.request.use(config => {
  // const token = localStorage.getItem('token')
  // if (token) config.headers.Authorization = `Bearer ${token}`
  return config
})


api.interceptors.response.use(
  res => res,
  err => {
    const status = err?.response?.status
    const data = err?.response?.data
    let text = 'Сетевая ошибка'
    if (status) text = `Ошибка ${status}`
    if (data && data.message) text += `: ${data.message}`
    ElMessage({ message: text, type: 'error' })
    return Promise.reject(err)
  }
)

export default api
