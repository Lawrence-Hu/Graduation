package com.ene.ego.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data

@TableName("status")
public class Status {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField
    private String status;
}