package com.ifsaid.shark.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**

 * <p>
 * MyBatis 通用 Mapper 接口
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */

public interface BaseMapper<T, ID> extends Mapper<T>, MySqlMapper<T> {

}
