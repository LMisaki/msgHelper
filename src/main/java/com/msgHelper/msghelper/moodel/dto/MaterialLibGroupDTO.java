package com.msgHelper.msghelper.moodel.dto;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MaterialLibGroupDTO {

    //数据库ID
    private Integer id;

    //不同用户的分组仓库id
    @JsonProperty(value = "material_lib_id")
    private Integer materialLibId;

    //分组名称
    @JsonProperty(value = "group_name")
    private String groupName;

    //创建分组,
    @JsonProperty(value = "create_type")
    private  Integer createType;

    //分组状态
    private  Integer status;

    @JsonProperty(value = "update_time")
    private LocalDateTime updateTime;

    @JsonProperty(value = "create_time")
    private LocalDateTime createTime;
}
