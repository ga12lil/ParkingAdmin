<template>
  <div>
    <div style="display:flex;gap:8px;margin-bottom:12px">
      <el-input v-model="q" placeholder="Поиск по ФИО" @keyup.enter="onSearch" style="width:300px" />
      <el-button @click="onSearch">Поиск</el-button>
      <el-button type="primary" @click="openAdd">Добавить владельца</el-button>
    </div>

    <el-table :data="store.owners" style="margin-top:10px">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column label="ФИО">
        <template #default="{ row }">{{ row.firstName }} {{ row.lastName }}</template>
      </el-table-column>
      <el-table-column prop="phone" label="Телефон" />
      <el-table-column label="Действия" width="240">
        <template #default="{ row }">
          <el-button type="primary" size="small" @click="edit(row)">Изменить</el-button>
          <el-button type="danger" size="small" @click="remove(row.id)">Удалить</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialog" :title="isEdit ? 'Редактировать владельца' : 'Новый владелец'">
      <el-form :model="form">
        <el-form-item label="Имя"><el-input v-model="form.firstName" /></el-form-item>
        <el-form-item label="Фамилия"><el-input v-model="form.lastName" /></el-form-item>
        <el-form-item label="Телефон"><el-input v-model="form.phone" /></el-form-item>
        <el-form-item label="Email"><el-input v-model="form.email" /></el-form-item>
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
import { useOwnersStore } from '../store/owners'

const store = useOwnersStore()
const dialog = ref(false)
const isEdit = ref(false)
const q = ref('')
const form = ref({ id: null, firstName: '', lastName: '', phone: '', email: '' })

onMounted(store.load)

function openAdd() { isEdit.value = false; form.value = { id: null, firstName: '', lastName: '', phone: '', email: '' }; dialog.value = true }

function edit(row: any) { isEdit.value = true; form.value = { ...row }; dialog.value = true }

async function save() {
  if (isEdit.value && form.value.id) await store.update(form.value.id, form.value)
  else await store.add(form.value)
  dialog.value = false
}

async function remove(id: number) {
  await store.remove(id)
}

async function onSearch() {
  if (!q.value) await store.load()
  else await store.search(q.value)
}
</script>