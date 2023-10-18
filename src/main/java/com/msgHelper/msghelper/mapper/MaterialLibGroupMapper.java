package com.msgHelper.msghelper.mapper;

import com.msgHelper.msghelper.moodel.dto.MaterialLibGroupDTO;
import com.msgHelper.msghelper.moodel.entity.MaterialLibGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


@Mapper
public interface MaterialLibGroupMapper {

    /**
     * Desc: 通过id返回分组
     * @param materialLibId
     * @param createType
     * @param status
     * @return {@link java.util.List<com.msgHelper.msghelper.moodel.entity.MaterialLibGroup>}
     * @author L_Misaki
     */
    @Select("select * from msghelper.material_lib_group where (material_lib_id = #{materialLibId} or create_type = #{createType}) and status = #{status} order by sort")
    List<MaterialLibGroup> getAllGroupsById(Integer materialLibId, Integer createType, Integer status);

    /**
     * Desc: 插入分组
     * @param materialLibGroup
     * @return
     * @author L_Misaki
     */
    void insert(MaterialLibGroup materialLibGroup);

    /**
     * Desc: 在插入数据时这是sort与id同步
     * @param id
     * @return
     * @author L_Misaki
     */
    @Update("update msghelper.material_lib_group set sort = #{id} where id = #{id}")
    void setSort(Integer id);

    /**
     * Desc: 更新分组信息（包括状态和顺序）
     * @param list
     * @return
     * @author L_Misaki
     */
    void UpdateLibGroup(List<MaterialLibGroup> list);

}
