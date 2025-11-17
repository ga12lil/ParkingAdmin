import { defineStore } from 'pinia'
import api from '../api/axios'

export const useSpotsStore = defineStore('spots', {
  state: () => ({ spots: [] as any[], loading: false }),
  actions: {
    async load() {
      this.loading = true
      const res = await api.get('/spots')
      this.spots = Array.isArray(res.data) ? res.data : []
      this.loading = false
    },
    async add(spot: any) {
      await api.post('/spots', spot)
      await this.load()
    },
    async update(id: number, spot: any) {
      await api.put(`/spots/${id}`, spot)
      await this.load()
    },
    async remove(id: number) {
      await api.delete(`/spots/${id}`)
      await this.load()
    }
  }
})