package com.ifsaid.shark.entity;

import lombok.*;

/**

 * <p>
 * 关联权限设置，时候用到
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */


@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Relation implements java.io.Serializable {

    /**
     * @description 比如: 用户 关联 角色，就表示 用户ID
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 20:36
     */
    private Integer targetId;

    /**
     * @description 比如: 用户 关联 角色，就表示 角色ID
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 20:36
     */
    private Integer sourceId;

}
