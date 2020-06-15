package com.imooc.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @date 2020/06/10
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(ServletWebRequest request);
}
