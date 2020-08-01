import request from '@/utils/request'

export function search(params) {
    return request({
      url: '/dms/contact/search',
      method: 'post',
      data:{
        params
      }
  
    })
  }
  export function highSearch(params) {
    return request({
      url: '/dms/contact/highSearch/',
      method: 'post',
      data:{
        params
      }
    })
  }
  
  export function createContact(params) {
    return request({
      url: '/dms/contact/createContact/',
      method: 'post',
      data:{
        params
      }
    })
  }
  export function updateContact(params) {
    return request({
      url: '/dms/contact/updateContact/',
      method: 'post',
      data:{
        params
      }
    })
  }
  export function deleteContact(params) {
    return request({
      url: '/dms/contact/deleteContact/',
      method: 'get',
      data:{
        id:params
      }
    })
  }