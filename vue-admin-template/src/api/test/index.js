import request from '@/utils/request'

export function getTest(url) {
  return request({
    url: url,
    method: 'get',
  })
}
export function postTest(url) {
    return request({
      url: url,
      method: 'post',
    })
  }