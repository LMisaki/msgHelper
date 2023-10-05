package com.msgHelper.msghelper.moodel.vo;

import com.msgHelper.msghelper.moodel.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialVO {
    private List<Material> list;
}
