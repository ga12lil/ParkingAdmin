import { defineStore } from 'pinia'
import api from '../api/axios'

export const useCarsStore = defineStore('cars', {
  state: () => ({ cars: [] as any[], loading: false }),
  actions: {
    async load() {
      this.loading = true
      const res = await api.get('/cars')
      this.cars = Array.isArray(res.data) ? res.data : []
      this.loading = false
    },
    async add(car: any) {
      await api.post('/cars', car)
      await this.load()
    },
    async update(id: number, car: any) {
      await api.put(`/cars/${id}`, car)
      await this.load()
    },
    async remove(id: number) {
      await api.delete(`/cars/${id}`)
      await this.load()
    },
    async search(q: string) {
      const res = await api.get('/cars/search', { params: { q } })
      this.cars = Array.isArray(res.data) ? res.data : []
    }
  }
})