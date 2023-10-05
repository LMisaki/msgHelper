package com.msgHelper.msghelper.service.impl;

import com.msgHelper.msghelper.mapper.MaterialMapper;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.service.intf.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    private MaterialMapper materialMapper;
    @Override
    public List<Material> Page() {
        return materialMapper.Page();
    }
}
