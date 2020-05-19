import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/login?username=' + data.username + '&password=' + data.password,
    method: 'post'
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}

export function register(data) {
  return request({
    url: '/register',
    method: 'post',
    data
  })
}

export function getInfo() {
  return request({
    url: '/user/info',
    method: 'get'
  })
}

export function getUserDetail(userId) {
  return request({
    url: '/user/' + userId,
    method: 'get'
  })
}

export function deleteUserById(userId) {
  return request({
    url: '/user/' + userId,
    method: 'delete'
  })
}

export function putUserById(user) {
  return request({
    url: '/user/' + user.id,
    method: 'put',
    data: user
  })
}

export function getUserMenu(roleCode) {
  return request({
    url: '/role/' + roleCode + '/menu',
    method: 'get'
  })
}

export function getUserList(condition) {
  return request({
    url: '/user',
    method: 'get',
    params: condition
  })
}

export function validateUser(data) {
  return request({
    url: '/valid/user/username',
    method: 'get',
    params: data
  })
}
