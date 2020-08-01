package com.ifsaid.shark.common.entrypoint;

import com.ifsaid.shark.util.JsonResult;
import com.ifsaid.shark.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;


/**

 * <p>
 * 认证失败，处理类
 * </p>
 *
 * @author LinZhaoBing <316638644@qq.com>
 * @version 2.0
 * @date 2019/4/18 11:45

 */


@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        /**身份认证未通过*/
        response.getWriter().write(
                JsonUtils.objectToJson(
                        JsonResult.result(
                                JsonResult.HttpStatus.UNAUTHORIZED.getStatus(),
                                authException.getMessage()
                        )
                )
        );
    }
}
