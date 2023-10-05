package com.msgHelper.msghelper.mapper;

import com.msgHelper.msghelper.moodel.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MaterialMapper {

    @Select("select * from msghelper.material")
    List<Material> Page();
}
