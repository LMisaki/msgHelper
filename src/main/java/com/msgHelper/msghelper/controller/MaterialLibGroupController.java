package com.msgHelper.msghelper.controller;

import com.msgHelper.msghelper.moodel.entity.MaterialGroup;
import com.msgHelper.msghelper.moodel.vo.MaterialGroupVO;
import com.msgHelper.msghelper.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/msghelper/api/v1/MaterialLibGroup/List")
public class MaterialLibGroupController {

    @GetMapping
    public Result<MaterialGroupVO> GetGroups(String str){
        log.info("接收的参数为：{}",str);
        MaterialGroup grup1 = MaterialGroup.builder()
                .id(1)
                .group_name("test1")
                .create_type(2)
                .build();
        MaterialGroup grup2 = MaterialGroup.builder()
                .id(2)
                .group_name("test2")
                .create_type(1)
                .build();

        List<MaterialGroup> listGroup = new ArrayList<>();
        listGroup.add(grup1);
        listGroup.add(grup2);

        MaterialGroupVO ans = MaterialGroupVO.builder()
                .list(listGroup)
                .build();

        return Result.success(ans);
    }
}
