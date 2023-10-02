package com.msgHelper.msghelper.test;

import com.msgHelper.msghelper.utils.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class MyTest {

    @Test
    public void test() throws Exception {
        String private_Key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOloVBfHgwC4UxL8WwnHuKxxcDYyQqfkIZLzYuuttXeHccUHpPBlEYrp1vSWiR2X5Y6/V8mNd02LFmuG7gT1syf5jNtNvwu67QhcSWyIq3JLj+Jo8VHJKSgTCHYkc5Fgr2rhRtdXgqANiN4wjcupMHf/xZCNU6GOH3TNjDl1768NAgMBAAECgYAkcKMWXlHr0hs4Ltlovrtsgr5d271v2ExMlL6IJIPdEDKZK/8af/D78tOaNolcKyuN/H4Qa8tpOhKLQZLWB+ULB4dXmMqiPxjfk6EpD8Lkje9PwWhthtbpqdk4S8SOWS//GLDxpXC8IFm4ElA5dGC3SsDs7XLFhxNONto0HQ+UeQJBAOtPPqzyry0CEmQpSkFyO5Uu5C4HW/5V7nQXYsS+li2vXWoluUR3h+aYugzZAQsK5iQWnSixf0ejvvBJfaqvM6UCQQD97kUPe1BlIcgp3BMEk8KRIJFTDCoQsgX0Npv9kBYLx1snjT8ELZbW69O/t9/wSjhUnOaCyK2UMZsgVeTDnBFJAkEAmGttSrOsOrgz14NTYQ8wfxKvpQ0JTra0zaf0R0K4noEQPliPKEVQ11KoePf7j/6QUmH/jMNkkbAEVzsTVoNELQJAHcTIHdeEBkcekyG0rQ2xepgJ0WWUrN5h6k5YnTI+E2Vsn0dkwtWhYFLHQSuKOj5JOkomj+B80Om72N+Y8QGj2QJBAIM+FxPbh0pz/yHuNzngusS32ZyepNRjnjDOZDlxnqcClRjyFe/zl8AWdyb1RtgZF5zaB4gkGuDiAN5PH3vdC8s=";
        String public_Key = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDpaFQXx4MAuFMS/FsJx7iscXA2MkKn5CGS82LrrbV3h3HFB6TwZRGK6db0lokdl+WOv1fJjXdNixZrhu4E9bMn+YzbTb8Luu0IXElsiKtyS4/iaPFRySkoEwh2JHORYK9q4UbXV4KgDYjeMI3LqTB3/8WQjVOhjh90zYw5de+vDQIDAQAB";
//        Map<String, String> key = RsaUtil.generateKey();
//        System.out.println(key);

        String result = RsaUtil.encryptByPublicKey("{\"mobile\":\"13811111111\",\"code\":\"1111\"}",public_Key);
        log.info("加密后的数据为：{}",result);

        result =RsaUtil.decryptByPrivateKey("nn8/j1jRmaYhNVJMngyi6QY2/sbKAfu8/9WHulPOSi9mu8b5v5zv/rkdh3pEEXsKxSJvbORFpKSj7jvPjRRLMq75Vd4YUhaqEiEqedi3SnlaUkg9563xYxg5Pb3uiHgdPk0jY5tfOZvdrGZ8py7EHkr8F7i80y/hZHm/J5xAXdI=",private_Key);
        log.info("加密后的数据为：{}",result);
    }

    @Autowired
    DataSource dataSource;
    @Test
    void contextLoads() {
        //查看数据源
        System.out.println(dataSource.getClass());
        //获得数据库连接
        try {
            Connection connection = dataSource.getConnection();
            System.out.println(connection);
            //关闭连接
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
