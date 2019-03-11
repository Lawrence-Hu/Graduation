package com.ene.ego.beans;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data

@TableName(value = "Orders")
public class Order {
    @TableId(value = "number", type = IdType.AUTO)
    private Integer number;
    @TableField
    private Integer userId;
    @TableField
    private Integer statusId;
    @TableField

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date time;//创建时间
    @TableField
    private float price;


}
