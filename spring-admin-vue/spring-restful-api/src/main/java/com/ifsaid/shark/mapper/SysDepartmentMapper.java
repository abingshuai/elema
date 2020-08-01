package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.SysDepartment;
import org.apache.ibatis.annotations.Select;

/**

 * <p>
 *
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */


public interface SysDepartmentMapper extends BaseMapper<SysDepartment, Integer> {

    /**
     * 判断某个部门下，是否还拥有 子部门
     *
     * @param id
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/14 0:25
     */
    @Select("SELECT COUNT(*) FROM tb_sys_department WHERE parent_id = #{id}")
    int haveChildren(Integer id);


    /**
     * 判断某个部门下，是否还拥有 用户
     *
     * @param id
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/14 0:25
     */
    @Select("SELECT COUNT(*) FROM tb_sys_user WHERE dept_id = #{id}")
    int haveUsers(Integer id);

}
