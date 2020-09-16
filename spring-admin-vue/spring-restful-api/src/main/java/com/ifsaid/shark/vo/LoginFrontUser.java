package com.ifsaid.shark.vo;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginFrontUser implements java.io.Serializable{
    @NotNull(message = "用户名不能为空")
    @Length(min = 5, message = "用户名长度不能少于五位")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 5, message = "密码长度不能少于五位")
    private String password;
}
