package com.msgHelper.msghelper.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.msgHelper.msghelper.mapper.MaterialMapper;
import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialSearchDTO;
import com.msgHelper.msghelper.moodel.dto.SortMaterialDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.service.intf.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.msgHelper.msghelper.constant.TypeConstant.STATUS_ENABLE;

@Service
@Slf4j
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;

    @Override
    public List<Material> pageQuery(MaterialSearchDTO materialSearchDTO) {

        materialSearchDTO.setStatus(STATUS_ENABLE);
        materialSearchDTO.setKeywords(materialSearchDTO.getKeywords().replace(' ','|'));

        Material material = new Material();
        BeanUtils.copyProperties(materialSearchDTO,material);

        Page<Object> page = PageHelper.startPage(materialSearchDTO.getPage(), materialSearchDTO.getPageSize());

        log.info("查询关键词为：{}", materialSearchDTO.getKeywords());

        return materialMapper.pageQuery(material);
    }

    @Override
    public void CreateMaterial(MaterialDTO materialDTO) {

        materialDTO.setCreateTime(LocalDateTime.now());
        materialDTO.setUpdateTime(LocalDateTime.now());
        materialDTO.setStatus(STATUS_ENABLE);

        Material material = new Material();
        BeanUtils.copyProperties(materialDTO,material);

        materialMapper.insert(material);
        //排序索引，初始化和主键保持一致
        materialMapper.setSort(material.getId());

    }

    @Override
    public void ModifyMaterial(MaterialDTO materialDTO) {
        materialDTO.setUpdateTime(LocalDateTime.now());

        Material material = new Material();
        BeanUtils.copyProperties(materialDTO,material);

        List<Material> list = new ArrayList<>();
        list.add(material);
        materialMapper.update(list);
    }

    @Override
    public void SortMaterial(SortMaterialDTO sortMaterialDTO) {

        //获取源消息的信息
        Material srcMaterial = materialMapper.selectById(sortMaterialDTO.getId());
        //目标消息
        Material tarMaterial;

        //获取目标消息的信息
        if (sortMaterialDTO.getType()==1) {
            tarMaterial = materialMapper.getPrevious(srcMaterial.getSortIndex());  //上移
        }else {
            tarMaterial = materialMapper.getNext(srcMaterial.getSortIndex());  //下移
        }

        //交换sort_index
        Integer temp = srcMaterial.getSortIndex();
        srcMaterial.setSortIndex(tarMaterial.getSortIndex());
        tarMaterial.setSortIndex(temp);

        List<Material> list = new ArrayList<>();
        list.add(srcMaterial);
        list.add(tarMaterial);

        materialMapper.update(list);
    }
}
