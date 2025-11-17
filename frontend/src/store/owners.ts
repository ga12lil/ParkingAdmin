import { defineStore } from 'pinia'
import api from '../api/axios'

export const useOwnersStore = defineStore('owners', {
  state: () => ({ owners: [] as any[], loading: false }),
  actions: {
    async load() {
      this.loading = true
      const res = await api.get('/owners')
      this.owners = Array.isArray(res.data) ? res.data : []
      this.loading = false
    },
    async add(owner: any) {
      await api.post('/owners', owner)
      await this.load()
    },
    async update(id: number, owner: any) {
      await api.put(`/owners/${id}`, owner)
      await this.load()
    },
    async remove(id: number) {
      await api.delete(`/owners/${id}`)
      await this.load()
    },
    async search(q: string) {
      const res = await api.get('/owners/search', { params: { q } })
      this.owners = Array.isArray(res.data) ? res.data : []
    }
  }
})