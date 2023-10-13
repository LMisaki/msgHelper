package com.msgHelper.msghelper.mapper;

import com.msgHelper.msghelper.moodel.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from msghelper.user where mobile= #{mobile} and status =#{status}")
    User getByIphone(String mobile,Integer status);
}
