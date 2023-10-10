package com.msgHelper.msghelper.service.intf;


import com.msgHelper.msghelper.moodel.entity.MaterialLibGroup;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GroupService {
    List<MaterialLibGroup> getAllGroups(Integer accountId);
}
