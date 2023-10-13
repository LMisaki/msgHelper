package com.msgHelper.msghelper.config;



import com.msgHelper.msghelper.annotation.UnderlineToCamelArgumentResolver;
import com.msgHelper.msghelper.filter.SignAuthFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 配置类，注册web层相关组件
 */
@Configuration
@Slf4j
public class WebMvcConfiguration implements WebMvcConfigurer {

    /*将下划线命名转化成驼峰，*/
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UnderlineToCamelArgumentResolver(true));
    }

/*    @Bean
    public FilterRegistrationBean registrationBean() {
       FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new SignAuthFilter());
       filterRegistrationBean.addUrlPatterns("/msghelper/api/v1/MaterialDetail/*");
       filterRegistrationBean.addUrlPatterns("/msghelper/api/v1/MaterialLibGroup/*");
       filterRegistrationBean.addInitParameter("Login", "/msghelper/api/v1/Material/*");

       return filterRegistrationBean;
    }*/
}
