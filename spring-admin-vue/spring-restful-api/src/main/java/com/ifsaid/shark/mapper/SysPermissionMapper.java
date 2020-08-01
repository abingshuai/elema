package com.ifsaid.shark.mapper;

import com.ifsaid.shark.common.mapper.BaseMapper;
import com.ifsaid.shark.entity.SysPermission;

import java.util.Set;

/**

 * <p>
 * 权限 Mapper 接口
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */

public interface SysPermissionMapper extends BaseMapper<SysPermission, Integer> {

    /**
     * 根据 用户ID 获取拥有的权限
     *
     * @param uid
     * @return Set<SysPermission>
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/13 23:43
     */
    Set<SysPermission> findAllByUserId(Integer uid);

    /**
     * 根据 角色ID 获取拥有的权限
     *
     * @param rid
     * @return Set<SysPermission>
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/13 23:43
     */
    Set<SysPermission> findAllByRoleId(Integer rid);

    /**
     * 判断是否某个权限下，是否还有拥有子权限
     *
     * @param pid
     * @return Integer
     * @throws
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/22 19:50
     */
    Integer haveChildren(Integer pid);


    /**
     * 判断是否某个权限，是否还被其他角色引用
     *
     * @param pid
     * @return Integer
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/22 19:50
     */
    Integer roleReference(Integer pid);

}
