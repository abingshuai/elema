package com.ifsaid.shark.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * <p>
 *  jwt 认证异常
 * </p>
 *
 * @author LinZhaoBing<316638644@qq.com>
 * @version 1.0
 * @createTime 2020/3/7 0007 16:59
 */

public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

}
