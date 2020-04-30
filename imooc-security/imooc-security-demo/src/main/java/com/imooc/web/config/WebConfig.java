package com.imooc.web.config;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置第三方filter
 * @Configuration 代替 @Component
 * 2020/4/20
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor);
    }

//    @Bean
//    public FilterRegistrationBean timeFilter() {
//        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
//        TimeFilter timeFilter = new TimeFilter();
//        registrationBean.setFilter(timeFilter);
//        /**
//         * 配置方式，指定filter在哪些url时起作用
//         */
//        List<String> urls = new ArrayList<>();
//        //所有url都会起作用
//        urls.add("/*");
//        registrationBean.setUrlPatterns(urls);
//        return registrationBean;
//
//    }
}
