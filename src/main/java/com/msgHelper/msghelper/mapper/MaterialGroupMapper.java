package com.msgHelper.msghelper.mapper;

import com.msgHelper.msghelper.moodel.entity.MaterialGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MaterialGroupMapper {

    @Select("select * from msghelper.material_lib_group where account_id = #{accountId}")
    List<MaterialGroup> getAllGroupsById(Integer accountId);

}
