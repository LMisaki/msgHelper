package com.msgHelper.msghelper.mapper;

import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialSearchDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MaterialMapper {

    List<Material> pageQuery(MaterialSearchDTO materialSearchDTO);

    void insert(MaterialDTO materialDTO);

    void update(MaterialDTO materialDTO);
}
