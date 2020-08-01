package com.ifsaid.shark.common.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;
import java.util.Set;

/**

 * <p>
 *
 * </p>
 *
 * @author LinZhaoBing<316638644@qq.com>
 * @version 2.0
 * @date 2019/12/12 21:31

 */

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TreeNode implements java.io.Serializable {

    /**
     * 获取 当前节点 ID
     *
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 21:32
     */
    private long id;

    /**
     * 当前节点标题
     *
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 21:32
     */
    private String title;

    /**
     * 上级节点 ID
     *
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 21:32
     */
    private long parentId;

    /**
     * 源属性
     *
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 21:32
     */
    private Object source;

    /**
     * 获取 所有子节点
     *
     * @JsonInclude(JsonInclude.Include.NON_NULL)
     * @return int
     * @author LinZhaoBing<316638644@qq.com>
     * @date 2019/12/12 21:32
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNode> children;

}
