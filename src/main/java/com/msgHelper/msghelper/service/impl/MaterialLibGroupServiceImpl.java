package com.msgHelper.msghelper.service.impl;

import com.msgHelper.msghelper.mapper.MaterialLibGroupMapper;
import com.msgHelper.msghelper.moodel.dto.MaterialLibGroupDTO;
import com.msgHelper.msghelper.moodel.dto.SortLibGroupDTO;
import com.msgHelper.msghelper.moodel.dto.SortMaterialDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.moodel.entity.MaterialLibGroup;
import com.msgHelper.msghelper.service.intf.MaterialLibGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static com.msgHelper.msghelper.constant.TypeConstant.*;


@Service
@Slf4j
public class MaterialLibGroupServiceImpl implements MaterialLibGroupService {

    @Autowired
    private MaterialLibGroupMapper materialLibGroupMapper;

    /**
     * Desc: 返回所有分组
     *
     * @param
     * @return {@link List< MaterialLibGroup >}
     * @author L_Misaki
     */
    public List<MaterialLibGroup> getAllGroups(Integer materialLibId) {
        // 查询所有个人、官方并且为启用状态的分组
        return materialLibGroupMapper.getAllGroupsById(materialLibId,MATERIAL_GROUP_OFFICIAL,STATUS_ENABLE);
    }

    @Override
    public void creatLibGroup(MaterialLibGroupDTO materialLibGroupDTO) {
        //设置创建时间、更新时间、分组类型
        materialLibGroupDTO.setCreateTime(LocalDateTime.now());
        materialLibGroupDTO.setUpdateTime(LocalDateTime.now());
        materialLibGroupDTO.setCreateType(MATERIAL_GROUP_PERSONAL);
        materialLibGroupDTO.setStatus(STATUS_ENABLE);

        MaterialLibGroup materialLibGroup = new MaterialLibGroup();
        BeanUtils.copyProperties(materialLibGroupDTO,materialLibGroup);

        materialLibGroupMapper.insert(materialLibGroup);
        //排序索引，初始化和主键保持一致
        materialLibGroupMapper.setSort(materialLibGroup.getId());
    }

    @Override
    public void ModifyLibGroup(MaterialLibGroupDTO materialLibGroupDTO) {
        materialLibGroupDTO.setUpdateTime(LocalDateTime.now());

        MaterialLibGroup materialLibGroup = new MaterialLibGroup();
        BeanUtils.copyProperties(materialLibGroupDTO,materialLibGroup);
        List<MaterialLibGroup> list = new ArrayList<>();
        list.add(materialLibGroup);

        //更新数据
        materialLibGroupMapper.UpdateLibGroup(list);
    }


    @Override
    public void ModifyLocation(List<MaterialLibGroup> list) {

        //更新数据
        materialLibGroupMapper.UpdateLibGroup(list);
    }
}
