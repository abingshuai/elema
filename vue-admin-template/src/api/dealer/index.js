import request from '@/utils/request'

export function getList() {
  return request({
    url: '/dms/dealer/list',
    method: 'get',

  })
}
export function search(params) {
  return request({
    url: '/dms/dealer/search/',
    method: 'post',
    data:{
      params
    }

  })
}
export function highSearch(params) {
  return request({
    url: '/dms/dealer/highSearch/',
    method: 'post',
    data:{
      params
    }
  })
}
export function createDealer(params) {
  return request({
    url: '/dms/dealer/createDealer/',
    method: 'post',
    data:{
      params
    }
  })
}
export function updateDealer(params) {
  return request({
    url: '/dms/dealer/updateDealer/',
    method: 'post',
    data:{
      params
    }
  })
}
