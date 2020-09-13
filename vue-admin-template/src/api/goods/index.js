import request from '@/utils/request'

export function search(params) {
  return request({
    url: '/dms/goods/search/',
    method: 'post',
    data:{
      params
    }

  })
}

//--------------------              以下是商品列表api       ----------------------

// 获取分页信息
export function getGoodsPage(params) {
  return request({ url: '/dms/goods/info/page', method: 'get', data: params })
}

export function getAll() {
  return request({ url: '/dms/goods/getAll', method: 'get' })
}

export function saveGoods(params) {
  return request({
    url: '/dms/goods/saveGoods/',
    method: 'post',
    data:params

  })
}
export function updateGoods(params) {
  return request({
    url: '/dms/goods/updateGoods/',
    method: 'put',
    data: params

  })
}
export function deleteGoods(params) {
  return request({
    url: '/dms/goods/deleteGoods/'+params,
    method: 'delete',


  })
}

//--------------------              以下是分类api       ----------------------

// 获取分页信息
export function getTypePage(params) {
  return request({ url: '/dms/type/info/page', method: 'get', data: params })
}

export function saveType(params) {
  return request({
    url: '/dms/type/saveType/',
    method: 'post',
    data:params

  })
}
export function updateType(params) {
  return request({
    url: '/dms/type/updateType/',
    method: 'put',
    data: params

  })
}
export function deleteType(params) {
  return request({
    url: '/dms/type/deleteType/'+params,
    method: 'delete',


  })
}

