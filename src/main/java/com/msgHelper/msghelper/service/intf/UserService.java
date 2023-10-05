package com.msgHelper.msghelper.service.intf;

import com.msgHelper.msghelper.moodel.entity.User;
import com.msgHelper.msghelper.moodel.vo.UserLoginVO;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    User Login(String data) throws Exception;
}
