package com.msgHelper.msghelper.filter;

import cn.hutool.crypto.digest.DigestUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;

import java.io.*;
import java.util.Map;
import java.util.TreeSet;

@Slf4j
public class SignAuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化成功");
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
                if(s.equals("signature")) continue;
                String temp = map.get(s)[0];
                if (temp.equals("")) continue;
                data.append(s);
                data.append("=");
                data.append(temp);
                data.append("&");
            }
            data.deleteCharAt(data.length()-1);
            log.info("排序后的数据为：{}", data);

        }else {

            // 这里将原始request传入，读出流并存储
            BodyReaderRequestWrapper requestWrapper = new BodyReaderRequestWrapper(request);

            String requestBody = requestWrapper.getRequestBody();
            data.append(requestBody.split("name=\"data\"")[1].trim().split("------")[0].trim());

            //将String对象转为JSON对象
            //JSONObject jsonObject = JSONObject.parseObject(String.valueOf(data));
            log.info("这是Post请求中的data数据,{}",data);
        }

        //获取token
        String token = request.getHeader("HTTP_X_YS_ACCOUNT_TOKEN");
        log.info("获取的Token为：{}",token);

        StringBuilder sign = new StringBuilder();
        sign.append(url);
        sign.append(data);

        //sign.append(token);
        log.info("最后拼接的结果为：{}",sign);

        //计算md5
        String md5 = DigestUtil.md5Hex(String.valueOf(sign));
        log.info("最终的md5值是：{}",md5);

        chain.doFilter(req,rep);
    }

    @Override
    public void destroy() {

    }

    public class BodyReaderRequestWrapper extends HttpServletRequestWrapper {
        // 将流中的内容保存
        private final byte[] buff;
        public BodyReaderRequestWrapper(HttpServletRequest request) throws IOException {
            super(request);
            InputStream is = request.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int len;
            while ((len = is.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            buff = baos.toByteArray();
        }
        @Override
        public ServletInputStream getInputStream() throws IOException {
            final ByteArrayInputStream bais = new ByteArrayInputStream(buff);
            return new ServletInputStream() {
                @Override
                public boolean isFinished() {
                    return false;
                }
                @Override
                public boolean isReady() {
                    return false;
                }
                @Override
                public void setReadListener(ReadListener listener) {
                }
                @Override
                public int read() throws IOException {
                    return bais.read();
                }
            };
        }
        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }
        public String getRequestBody() {
            return new String(buff);
        }
    }
}
