<template>
  <div>
    <div style="display:flex;gap:8px;margin-bottom:12px">
      <el-input v-model="q" placeholder="Поиск по номеру" @keyup.enter="onSearch" style="width:300px" />
      <el-button @click="onSearch">Поиск</el-button>
      <el-button type="primary" @click="openAdd">Добавить авто</el-button>
    </div>

    <el-table :data="store.cars" style="margin-top:20px">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="licensePlate" label="Номер" />
      <el-table-column prop="ownerId" label="ID владельца" />
      <el-table-column label="Действия" width="220">
        <template #default="{ row }">
          <el-button size="small" @click="edit(row)">Изменить</el-button>
          <el-button type="danger" size="small" @click="remove(row.id)">Удалить</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialog" title="Авто">
      <el-form :model="form">
        <el-form-item label="Номер"><el-input v-model="form.licensePlate" /></el-form-item>
        <el-form-item label="Владелец (ID)"><el-input v-model="form.ownerId" /></el-form-item>
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
import { useCarsStore } from '../store/cars'

const store = useCarsStore()
const dialog = ref(false)
const isEdit = ref(false)
const q = ref('')
const form = ref({ id: null, licensePlate: '', ownerId: null })

onMounted(store.load)

function openAdd() { isEdit.value = false; form.value = { id: null, licensePlate: '', ownerId: null }; dialog.value = true }
function edit(row: any) { isEdit.value = true; form.value = { ...row }; dialog.value = true }
async function save() {
  if (isEdit.value && form.value.id) await store.update(form.value.id, form.value)
  else await store.add(form.value)
  dialog.value = false
}
async function remove(id: number) { await store.remove(id) }
async function onSearch() { if (!q.value) await store.load(); else await store.search(q.value) }
</script>