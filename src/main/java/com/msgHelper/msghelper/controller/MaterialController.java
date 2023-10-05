package com.msgHelper.msghelper.controller;


import com.msgHelper.msghelper.mapper.MaterialMapper;
import com.msgHelper.msghelper.moodel.entity.Material;
import com.msgHelper.msghelper.moodel.vo.MaterialVO;
import com.msgHelper.msghelper.result.Result;
import com.msgHelper.msghelper.service.intf.MaterialService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/msghelper/api/v1/MaterialDetail/List")
public class MaterialController {

    @Autowired
    private MaterialService materialService;


    //TODO 暂时没解决参数问题，后面要改成分页查询
    /**
     * Desc: 分页查询
     * @param str
     * @return {@link Result< MaterialVO>}
     * @author L_Misaki
     */
    @GetMapping
    public Result<MaterialVO> Page(String str){
        log.info("接收的消息为:{}",str);

        List<Material> list= materialService.Page();

        MaterialVO vo = MaterialVO.builder()
                .list(list)
                .build();
        return Result.success(vo);
    }
}
