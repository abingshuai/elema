import request from '@/utils/request'



// 获取分页信息
export function getOrderPage(params) {
  return request({ url: '/dms/order/info/page', method: 'get', data: params })
}

//通过订单编号查询订单详情
export function getOrderItemByNo(params) {
  return request({ url: '/dms/order/getOrderItem/'+params, method: 'get' })
}




