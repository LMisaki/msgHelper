package com.msgHelper.msghelper.controller;

import com.msgHelper.msghelper.moodel.entity.MaterialGroup;
import com.msgHelper.msghelper.moodel.vo.MaterialGroupVO;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.service.intf.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/msghelper/api/v1/MaterialLibGroup/List")
public class MaterialLibGroupController {

    @Autowired
    private GroupService groupService;

    //TODO 需要改写成先获取用户，再获取用户id的分组
    @GetMapping
    public Result<MaterialGroupVO> GetAllGroups(String str){
        log.info("接收的参数为：{}",str);

        List<MaterialGroup> list =groupService.getAllGroups();

        MaterialGroupVO vo = MaterialGroupVO.builder()
                .list(list)
                .build();
        return Result.success(vo);
    }
}
