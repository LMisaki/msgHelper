package com.msgHelper.msghelper.service.intf;


import com.msgHelper.msghelper.moodel.dto.MaterialLibGroupDTO;
import com.msgHelper.msghelper.moodel.entity.MaterialLibGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialLibGroupService {
    List<MaterialLibGroup> getAllGroups(Integer materialLibId);

    void creatLibGroup(MaterialLibGroupDTO materialLibGroupDTO);

    void ModifyLibGroup(MaterialLibGroupDTO materialLibGroupDTO);
}
