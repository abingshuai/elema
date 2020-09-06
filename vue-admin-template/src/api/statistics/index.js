import request from "@/utils/request";

export function getRealTimeCount() {
  return request({ url: '/dms/statistics/getRealTimeCount', method: 'get' })
}

export function getPerWeek() {
  return request({ url: '/dms/statistics/getPerWeek', method: 'get' })
}
