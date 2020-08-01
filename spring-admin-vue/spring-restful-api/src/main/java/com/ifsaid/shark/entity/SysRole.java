package com.ifsaid.shark.entity;

import com.ifsaid.shark.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;


/**

 * <p>
 * 后台系统角色
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */


@Entity
@Table(name = "tb_sys_role")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysRole extends BaseEntity implements java.io.Serializable {

    /**
     * @description: 角色ID
     * @date: 2019/12/11 22:15
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rid;

    /**
     * @description: 角色 名称
     * @date: 2019/12/11 22:15
     */
    private String roleName;

    /**
     * @description: 角色 描述
     * @date: 2019/12/11 22:15
     */
    private String description;

}