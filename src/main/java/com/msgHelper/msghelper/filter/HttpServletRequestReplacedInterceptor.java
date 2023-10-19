package com.msgHelper.msghelper.filter;

import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.crypto.digest.MD5;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.utils.HttpHelper;
import com.msgHelper.msghelper.utils.MD5Util;
import com.msgHelper.msghelper.utils.SignUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author 李浩真
 */
@Slf4j
public class HttpServletRequestReplacedInterceptor implements HandlerInterceptor {

    // 进入控制器之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断请求类型
        if (request.getMethod().equals("GET")) {
            return doGet(request, response);
        } else if (request.getMethod().equals("POST")) {
            return doPost(request, response);
        }
        return false;
    }

    /**
     * GET请求
     */
    public Boolean doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        if (!Objects.equals(request.getContentType(), "application/json")) return true;
        //从request获取到所有的参数及其值
        String sign = request.getParameter("signature");
        StringBuffer url = request.getRequestURL();

        if (sign == null) {
            returnCode(request, response, sign);
            return false;
        }

        Enumeration<?> pNames = request.getParameterNames();
        Map<String, Object> map = new HashMap<String, Object>();
        while (pNames.hasMoreElements()) {
            String pName = (String) pNames.nextElement();
            Object pValue = request.getParameter(pName);
            map.put(pName, pValue);
        }
        //移除
        map.remove("signature");

        String newSign = GetNewSign(map,url);

        if (!newSign.equals(sign)) {
            returnCode(request, response, sign);
        }
        return true;
    }

    /**
     * POST请求
     */
    public Boolean doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String bodyString = HttpHelper.getBodyString(request);
        StringBuffer url = request.getRequestURL();
        String sign = request.getParameter("signature");
        String timestamp = request.getParameter("timestamp");

        JSONObject jb = JSONObject.fromObject(bodyString);
        Map<String, Object> map = (Map<String, Object>) jb;
        map.remove("signature");
        map.put("timestamp",timestamp);

        String newSign = GetNewSign(map,url);

        if (!newSign.equals(sign)) {
            returnCode(request, response, sign);
        }
        return true;
    }

    public String GetNewSign(Map map,StringBuffer url){
        Map<String, Object> sortMap = new TreeMap<>(Comparator.naturalOrder());
        sortMap.putAll(map);

        StringBuffer str = new StringBuffer();
        str.append(url);
        for (Map.Entry<String, Object> entry : sortMap.entrySet()) {
            if (entry.getValue() ==null || entry.getValue()=="") continue;
            str.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        str.deleteCharAt(str.length()-1);

        return DigestUtil.md5Hex(String.valueOf((str)));
    }
    /**
     * 验证签名方法:失败，返回false
     * 2000001：签名错误
     */
    public boolean returnCode(HttpServletRequest request, HttpServletResponse response, String sign) throws IOException {
        OutputStream out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");

            out = response.getOutputStream();
            out.write(Result.error(2000001).toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.flush();
            out.close();
        }
        return false;
    }
}
