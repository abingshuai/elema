package com.ifsaid.shark.vo;

import lombok.*;

import java.util.List;
import java.util.Set;

/**

 * <p>
 * 菜单权限
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
@EqualsAndHashCode
public class MenuVo implements java.io.Serializable {

    private Integer pid;

    private Integer parentId;

    private String icon;

    private String resources;

    private String title;

    private List<MenuVo> children;

}
