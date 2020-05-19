<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      :class="registerType ? 'animated zoomInRight' : 'animated zoomInLeft'"
      label-position="left"
    >

      <div class="title-container">
        <h3 class="title">Sigma {{ todoInfo }}</h3>
      </div>

      <el-form-item prop="username">
        <span class="svg-container">
          <svg-icon icon-class="user" />
        </span>
        <el-input
          ref="username"
          v-model="loginForm.username"
          :placeholder="registerType ? '请输入用户名' : 'Username'"
          name="username"
          type="text"
          tabindex="1"
          autocomplete="off"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          :placeholder="registerType ? '请输入密码（不少于6位）' : 'Password'"
          name="password"
          tabindex="2"
          autocomplete="new-password"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="registerType ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:10px;"
        @click.native.prevent="handleLogin"
      >
        {{ todoInfo }}
      </el-button>

      <div :class="[registerType ? 'center' : '','register']">
        <template v-if="registerType">
          <span class="clcikRegister" @click="handleRegister">已有账号登录</span>
        </template>
        <template v-else>
          没有账号？
          <span class="clcikRegister" @click="handleRegister">注册</span>
          <a href="#" class="right clcikRegister">忘记密码</a>
        </template>
      </div>

    </el-form>
  </div>
</template>

<script>
import { login, register, validateUser } from '@/api/user'
import { setToken } from '@/utils/auth'

export default {
  name: 'Login',
  data() {
    const validateUsername = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error('请输入用户名'))
      } else if (this.todoInfo === 'Register') {
        validateUser({ value: this.loginForm.username })
        callback()
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 4) {
        callback(new Error('请输入用户密码（不得少于6位）'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        username: '',
        password: ''
      },
      loginRules: {
        username: [{ required: true, trigger: 'blur', validator: validateUsername }],
        password: [{ required: true, trigger: 'blur', validator: validatePassword }]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      todoInfo: 'Login',
      registerModel: {
        'avatar': 'string',
        'birthday': '2019-08-03T06:33:34.161Z',
        'email': 'string',
        'enabled': true,
        'gender': 0,
        'nickname': 'string',
        'password': '',
        'mobile': '1360312313',
        'roleDTOS': [
          {
            'code': 'ROLE_USER'
          }
        ],
        'username': ''
      }
    }
  },
  computed: {
    registerType() {
      return this.todoInfo === 'Register'
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.loading = true
          if (this.registerType) {
            const tempData = Object.assign(this.registerModel, this.loginForm)
            register(tempData).then((response) => {
              this.$message({
                message: '恭喜你，注册成功！快去登录吧！',
                type: 'success'
              })
              this.todoInfo = 'Login'
              this.loading = false
            })
          } else {
            login(this.loginForm).then((response) => {
              setToken(response.data.principal.password)
              this.$router.push({ path: this.redirect || '/' })
              this.loading = false
            }).catch((e) => {
              this.loading = false
            })
          }
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    handleRegister() { // 注册
      this.todoInfo = this.registerType ? 'Login' : 'Register'
      this.$refs['loginForm'].resetFields()
    }
  }
}
</script>

<style lang="scss" >
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg:#283443;
$light_gray:#fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg:#2d3a4b;
$dark_gray:#889aa4;
$light_gray:#eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 520px;
    max-width: 100%;
    padding: 160px 35px 0;
    margin: 0 auto;
    overflow: hidden;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  // 注册
  .register {
    color: #8b9196;
    &.center {
      text-align: center;
    }
    .clcikRegister {
      color: #007fff;
      cursor: pointer;
    }
    .right{
      float: right;
    }
  }
}
</style>
