<template>
<el-container style="height:100vh">
<el-aside width="250px">
<el-menu router>
<el-menu-item index="/owners">Владельцы</el-menu-item>
<el-menu-item index="/cars">Авто</el-menu-item>
<el-menu-item index="/spots">Места</el-menu-item>
<el-menu-item index="/reservations">Брони</el-menu-item>
</el-menu>
</el-aside>


<el-container>
<el-header><h2>Parking Admin</h2></el-header>
<el-main>
<router-view />
</el-main>
</el-container>
</el-container>
</template>


<script setup lang="ts">
</script>