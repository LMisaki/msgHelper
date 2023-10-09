package com.msgHelper.msghelper.moodel.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialLibGroupDTO {

    //不同用户的分组仓库id
    @JsonProperty(value = "material_lib_id")
    private Integer materialLibId;

    //创建分组,null为不创建，不知道干嘛的
    @JsonProperty(value = "create_type")
    private  Integer createType;
    //签名信息
    private String signature;
}
