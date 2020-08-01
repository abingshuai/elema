import request from '@/utils/request'

export function search(params) {
  return request({
    url: '/dms/stock/search/',
    method: 'post',
    data:{
      params
    }

  })
}
export function handleInbound(params) {
  return request({
    url: '/dms/stock/handleInbound/',
    method: 'post',
    data:{
      params
    }

  })
}
export function handleOutbound(params) {
  return request({
    url: '/dms/stock/handleOutbound/',
    method: 'post',
    data:{
      params
    }

  })
}
export function createStock(params) {
  return request({
    url: '/dms/stock/createStock/',
    method: 'post',
    data:{
      params
    }

  })
}