<template>
  <div class="app-container">
    <header class="header-container">
      <el-input size="mini" placeholder="搜索" style="width: 200px;" />
      <el-button size="mini" type="success" icon="el-icon-search">搜索</el-button>
      <el-button size="mini" type="primary" icon="el-icon-plus">新增</el-button>
    </header>
    <section class="middle-container">
      <el-table
        :data="menuData"
        style="width: 100%;margin-bottom: 20px;"
        row-key="id"
        :default-expand-all="true"
        :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
      >
        <el-table-column prop="name" label="名称" />
        <el-table-column prop="path" label="链接地址" />
      </el-table>
    </section>
  </div>
</template>

<script>
import { getUserMenu } from '@/api/user'

export default {
  data() {
    return {
      menuData: []
    }
  },
  mounted() {
    getUserMenu(this.$store.getters.roles[0]).then(response => {
      this.menuData = response.data
    })
  }
}
</script>
