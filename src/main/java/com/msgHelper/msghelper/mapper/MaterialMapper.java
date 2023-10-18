package com.msgHelper.msghelper.mapper;

import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialSearchDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface MaterialMapper {

    /**
     * Desc: 根据ID返回数据
     * @param id
     * @return {@link Material}
     * @author L_Misaki
     */
    @Select("select * from msghelper.material where id = #{id}")
    Material selectById(Integer id);

    /**
     * Desc: 分页查询内容
     * @param material
     * @return {@link java.util.List<com.msgHelper.msghelper.moodel.entity.Material>}
     * @author L_Misaki
     */
    List<Material> pageQuery(Material material);

    /**
     * Desc: 插入数据
     * @param material
     * @return
     * @author L_Misaki
     */
    void insert(Material material);

    /**
     * Desc: 更新数据（包括状态和顺序）
     * @param list
     * @return
     * @author L_Misaki
     */
    void update(List<Material> list);
    @Update("update msghelper.material set sort_index=id where id = #{id} ")
    void setSort(Integer id);

    /**
     * Desc: 根据sort索引获取前一个的数据
     * @param sortIndex
     * @return {@link Material}
     * @author L_Misaki
     */
    @Select("select * from msghelper.material where sort_index< #{sortIndex} order by sort_index DESC limit 0,1 ")
    Material getPrevious(Integer sortIndex);

    /**
     * Desc: 根据sort索引获取后一个的数据
     * @param sortIndex
     * @return {@link Material}
     * @author L_Misaki
     */
    @Select("select * from msghelper.material where sort_index> #{sortIndex} order by sort_index ASC limit 0,1 ")
    Material getNext(Integer sortIndex);
}
