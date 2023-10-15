package com.msgHelper.msghelper.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.msgHelper.msghelper.mapper.MaterialMapper;
import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialSearchDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.service.intf.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.msgHelper.msghelper.constant.TypeConstant.STATUS_ENABLE;

@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> pageQuery(MaterialSearchDTO materialSearchDTO) {

        Page<Object> page = PageHelper.startPage(materialSearchDTO.getPage(), materialSearchDTO.getPageSize());

        //TODO 分页查询优化正则表达式
        /*将 空格 替换为 .* 用于正则表达式 查找关键字*/
/*        if (materialSearchDTO.getKeywords() !=  "" ){
//            materialDTO.setKeywords(materialDTO.getKeywords().replace(" ", ".*"));
            materialSearchDTO.setKeywords("con.*");
        }*/

        log.info("查询关键词为：{}", materialSearchDTO.getKeywords());
        List<Material> list = materialMapper.pageQuery(materialSearchDTO);
        return list;

    }

    @Override
    public void CreateMaterial(MaterialDTO materialDTO) {

        materialDTO.setCreateTime(LocalDateTime.now());
        materialDTO.setUpdateTime(LocalDateTime.now());
        materialDTO.setStatus(STATUS_ENABLE);

        materialMapper.insert(materialDTO);
    }

    @Override
    public void ModifyMaterial(MaterialDTO materialDTO) {
        materialDTO.setUpdateTime(LocalDateTime.now());

        materialMapper.update(materialDTO);
    }
}
