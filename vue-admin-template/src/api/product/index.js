import request from '@/utils/request'

export function search(params) {
  return request({
    url: '/dms/product/search/',
    method: 'post',
    data:{
      params
    }

  })
}
export function createProduct(params) {
    return request({
      url: '/dms/product/createProduct/',
      method: 'post',
      data:{
        params
      }
  
    })
  }
  export function updateProduct(params) {
    return request({
      url: '/dms/product/updateProduct/',
      method: 'post',
      data:{
        params
      }
  
    })
  }
  export function deleteProduct(params) {
    return request({
      url: '/dms/product/deleteProduct/',
      method: 'get',
      data:{
        id:params
      }
  
    })
  }
  export function getAvailableStoreHouse(params) {
    return request({
      url: '/dms/storeHouse/getAvailableStoreHouse/',
      method: 'get',
      data:{
        nums:params
      }
  
    })
  }

  