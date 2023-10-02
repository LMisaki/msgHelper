package com.msgHelper.msghelper.controller;


import com.alibaba.fastjson.JSONObject;
import com.msgHelper.msghelper.constant.MessageConstant;
import com.msgHelper.msghelper.moodel.entity.User;
import com.msgHelper.msghelper.moodel.vo.UserLoginVO;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.service.intf.UserService;
import com.msgHelper.msghelper.utils.RsaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/msghelper/api/v1/Material")
public class UserController {

    @Autowired
    private UserService userService;

    //TODO 增加验证码校验
    @PostMapping("/**")
    public Result<UserLoginVO> Login(String data) throws Exception {

        User user =userService.Login(data);
        //如果返回数据为空，说明用户不存在，返回 用户不存在 错误代码
        if (user == null) return Result.error(MessageConstant.ACCOUNT_NOT_FOUND);

        //用户存在，返回VO对象
        UserLoginVO userLoginVO = UserLoginVO.builder().
                user_token("111111111111")
                .user_id(user.getId())
                .build();
        log.info("登录User信息为：{}",userLoginVO);

        return Result.success(userLoginVO);
    }
}
