package com.ifsaid.shark.vo;

import com.ifsaid.shark.entity.SysUser;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TokenUserVo {
    private TokenValue tokenValue;
    private SysUser user;

    public TokenValue getTokenValue() {
        return tokenValue;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public void setTokenValue(TokenValue tokenValue) {
        this.tokenValue = tokenValue;
    }


    }
