package com.imooc.security.core.validate.code.sms;

/**
 * @date 2020/06/12
 */
public interface SmsCodeSender {
    void send(String mobile, String code);
}
