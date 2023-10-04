package com.msgHelper.msghelper.moodel.entity;

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
public class MaterialGroup implements Serializable {
    @Serial
    private static final long serialVersionUID = 8988769824904497024L;

    private Integer id;
    private String group_name;
    //1为官方，2为非官方
    private Integer create_type;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
}
