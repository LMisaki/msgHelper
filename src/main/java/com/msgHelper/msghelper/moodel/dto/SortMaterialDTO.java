package com.msgHelper.msghelper.moodel.dto;

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
public class SortMaterialDTO {

    private Integer id;

    //不同用户的分组仓库id
    @JsonProperty(value = "material_lib_id")
    private Integer materialLibId;

    //1为上移，2为下移
    private Integer type;

}
