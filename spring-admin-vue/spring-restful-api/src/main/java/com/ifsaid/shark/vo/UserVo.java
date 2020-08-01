package com.ifsaid.shark.vo;

import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


/**

 * <p>
 * 登录成功后，用户详细 Vo 模型
 * </p>
 *
 * @author LinZhaoBing<316638644@qq.com>
 * @version 2.0
 * @date 2019/12/12 23:29

 */


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVo implements java.io.Serializable {

    private Integer uid;

    /**
     * 头像
     *
     *   
     */
    private String avatar;

    /**
     * 用户名
     *
     *   
     */
    private String username;

    /**
     * 邮箱
     *
     *   
     */
    private String email;

    /**
     * 昵称
     *
     *   
     */
    private String nickname;

    /**
     * 性别
     *
     *   
     */
    private Integer gender;

    /**
     * 部门名称
     *
     *   
     */
    private String departmentName;

    /**
     * 角色名称，列表
     *
     *   
     */
    private Set<String> roles;

    /**
     * 生日
     *
     *   
     */
    private LocalDate birthday;

    /**
     * @describe 按钮

     * @author LinZhaoBing
     */
    private List<ButtonVo> buttons;

    /**
     * @describe 菜单

     * @author LinZhaoBing
     */
    private List<MenuVo> menus;

}
