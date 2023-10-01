package com.msgHelper.msghelper.controller;


import com.alibaba.fastjson.JSONObject;
import com.msgHelper.msghelper.moodel.vo.UserLoginVO;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.utils.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/msghelper/api/v1/Material")
public class UserController {

    //TODO RSA私钥直接写在这不安全，需要更改，暂时是忽略登录连接的参数，直接拦截，也要改
    final static String private_Key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOloVBfHgwC4UxL8WwnHuKxxcDYyQqfkIZLzYuuttXeHccUHpPBlEYrp1vSWiR2X5Y6/V8mNd02LFmuG7gT1syf5jNtNvwu67QhcSWyIq3JLj+Jo8VHJKSgTCHYkc5Fgr2rhRtdXgqANiN4wjcupMHf/xZCNU6GOH3TNjDl1768NAgMBAAECgYAkcKMWXlHr0hs4Ltlovrtsgr5d271v2ExMlL6IJIPdEDKZK/8af/D78tOaNolcKyuN/H4Qa8tpOhKLQZLWB+ULB4dXmMqiPxjfk6EpD8Lkje9PwWhthtbpqdk4S8SOWS//GLDxpXC8IFm4ElA5dGC3SsDs7XLFhxNONto0HQ+UeQJBAOtPPqzyry0CEmQpSkFyO5Uu5C4HW/5V7nQXYsS+li2vXWoluUR3h+aYugzZAQsK5iQWnSixf0ejvvBJfaqvM6UCQQD97kUPe1BlIcgp3BMEk8KRIJFTDCoQsgX0Npv9kBYLx1snjT8ELZbW69O/t9/wSjhUnOaCyK2UMZsgVeTDnBFJAkEAmGttSrOsOrgz14NTYQ8wfxKvpQ0JTra0zaf0R0K4noEQPliPKEVQ11KoePf7j/6QUmH/jMNkkbAEVzsTVoNELQJAHcTIHdeEBkcekyG0rQ2xepgJ0WWUrN5h6k5YnTI+E2Vsn0dkwtWhYFLHQSuKOj5JOkomj+B80Om72N+Y8QGj2QJBAIM+FxPbh0pz/yHuNzngusS32ZyepNRjnjDOZDlxnqcClRjyFe/zl8AWdyb1RtgZF5zaB4gkGuDiAN5PH3vdC8s=";
    @PostMapping("/**")
    public Result<UserLoginVO> Login(String data) throws Exception {
        //接收来自前端的信息，并且使用私钥解密
        String jsonData = RsaUtil.decryptByPrivateKey(data,private_Key);
        log.info("解密后的数据为：{}",jsonData);

        //从数据库中查询数据，返回到VO对象
        JSONObject jsonObject = JSONObject.parseObject(jsonData);
        UserLoginVO userLoginVO = UserLoginVO.builder().
                user_token("111111111111")
                .user_id(jsonObject.getString("mobile"))
                .build();
        log.info("登录User信息为：{}",userLoginVO);

        return Result.success(userLoginVO);
    }
}
