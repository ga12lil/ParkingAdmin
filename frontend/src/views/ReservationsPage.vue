<template>
  <div>
    <el-button type="primary" @click="openAdd">Создать бронь</el-button>

    <el-table :data="store.reservations" style="margin-top:20px">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="carId" label="ID авто" />
      <el-table-column prop="ownerId" label="ID владельца" />
      <el-table-column prop="spotId" label="ID места" />
      <el-table-column prop="paid" label="Оплачено">
        <template #default="scope">
          <el-tag :type="scope.row.paid ? 'success' : 'warning'">{{ scope.row.paid ? 'Да' : 'Нет' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Действия" width="320">
        <template #default="{ row }">
          <el-button size="small" @click="edit(row)">Изменить</el-button>
          <el-button type="success" size="small" @click="pay(row.id)" :disabled="row.paid">Оплатить</el-button>
          <el-button type="danger" size="small" @click="remove(row.id)">Отменить</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialog" title="Бронь">
      <el-form :model="form">
        <el-form-item label="Авто">
          <el-select v-model="form.carId" placeholder="Выберите авто">
            <el-option v-for="c in cars" :key="c.id" :label="c.licensePlate" :value="c.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="Владелец">
          <el-select v-model="form.ownerId" placeholder="Выберите владельца">
            <el-option v-for="o in owners" :key="o.id" :label="o.firstName + ' ' + o.lastName" :value="o.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="Место">
          <el-select v-model="form.spotId" placeholder="Выберите место">
            <el-option v-for="s in spots" :key="s.id" :label="s.spotNumber" :value="s.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="Оплачено"><el-switch v-model="form.paid" /></el-form-item>
      </el-form>

      <template #footer>
        <el-button @click="dialog=false">Отмена</el-button>
        <el-button type="primary" @click="save">Сохранить</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useReservationsStore } from '../store/reservations'
import { useCarsStore } from '../store/cars'
import { useSpotsStore } from '../store/spots'
import { useOwnersStore } from '../store/owners'

const store = useReservationsStore()
const carsStore = useCarsStore()
const spotsStore = useSpotsStore()
const ownersStore = useOwnersStore()

const dialog = ref(false)
const isEdit = ref(false)
const form = ref({ id: null, carId: null, ownerId: null, spotId: null, paid: false })

onMounted(async () => {
  await Promise.all([store.load(), carsStore.load(), spotsStore.load(), ownersStore.load()])
})

function openAdd() { isEdit.value = false; form.value = { id: null, carId: null, ownerId: null, spotId: null, paid: false }; dialog.value = true }
function edit(row: any) { isEdit.value = true; form.value = { ...row }; dialog.value = true }
async function save() { if (isEdit.value && form.value.id) await store.update(form.value.id, form.value); else await store.add(form.value); dialog.value = false }
async function remove(id: number) { await store.remove(id) }
async function pay(id: number) { await store.pay(id) }

const cars = carsStore.cars
const spots = spotsStore.spots
const owners = ownersStore.owners
</script>
