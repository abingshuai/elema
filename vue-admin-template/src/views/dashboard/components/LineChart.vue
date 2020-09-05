<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
  import echarts from 'echarts'
  require('echarts/theme/macarons') // echarts theme
  import resize from './mixins/resize'

  import {
    getPerWeek
  } from '@/api/statistics/index'

  export default {
    mixins: [resize],
    props: {
      className: {
        type: String,
        default: 'chart'
      },
      width: {
        type: String,
        default: '100%'
      },
      height: {
        type: String,
        default: '350px'
      },
      autoResize: {
        type: Boolean,
        default: true
      },
      chartData: {
        type: Object,
        required: true
      }
    },
    data() {
      return {
        chart: null,
        date:[],
        orderCountList: [],
        salesVolumeList:[]
      }
    },
    watch: {
      chartData: {
        deep: true,
        handler(val) {
          this.setOptions(val)
        }
      }
    },
    mounted() {
      this.$nextTick(() => {
        this.initChart()
      })
    },
    beforeDestroy() {
      if (!this.chart) {
        return
      }
      this.chart.dispose()
      this.chart = null
    },
    methods: {
      initChart() {
        this.chart = echarts.init(this.$el, 'macarons')
        this.setOptions(this.chartData)
      },
      setOptions({ expectedData, actualData } = {}) {
        const _this = this
        getPerWeek().then(result =>{
          for(var i=0;i<result.length;i++){
            _this.date.unshift(result[i].date)
            _this.orderCountList.unshift(result[i].orderCount)
            console.log()
            _this.salesVolumeList.unshift(result[i].salesVolume)
          }
          console.log(this.orderCountList)
          this.chart.setOption({
            xAxis: {
              data: this.date,
              boundaryGap: false,
              axisTick: {
                show: false
              }
            },
            grid: {
              left: 10,
              right: 10,
              bottom: 20,
              top: 30,
              containLabel: true
            },
            tooltip: {
              trigger: 'axis',
              axisPointer: {
                type: 'cross'
              },
              padding: [5, 10]
            },
            yAxis: {
              axisTick: {
                show: false
              }
            },
            legend: {
              data: ['订单数量', '订单总金额']
            },
            series: [{
              name: '订单数量', itemStyle: {
                normal: {
                  color: '#FF005A',
                  lineStyle: {
                    color: '#FF005A',
                    width: 2
                  }
                }
              },
              smooth: true,
              type: 'line',
              data: this.orderCountList,
              animationDuration: 2800,
              animationEasing: 'cubicInOut'
            },
              {
                name: '订单总金额',
                smooth: true,
                type: 'line',
                itemStyle: {
                  normal: {
                    color: '#3888fa',
                    lineStyle: {
                      color: '#3888fa',
                      width: 2
                    },
                    areaStyle: {
                      color: '#f3f8ff'
                    }
                  }
                },
                data: this.salesVolumeList,
                animationDuration: 2800,
                animationEasing: 'quadraticOut'
              }]
          })
        })
        console.log(this.date)

      }
    }
  }
</script>
