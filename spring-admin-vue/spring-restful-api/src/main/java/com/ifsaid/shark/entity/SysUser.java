package com.ifsaid.shark.entity;

import com.ifsaid.shark.common.domain.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


/**

 * <p>
 * 后台系统用户
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */


@Entity
@Table(name = "tb_sys_user")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SysUser extends BaseEntity implements java.io.Serializable {

    /**
     * 用户唯一id
     *
     * @date: 2019/12/11 22:15
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer uid;

    /**
     * 头像
     *
     * @date: 2019/12/11 22:15
     */
    private String avatar;

    /**
     * 用户名
     *
     * @date: 2019/12/11 22:15
     */
    private String username;

    /**
     * 邮箱
     *
     * @date: 2019/12/11 22:15
     */
    private String email;

    /**
     * 昵称
     *
     * @date: 2019/12/11 22:15
     */
    private String nickname;

    /**
     * 密码
     *
     * @date: 2019/12/11 22:15
     */
    private String password;

    /**
     * 性别
     *
     * @date: 2019/12/11 22:15
     */
    private Integer gender;

    /**
     * 生日
     *
     * @date: 2019/12/11 22:15
     */
    private LocalDate birthday;

    /**
     * 部门 Id
     *
     * @date: 2019/12/11 22:15
     */
    private Integer deptId;

    /**
     * 状态 [ 0.禁用 1.正常 2.被删除 ]
     *
     * @date: 2019/12/11 22:15
     */
    private Integer status;

    private String phone;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
