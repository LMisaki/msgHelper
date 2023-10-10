package com.msgHelper.msghelper.moodel.vo;

import com.msgHelper.msghelper.moodel.entity.MaterialLibGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialGroupVO {
    private List<MaterialLibGroup> list;
}
