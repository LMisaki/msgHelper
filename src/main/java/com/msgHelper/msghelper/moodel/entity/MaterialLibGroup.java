package com.msgHelper.msghelper.moodel.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialLibGroup implements Serializable {
    @Serial
    private static final long serialVersionUID = 8988769824904497024L;

    //id
    private Integer id;

    //分组名称
    @JsonProperty(value = "group_name")
    private String groupName;

    //分组类型，1为官方，2为非官方
    @JsonProperty(value = "create_type")
    private Integer createType;

    //创建时间
    @JsonProperty(value = "create_time")
    private LocalDateTime createTime;

    //修改时间
    @JsonProperty(value = "update_time")
    private LocalDateTime updateTime;
}
