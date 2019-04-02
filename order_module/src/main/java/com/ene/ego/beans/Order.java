package com.ene.ego.beans;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName(value = "t_order")
public class Order {
    private String id;
    private Integer userId;
    private Integer statusId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(
            pattern = "yyyy-MM-dd HH:mm:ss"
    )
    private LocalDateTime time;//创建时间
    private float price;

}
