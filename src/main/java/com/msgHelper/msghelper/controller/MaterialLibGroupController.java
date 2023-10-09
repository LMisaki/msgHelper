package com.msgHelper.msghelper.controller;

import com.msgHelper.msghelper.annotation.ParameterModel;
import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialLibGroupDTO;
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
    public Result<MaterialGroupVO> GetAllGroups(@ParameterModel MaterialLibGroupDTO materialLibGroupDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialLibGroupDTO.setMaterialLibId(accountId);
        log.info("接收的参数为：{}",materialLibGroupDTO);

        //返回所有分组，如果是新增，则先新增再返回所有
        List<MaterialGroup> list =groupService.getAllGroups(accountId);

        MaterialGroupVO vo = MaterialGroupVO.builder()
                .list(list)
                .build();
        return Result.success(vo);
    }
}
