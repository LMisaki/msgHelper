package com.msgHelper.msghelper.annotation;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ServletModelAttributeMethodProcessor;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将请求参数带有下划线转驼峰命名
 */
public class UnderlineToCamelArgumentResolver extends ServletModelAttributeMethodProcessor {

    public UnderlineToCamelArgumentResolver(boolean annotationNotRequired) {
        super(annotationNotRequired);
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(ParameterModel.class);
    }

    protected void bindRequestParameters(WebDataBinder binder, NativeWebRequest request) {
        // 将key-value封装为map，传给bind方法进行参数值绑定
        Map<String, String> map = new HashMap<>();
        Map<String, String[]> params = request.getParameterMap();

        for (Map.Entry<String, String[]> entry : params.entrySet()) {
            String name = entry.getKey();
            // 执行urldecode
            // String value = URLDecoder.decode(entry.getValue()[0], "UTF-8");
            String value = entry.getValue()[0];
            map.put(underLineToCamel(name), value);
        }

        PropertyValues propertyValues = new MutablePropertyValues(map);

        // 将K-V绑定到binder.target属性上
        binder.bind(propertyValues);
    }

    private String underLineToCamel(String source) {
        Matcher matcher = Pattern.compile("_(\\w)").matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }
}