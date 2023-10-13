package com.msgHelper.msghelper.controller;


import com.msgHelper.msghelper.annotation.ParameterModel;
import com.msgHelper.msghelper.moodel.dto.MaterialDTO;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.moodel.vo.MaterialVO;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.service.intf.MaterialService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.msgHelper.msghelper.constant.TypeConstant.STATUS_ENABLE;

@Slf4j
@RestController
@RequestMapping("/msghelper/api/v1/MaterialDetail/List")
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    /**
     * Desc: 分页查询
     * @return {@link Result<MaterialVO>}
     * @author L_Misaki
     */
    @GetMapping
    public Result<MaterialVO> Page(@ParameterModel MaterialDTO materialDTO, @RequestHeader(value = "HTTP_X_YS_ACCOUNT_ID") Integer accountId,@RequestHeader(value = "HTTP_X_YS_ACCOUNT_TOKEN") String token){

        materialDTO.setMaterialLibId(accountId);
        materialDTO.setStatus(STATUS_ENABLE);

        log.info("分页查询内容:{}",materialDTO);

        List<Material> list= materialService.pageQuery(materialDTO);
        MaterialVO vo = MaterialVO.builder()
                .list(list)
                .build();

        return Result.success(vo);
    }
}
