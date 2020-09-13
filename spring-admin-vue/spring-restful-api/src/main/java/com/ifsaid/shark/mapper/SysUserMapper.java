package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.Relation;
import com.ifsaid.shark.entity.SysUser;

import java.util.List;
import java.util.Set;

/**

 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */

public interface SysUserMapper extends BaseMapper<SysUser, Integer> {

    /**
     * 根据 用户名，和 昵称，模糊匹配
     *
     * @param keywords
     * @return Set<SysRole>
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/14 0:04
     */
    Set<SysUser> selectByKeywords(String keywords);

    /**
     * 角色关联，多个角色
     *
     * @param record
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/14 0:25
     */
    int insertByRoles(List<Relation> record);

    /**
     * 删除某个用户，拥有的角色
     *
     * @param uid
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/14 0:25
     */
    int deleteHaveRoles(Integer uid);


    Long getUserCount();

    SysUser getAllById(String id);
}
