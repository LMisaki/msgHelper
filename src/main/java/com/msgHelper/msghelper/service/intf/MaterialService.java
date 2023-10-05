package com.msgHelper.msghelper.service.intf;

import com.msgHelper.msghelper.moodel.entity.Material;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface   MaterialService {
    List<Material> Page();
}
