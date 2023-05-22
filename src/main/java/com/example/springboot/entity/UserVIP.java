package com.example.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName("userviptable")
@Data
public class UserVIP {

    @TableId(type = IdType.AUTO)

    private String username;

    private String email;
    private String vip;

}
