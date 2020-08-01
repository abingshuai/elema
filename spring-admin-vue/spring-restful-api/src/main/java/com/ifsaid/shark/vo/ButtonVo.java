package com.ifsaid.shark.vo;

import lombok.*;


/**

 * <p>
 * 用户登录成功后，返回的按钮权限
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
public class ButtonVo implements java.io.Serializable {

    private Integer pid;

    private String resources;

    private String title;


}
