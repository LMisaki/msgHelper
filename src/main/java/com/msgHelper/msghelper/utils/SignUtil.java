package com.msgHelper.msghelper.utils;

import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author 李浩真
 * 签名与验签工具
 */
@Slf4j
public class SignUtil {

    public static String createSign(Map<String, Object> originMap) {

        if (originMap == null) {
            return null;
        }
        originMap = sortMapByKey(originMap);
        StringBuffer originStr = new StringBuffer();
        for (Map.Entry<String, Object> entry : originMap.entrySet()) {
            if (entry.getValue() ==null || entry.getValue()=="") continue;
            originStr.append(entry.getKey() + "=" + entry.getValue());
            originStr.append("&");
        }
        originStr.deleteCharAt(originStr.length()-1);
        log.info("Get请求参数为：{}",originStr);
        return MD5Util.getMD5(Base64.getEncoder().encodeToString(originStr.toString().getBytes(StandardCharsets.UTF_8)));
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        /**
         * 对Map对象的key升序（a->z）排列
         */
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<>(Comparator.naturalOrder());
        sortMap.putAll(map);
        return sortMap;
    }
}
