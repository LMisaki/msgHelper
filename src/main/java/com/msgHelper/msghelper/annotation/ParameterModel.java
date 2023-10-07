package com.msgHelper.msghelper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实体映射注解
 * 配置该注解的参数会使用 UnderlineToCamelArgumentResolver 类完成装载
 * 将前端的下划线命名在Get请求中也可以转换成驼峰命名
 */
@Target(value = ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParameterModel {
}