package com.msgHelper.msghelper.controller;


import com.msgHelper.msghelper.annotation.ParameterModel;
import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialSearchDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.moodel.vo.MaterialVO;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.service.intf.MaterialService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.msgHelper.msghelper.constant.TypeConstant.STATUS_ENABLE;

@Slf4j
@RestController
@RequestMapping("/msghelper/api/v1/MaterialDetail")
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    /**
     * Desc: 分页查询
     * @return {@link Result<MaterialVO>}
     * @author L_Misaki
     */
    @GetMapping("/List")
    public Result<MaterialVO> Page(@ParameterModel MaterialSearchDTO materialSearchDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){

        materialSearchDTO.setMaterialLibId(accountId);
        materialSearchDTO.setStatus(STATUS_ENABLE);

        log.info("分页查询内容:{}", materialSearchDTO);

        List<Material> list= materialService.pageQuery(materialSearchDTO);
        MaterialVO vo = MaterialVO.builder()
                .list(list)
                .build();

        return Result.success(vo);
    }

    @PostMapping("/Create")
    public Result CreateMaterial(@ParameterModel MaterialDTO materialDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialDTO.setMaterialLibId(accountId);

        log.info("创建消息，参数为：{}", materialDTO);

        materialService.CreateMaterial(materialDTO);

        return Result.success();
    }

    @PostMapping("/Modify")
    public Result ModifyMaterial(@ParameterModel MaterialDTO materialDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialDTO.setMaterialLibId(accountId);

        log.info("修改消息，参数为：{}", materialDTO);

        materialService.ModifyMaterial(materialDTO);

        return Result.success();
    }
}
