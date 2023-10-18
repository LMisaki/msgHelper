package com.msgHelper.msghelper.controller;


import com.msgHelper.msghelper.annotation.ParameterModel;
import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.dto.MaterialSearchDTO;
import com.msgHelper.msghelper.moodel.dto.SortMaterialDTO;
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

        log.info("分页查询内容:{}", materialSearchDTO);

        List<Material> list= materialService.pageQuery(materialSearchDTO);
        MaterialVO vo = MaterialVO.builder()
                .list(list)
                .build();

        return Result.success(vo);
    }

    /**
     * Desc: 创建物料
     * @param materialDTO
     * @param accountId
     * @param token
     * @return {@link Result}
     * @author L_Misaki
     */
    @PostMapping("/Create")
    public Result CreateMaterial(@ParameterModel MaterialDTO materialDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialDTO.setMaterialLibId(accountId);

        log.info("创建消息，参数为：{}", materialDTO);

        materialService.CreateMaterial(materialDTO);

        return Result.success();
    }

    /**
     * Desc: 修改内容（包括状态）
     * @param materialDTO
     * @param accountId
     * @param token
     * @return {@link Result}
     * @author L_Misaki
     */
    @PostMapping("/Modify")
    public Result ModifyMaterial(@ParameterModel MaterialDTO materialDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        materialDTO.setMaterialLibId(accountId);

        log.info("修改消息，参数为：{}", materialDTO);

        materialService.ModifyMaterial(materialDTO);

        return Result.success();
    }

    /**
     * Desc: 修改位置
     * @param sortMaterialDTO
     * @param accountId
     * @param token
     * @return {@link Result}
     * @author L_Misaki
     */
    @PostMapping("/ModifyLocation")
    public Result SortMaterial(@ParameterModel SortMaterialDTO sortMaterialDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){
        sortMaterialDTO.setMaterialLibId(accountId);

        log.info("排序消息，参数为：{}", sortMaterialDTO);

        materialService.SortMaterial(sortMaterialDTO);

        return Result.success();
    }
}
