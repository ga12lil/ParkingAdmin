<template>
  <div>
    <el-button type="primary" @click="openAdd">Добавить место</el-button>
    <el-table :data="store.spots" style="margin-top:20px">
      <el-table-column prop="id" label="ID" width="80" />
      <el-table-column prop="spotNumber" label="Номер места" />
      <el-table-column prop="isAvailable" label="Доступно">
        <template #default="scope">
          <el-tag :type="scope.row.isAvailable ? 'success' : 'danger'">{{ scope.row.isAvailable ? 'Да' : 'Нет' }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column label="Действия" width="220">
        <template #default="{ row }">
          <el-button size="small" @click="edit(row)">Изменить</el-button>
          <el-button type="danger" size="small" @click="remove(row.id)">Удалить</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog v-model="dialog" title="Место">
      <el-form :model="form">
        <el-form-item label="Номер"><el-input v-model="form.spotNumber" /></el-form-item>
        <el-form-item label="Доступно"><el-switch v-model="form.isAvailable" /></el-form-item>
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
import { useSpotsStore } from '../store/spots'

const store = useSpotsStore()
const dialog = ref(false)
const isEdit = ref(false)
const form = ref({ id: null, spotNumber: '', isAvailable: true })

onMounted(store.load)
function openAdd() { isEdit.value = false; form.value = { id: null, spotNumber: '', isAvailable: true }; dialog.value = true }
function edit(row: any) { isEdit.value = true; form.value = { ...row }; dialog.value = true }
async function save() { if (isEdit.value && form.value.id) await store.update(form.value.id, form.value); else await store.add(form.value); dialog.value=false }
async function remove(id: number) { await store.remove(id) }
</script>
