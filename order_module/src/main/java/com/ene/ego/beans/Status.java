package com.ene.ego.beans;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data

@TableName("status")
public class Status {
    @TableId(value = "id")
    private String id;
    @TableField
    private String status;
}