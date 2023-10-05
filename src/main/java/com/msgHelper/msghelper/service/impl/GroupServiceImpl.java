package com.msgHelper.msghelper.service.impl;

import com.msgHelper.msghelper.mapper.MaterialGroupMapper;
import com.msgHelper.msghelper.moodel.entity.MaterialGroup;
import com.msgHelper.msghelper.service.intf.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    @Autowired
    private MaterialGroupMapper materialGroupMapper;
    /**
     * Desc: 返回所有分组
     * @param
     * @return {@link List<MaterialGroup>}
     * @author L_Misaki
     */
    public List<MaterialGroup> getAllGroups() {
        return materialGroupMapper.getAllGroups();
    }
}
