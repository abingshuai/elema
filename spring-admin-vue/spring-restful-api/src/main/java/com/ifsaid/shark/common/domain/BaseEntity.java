package com.ifsaid.shark.common.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


/**

 * <p>
 * 父实体类
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */

@Getter
@Setter
public class BaseEntity implements java.io.Serializable {

    /**
     * @description: 创建时间
     * @date: 2019/12/11 21:12
     */
    private LocalDateTime createTime;

    /**
     * @description: 最后一次修改时间
     * @date: 2019/12/11 21:12
     */
    private LocalDateTime lastUpdateTime;

}
