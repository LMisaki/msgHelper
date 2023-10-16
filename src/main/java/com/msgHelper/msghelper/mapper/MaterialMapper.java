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

    List<Material> pageQuery(Material material);

    void insert(Material material);

    void update(List<Material> list);

    @Select("select * from msghelper.material where id = #{id}")
    Material selectById(Integer id);
    @Update("update msghelper.material set sort_index=id where id = #{id} ")
    void setSort(Integer id);


    @Select("select * from msghelper.material where sort_index< #{sortIndex} order by sort_index DESC limit 0,1 ")
    Material getPrevious(Integer sortIndex);

    @Select("select * from msghelper.material where sort_index> #{sortIndex} order by sort_index ASC limit 0,1 ")
    Material getNext(Integer sortIndex);
}
