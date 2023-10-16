package com.msgHelper.msghelper.moodel.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    private Integer id;

    //不同用户的分组仓库id
    @JsonProperty(value = "material_lib_id")
    private Integer materialLibId;

    //分组id
    @JsonProperty(value = "material_lg_id")
    private Integer materialLgId;

    //类型，短语？图文？文件？，分别是1，2，3
    @JsonProperty(value = "content_type")
    private Integer contentType;

    //高光？不知道干嘛的
    private String highlight;

    //名字
    @JsonProperty(value = "material_name")
    private String materialName;

    //内容
    @JsonProperty(value = "material_content")
    private String materialContent;

    //详细内容
    @JsonProperty(value = "content_detail")
    private String contentDetail;

    //文件URL
    @JsonProperty(value = "file_url")
    private String fileUrl;

    //文件名
    @JsonProperty(value = "file_name")
    private String fileName;

    //文件大小
    @JsonProperty(value = "file_size")
    private Long fileSize;

    //排序,界面显示顺序
    @JsonProperty(value = "sort_index")
    private Integer sortIndex;

    //当前状态
    private Integer status;

    //创建类型，也不知道干嘛的，好像是组别的类型
    @JsonProperty(value = "create_type")
    private Integer createType;

    @JsonProperty(value = "create_time")
    private LocalDateTime createTime;

    @JsonProperty(value = "update_time")
    private LocalDateTime updateTime;
}
