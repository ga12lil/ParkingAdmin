import { createRouter, createWebHistory } from 'vue-router'
import OwnersPage from '../views/OwnersPage.vue'
import CarsPage from '../views/CarsPage.vue'
import SpotsPage from '../views/SpotsPage.vue'
import ReservationsPage from '../views/ReservationsPage.vue'


export default createRouter({
history: createWebHistory(),
routes: [
{ path: '/', redirect: '/owners' },
{ path: '/owners', component: OwnersPage },
{ path: '/cars', component: CarsPage },
{ path: '/spots', component: SpotsPage },
{ path: '/reservations', component: ReservationsPage },
]
})