package com.msgHelper.msghelper.moodel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SortLibGroupDTO {

    private Integer id;
    private String sort_data;

    private String timestamp;

    @JsonProperty(value = "material_lib_id")
    private Integer materialLibId;
}

