import request from '@/utils/request'

export function search(params) {
  return request({
    url: '/dms/storeHouse/search/',
    method: 'post',
    data:{
      params
    }

  })
}
export function highSearch(params) {
    return request({
      url: '/dms/storeHouse/highSearch/',
      method: 'post',
      data:{
        params
      }
    })
  }
export function updateStoreHouse(params) {
    return request({
      url: '/dms/storeHouse/updateStoreHouse/',
      method: 'post',
      data:{
        params
      }
  
    })
  }
  export function createStoreHouse(params) {
    return request({
      url: '/dms/storeHouse/createStoreHouse/',
      method: 'post',
      data:{
        params
      }
  
    })
  }
  export function deleteStoreHouse(params) {
    return request({
      url: '/dms/storeHouse/deleteStoreHouse/',
      method: 'get',
      data:{
        id:params
      }
    })
  }