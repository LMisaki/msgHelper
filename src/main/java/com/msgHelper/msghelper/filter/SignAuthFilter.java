package com.msgHelper.msghelper.filter;

import cn.hutool.crypto.digest.DigestUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeSet;

@Slf4j
@Order(1)
@WebFilter("/*")
public class SignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化成功");
        System.out.println("过滤器初始化成功");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse rep, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) rep;

        //获取签名函数需要的URL和参数
        StringBuffer url = request.getRequestURL();
        log.info("URL为：{}",url);

        Map<String, String[]> map = request.getParameterMap();// 获取参数映射

        //区分get和post
        String method = request.getMethod();

        StringBuilder data = new StringBuilder();
        if(method.equals("GET")){

            //对参数进行排序，与前端保持一致
            TreeSet<String> set = new TreeSet<>(map.keySet());
            for (String s:set){
                if(s.equals("timestamp") || s.equals("signature")) continue;
                data.append(s);
                data.append("=");
                data.append(Arrays.toString(map.get(s)));
                data.append("&");
            }
            data.deleteCharAt(data.length()-1);
            log.info("排序后的数据为：{}", data);

        }else {

        }

        //获取时间戳
        String time= Arrays.toString(map.get("timestamp"));

        //获取token
        String token = request.getHeader("HTTP_X_YS_ACCOUNT_TOKEN");
        log.info("获取的Token为：{}",token);

        StringBuilder sign = new StringBuilder();
        sign.append(url);
        sign.append(data);
        sign.append(time);
        sign.append(token);
        log.info("最后拼接的结果为：{}",sign);

        //计算md5
        String md5 = DigestUtil.md5Hex(String.valueOf(sign));
        log.info("最终的md5值是：{}",md5);

        chain.doFilter(req,rep);
    }

    @Override
    public void destroy() {

    }
}
