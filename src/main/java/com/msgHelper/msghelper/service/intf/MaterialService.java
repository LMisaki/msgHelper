package com.msgHelper.msghelper.service.intf;

import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialSearchDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface   MaterialService {

    List<Material> pageQuery(MaterialSearchDTO materialSearchDTO);

    void CreateMaterial(MaterialDTO materialTO);

    void ModifyMaterial(MaterialDTO materialDTO);
}
