package com.msgHelper.msghelper.controller;

import com.msgHelper.msghelper.annotation.ParameterModel;
import com.msgHelper.msghelper.moodel.dto.MaterialLibGroupDTO;
import com.msgHelper.msghelper.moodel.entity.MaterialLibGroup;
import com.msgHelper.msghelper.moodel.vo.MaterialGroupVO;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.service.intf.MaterialLibGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/msghelper/api/v1/MaterialLibGroup")
public class MaterialLibGroupController {

    @Autowired
    private MaterialLibGroupService materialLibGroupService;

    //TODO 需要改写成先获取用户，再获取用户id的分组
    /**
     * Desc: 获取全部有效的分组别表
     * @param materialLibGroupDTO
     * @param accountId
     * @param token
     * @return {@link Result<MaterialGroupVO>}
     * @author L_Misaki
     */
    @GetMapping("/List")
    public Result<MaterialGroupVO> GetAllLibGroups(@ParameterModel MaterialLibGroupDTO materialLibGroupDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialLibGroupDTO.setMaterialLibId(accountId);
        log.info("获取全部分组，接收参数：{}",materialLibGroupDTO);

        //返回所有分组，如果是新增，则先新增再返回所有
        List<MaterialLibGroup> list = materialLibGroupService.getAllGroups(accountId);

        MaterialGroupVO vo = MaterialGroupVO.builder()
                .list(list)
                .build();
        return Result.success(vo);
    }

    /**
     * Desc: 创建分组
     * @param materialLibGroupDTO
     * @param accountId
     * @param token
     * @return {@link Result}
     * @author L_Misaki
     */
    @PostMapping("/Create")
    public Result CreateLibGroup(@ParameterModel MaterialLibGroupDTO  materialLibGroupDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialLibGroupDTO.setMaterialLibId(accountId);
        log.info("创建一个新的分组：{}",materialLibGroupDTO);

        materialLibGroupService.creatLibGroup(materialLibGroupDTO);

        return Result.success();
    }

    /**
     * Desc: 修改分组
     * @param materialLibGroupDTO
     * @param accountId
     * @param token
     * @return {@link Result}
     * @author L_Misaki
     */
    @PostMapping("/Modify")
    public Result ModifyLibGroup(@ParameterModel MaterialLibGroupDTO  materialLibGroupDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialLibGroupDTO.setMaterialLibId(accountId);

        log.info("修改当前分组：{}",materialLibGroupDTO);
        materialLibGroupService.ModifyLibGroup(materialLibGroupDTO);

        return Result.success();
    }
}
