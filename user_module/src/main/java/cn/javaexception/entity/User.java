package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;
    /**
     * '主键id'
     */
    @TableId(value = "id")
    //@NotNull(message = "用户id不能为空")
    @NotNull(message = "用户id不能为null")
    private String id;

    /**
     * '姓名'
     */
    private String name;

    /**
     * '账号'
     */
    private String account;

    /**
     * '手机号'
     */
    @NotNull(message = "用户email不能为null")
    private String phone;

    /**
     * '保密'
     */
    private String gender;

    /**
     * '地址'
     */
    private String address;

    /**
     * '邮箱'
     */
    //@Email
    @NotNull(message = "用户email不能为null")
    private String email;

    /**
     * '创建时间'
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createdTime;

    /**
     * '个人签名'
     */
    private String sign;

    private String status;

    /**
     * '头像地址'
     */
    private String imgUrl;

    /**
     * '上次登录时间'
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date lastLoginTime;

    /**
     * '是否认证 0 否 1是'
     */
    private String certification;

    /**
     * '生日'
     */
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday;

    /**
     * '角色'
     */
    private String roleId;

    /**
     * '个人简历'
     */
    private String profile;

    /**
     * '默认收货地址'
     */
    private String deliveryAddressId;
    /**
     * 'alipay'
     *
     */
    private String alipayAccount;
    /**
     * '验证码'
     *
     */
    private String identifyingCode;

    @TableField(exist = false)
    private UserStatus userStatus;

    @TableField(exist = false)
    private AuthUser authUser;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
