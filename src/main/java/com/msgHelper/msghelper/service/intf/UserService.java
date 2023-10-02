package com.msgHelper.msghelper.service.intf;

import com.msgHelper.msghelper.moodel.entity.User;
import com.msgHelper.msghelper.moodel.vo.UserLoginVO;

public interface UserService {
    User Login(String data) throws Exception;
}
