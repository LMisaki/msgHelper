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
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 8921248580143955186L;

    private int id;

    private String userId;

    //姓名
    private String name;

    //手机号
    private String mobile;

    //性别 0 女 1 男
    private String sex;

    //身份证号
    private String idNumber;

    //账户类型
    private String accountType;

    //注册时间
    private LocalDateTime createTime;
}
