package com.msgHelper.msghelper.mapper;

import com.msgHelper.msghelper.moodel.dto.MaterialLibGroupDTO;
import com.msgHelper.msghelper.moodel.entity.MaterialLibGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface MaterialLibGroupMapper {

    @Select("select * from msghelper.material_lib_group where (material_lib_id = #{materialLibId} or create_type = #{createType}) and status = #{status}")
    List<MaterialLibGroup> getAllGroupsById(Integer materialLibId, Integer createType, Integer status);

    void insert(MaterialLibGroupDTO materialLibGroupDTO);

    void UpdateLibGroup(MaterialLibGroupDTO materialLibGroupDTO);

}
