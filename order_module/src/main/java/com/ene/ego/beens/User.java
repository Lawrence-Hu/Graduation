package com.ene.ego.beens;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@TableName("user")
public class User {

    @TableId
    private int id;
    @TableField
    private String name;
    private int phone;
    private String address;

}
