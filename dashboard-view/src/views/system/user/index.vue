<template>
  <div class="container">
    <el-form
      :inline="true"
      :model="condition"
      label-width="70px"
      label-position="left"
      size="small"
    >
      <el-row :gutter="10">
        <el-col :span="6">
          <el-form-item label="用户名">
            <el-input v-model="condition.username" placeholder="请输入用户名" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="昵称">
            <el-input v-model="condition.nickname" placeholder="请输入昵称" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="性别">
            <el-select v-model="condition.gender" placeholder="请选择性别">
              <el-option label="男" value="1" />
              <el-option label="女" value="0" />
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="角色">
            <el-select v-model="condition.roleCode" placeholder="请选择角色">
              <el-option label="管理员" value="1" />
              <el-option label="普通用户" value="0" />
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="6">
          <el-form-item label="手机">
            <el-input v-model="condition.mobile" placeholder="请输入手机" />
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="邮箱">
            <el-input v-model="condition.email" placeholder="请输入邮箱" />
          </el-form-item>
        </el-col>
        <el-col :span="6" :offset="6">
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="fetchUserList">查询</el-button>
            <el-button type="primary" icon="el-icon-download">导出</el-button>
            <el-button type="primary" icon="el-icon-plus" disabled @click="addUser">添加</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table :data="users" style="width: 100%" size="small">
      <el-table-column type="index" />
      <el-table-column prop="username" label="用户名" width="100px" />
      <el-table-column prop="nickname" label="昵称" width="90px" />
      <el-table-column label="生日" width="140px">
        <template slot-scope="{row}">
          {{ row.birthday | formatDataTime }}
        </template>
      </el-table-column>
      <el-table-column label="性别" width="80px">
        <template slot-scope="{row}">
          {{ row.gender | formatGender }}
        </template>
      </el-table-column>
      <el-table-column prop="balance" label="余额" width="90px">
        <template slot-scope="{row}">
          {{ row.balance | formatBalance }}
        </template>
      </el-table-column>
      <el-table-column prop="mobile" label="手机号" width="110px" />
      <el-table-column prop="email" label="邮箱" width="150px" />
      <el-table-column prop="roleName" label="角色" width="120px" />
      <el-table-column prop="signature" label="签名" width="300" />
      <el-table-column label="操作" fixed="right" width="200">
        <template slot-scope="{row}">
          <el-button size="mini" icon="el-icon-edit" type="primary" @click="handleUpdate(row)">编辑</el-button>
          <el-button size="mini" icon="el-icon-delete" type="danger" @click="deleteUserById(row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      layout="total, sizes, prev, pager, next, jumper"
      :page-sizes="[10, 20, 30, 40]"
      :page-size="10"
      :total="condition.total"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :form="editData" style="width: 400px;" label-width="70px">
        <el-form-item label="用户名">
          <el-input v-model="editData.username" />
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="editData.mobile" />
        </el-form-item>
        <el-form-item label="角色">
          <el-input v-model="editData.roleName" />
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="editData.email" />
        </el-form-item>
        <el-form-item label="生日">
          <el-date-picker
            v-model="editData.birthday"
            type="datetime"
            placeholder="选择日期时间"
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="fetchUserInfo">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getUserList, deleteUserById, putUserById } from '@/api/user'
// import { get } from 'https'
// import { debuglog } from 'util'
// import { sign } from 'crypto'

export default {
  data() {
    return {
      condition: {
        nickname: '',
        username: '',
        gender: '',
        roleCode: '',
        mobile: '',
        email: '',
        total: 0,
        current: 1,
        size: 10
      },
      users: null,
      userLoading: true,
      dialogFormVisible: false,
      formLabelWidth: '120px',
      editData: '',
      textMap: {
        create: '添加',
        update: '修改'
      },
      dialogStatus: ''
    }
  },
  created() {
    this.fetchUserList()
  },
  methods: {
    addUser() { // 添加用户
      this.dialogFormVisible = true
      this.dialogStatus = 'create'
    },
    handleUpdate(row) { // 用户编辑
      this.dialogFormVisible = true
      this.editData = row
      this.dialogStatus = 'update'
    },
    fetchUserList() {
      this.userLoading = true
      getUserList(this.condition)
        .then(response => {
          if (response.success) {
            const { records, current, total, size } = { ...response.data }
            this.users = records
            this.condition.current = current
            this.condition.total = total
            this.condition.size = size
          }
          this.userLoading = false
        })
        .catch(e => {
          this.userLoading = false
          console.log(e)
        })
    },
    deleteUserById(userId) {
      deleteUserById(userId).then((response) => {
        this.$message.success('删除成功！')
        this.fetchUserList()
      }).catch((e) => {
        console.log(e)
      })
    },
    fetchUserInfo() {
      putUserById(this.editData).then((response) => {
        this.dialogFormVisible = false
        this.$notify({
          title: '成功',
          message: '更新成功',
          type: 'success',
          duration: 2000
        })
      }).catch((error) => {
        console.log(error)
        this.dialogFormVisible = false
      })
    },
    handleSizeChange(size) {
      this.condition.size = size
      this.fetchUserList()
    },
    handleCurrentChange(current) {
      this.condition.current = current
      this.fetchUserList()
    }
  }
}
</script>

<style lang="scss" scoped>
  .container {
    padding: 40px;
    .el-pagination {
      text-align: center;
      margin-top: 30px;
    }
  }
</style>
