import { defineStore } from 'pinia'
import api from '../api/axios'

export const useReservationsStore = defineStore('reservations', {
  state: () => ({ reservations: [] as any[], loading: false }),
  actions: {
    async load() {
      this.loading = true
      const res = await api.get('/reservations')
      this.reservations = Array.isArray(res.data) ? res.data : []
      this.loading = false
    },
    async add(reservation: any) {
      await api.post('/reservations', reservation)
      await this.load()
    },
    async update(id: number, reservation: any) {
      await api.put(`/reservations/${id}`, reservation)
      await this.load()
    },
    async remove(id: number) {
      await api.delete(`/reservations/${id}`)
      await this.load()
    },
    async pay(id: number) {
      await api.post(`/reservations/${id}/pay`)
      await this.load()
    }
  }
})