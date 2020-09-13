package com.ifsaid.shark.controller;

import com.ifsaid.shark.entity.Statistics;
import com.ifsaid.shark.service.IGoodsService;
import com.ifsaid.shark.service.IOrderService;
import com.ifsaid.shark.service.IStatisticsService;
import com.ifsaid.shark.service.SysUserService;
import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.vo.StatisticsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 数据统计
 * @author LINHAN
 */
@Api(tags = "后台数据统计")
@RestController
@RequestMapping("/dms/statistics")
public class StatisticsController {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private IGoodsService goodsService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IStatisticsService statisticsService;
    /**
     * 统计实时数据
     * @return
     */
    @ApiOperation(value = "统计实时数据", notes = "统计实时数据")
    @RequestMapping("getRealTimeCount")
    public JsonResult<StatisticsVo> getRealTimeCount(){
        StatisticsVo statisticsVo = new StatisticsVo();
        try {
            statisticsVo.setUserCount(sysUserService.getUserCount());
            statisticsVo.setGoodsCount(goodsService.getGoodsCount());
            statisticsVo.setOrderCount(orderService.getOrderCount());
            statisticsVo.setOrderPrice(orderService.getOrderPrice());
            return JsonResult.success(statisticsVo);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }

    /**
     * 查询每日任务（周期为一周）
     * @return
     */
    @ApiOperation(value = "统计每日数据", notes = "统计每日任务（周期为一周）")
    @RequestMapping("getPerWeek")
    public JsonResult<List<Statistics>> getPerWeek(){
        try{
            List<Statistics> statisticsList = statisticsService.selectPerWeek();
            return JsonResult.success(statisticsList);
        }catch (Exception e){
            return JsonResult.fail(e.getMessage());
        }
    }
}
