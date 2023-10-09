package com.msgHelper.msghelper.moodel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialDTO {

    //不同用户的分组仓库id？？
    @JsonProperty(value = "material_lib_id")
    private Integer materialLibId;

    //当前选中哪个分组的id
    @JsonProperty(value = "material_lg_id")
    private Integer materialLgId;

    //内容分类
    @JsonProperty(value = "content_type")
    private Integer contentType;

    private String keywords;

    //第几页
    private Integer page;

    //每页数据数
    @JsonProperty(value = "page_size")
    private Integer pageSize;

    //签名信息
    private String signature;
}
