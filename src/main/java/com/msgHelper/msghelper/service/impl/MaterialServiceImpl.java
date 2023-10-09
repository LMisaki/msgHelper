package com.msgHelper.msghelper.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.msgHelper.msghelper.mapper.MaterialMapper;
import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.moodel.vo.MaterialVO;
import com.msgHelper.msghelper.service.intf.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> pageQuery(MaterialDTO materialDTO) {
        Page<Object> page = PageHelper.startPage(materialDTO.getPage(), materialDTO.getPageSize());
        log.info("分页查询属性为：{}",page);

        /*将 空格 替换为 .* 用于正则表达式 查找关键字*/
        if (materialDTO.getKeywords() !=  null){
//            materialDTO.setKeywords(materialDTO.getKeywords().replace(" ", ".*"));
            materialDTO.setKeywords("con.*");
        }

        log.info("查询关键词为：{}",materialDTO.getKeywords());
        List<Material> list = materialMapper.pageQuery(materialDTO);
        return list;



    }
}
