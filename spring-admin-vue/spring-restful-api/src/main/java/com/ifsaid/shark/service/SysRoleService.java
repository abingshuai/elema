package com.ifsaid.shark.service;

import com.ifsaid.shark.common.service.BaseService;
import com.ifsaid.shark.entity.SysRole;

import java.util.Set;

/**

 * <p>
 * 角色 Service 接口
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */


public interface SysRoleService extends BaseService<SysRole, Integer> {

    /**
     * 修改角色的权限
     *
     * @param rid
     * @param permissionIds
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 21:21
     */
    int updateRolePermissions(Integer rid, Set<Integer> permissionIds);

    /**
     * 根据 用户id 查出 拥有的 角色
     *
     * @param uid
     * @return Set<SysRole>
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 21:21
     */
    Set<SysRole> findAllByUserId(Integer uid);

}
