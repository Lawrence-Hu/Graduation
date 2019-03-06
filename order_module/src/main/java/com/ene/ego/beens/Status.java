package com.ene.ego.beens;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data

@TableName("status")
public class Status {
    @TableId
    private int id;
    @TableField
    private String status;
}