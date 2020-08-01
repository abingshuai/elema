package com.ifsaid.shark.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

/**

 * <p>
 * 用户关联角色
 * </p>
 *
 * @author LinZhaoBing<316638644@qq.com>
 * @version 2.0
 * @date 2019/12/16 21:15

 */

@Getter
@Setter
@ToString
public class UserRelatedRoleVo implements java.io.Serializable {

    @NotNull(message = "用户ID不能为空!")
    private Integer uid;

    @NotNull(message = "角色ID列表,不能为空!")
    @Size(min = 1, message = "用户角色最少赋值一个")
    private Set<Integer> roles;

}
