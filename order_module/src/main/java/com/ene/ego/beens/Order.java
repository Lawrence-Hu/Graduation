package com.ene.ego.beens;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Getter
@Setter


@TableName(value = "Orders")
public class Order {
    @TableId
    private int number;
    @TableField
    private int userId;
    @TableField
    private int statusId;
    @TableField

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private Date time;//创建时间
    @TableField
    private float price;


}
