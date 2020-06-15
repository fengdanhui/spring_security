package com.imooc.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * spring security 抛出异常的基类：AuthenticationException
 * @date 2020/06/05
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
