package com.msgHelper.msghelper.config;



import com.msgHelper.msghelper.annotation.UnderlineToCamelArgumentResolver;
import com.msgHelper.msghelper.filter.HttpServletRequestReplacedFilter;
import com.msgHelper.msghelper.filter.HttpServletRequestReplacedInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    public void addInterceptors(InterceptorRegistry registry) {
        //拦截
        InterceptorRegistration registration = registry.addInterceptor(new HttpServletRequestReplacedInterceptor());

        registration.addPathPatterns("/msghelper/api/v1/MaterialDetail/*");
        registration.addPathPatterns("/msghelper/api/v1/MaterialLibGroup/*");
        registration.excludePathPatterns("Login", "/msghelper/api/v1/Material/*");
    }

    @Bean
    public FilterRegistrationBean httpServletRequestReplacedRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new HttpServletRequestReplacedFilter());
        registration.addUrlPatterns("/msghelper/api/v1/MaterialDetail/*");
        registration.addUrlPatterns("/msghelper/api/v1/MaterialLibGroup/*");
        registration.addInitParameter("Login", "/msghelper/api/v1/Material/*");
        registration.setOrder(1);
        return registration;
    }
}
