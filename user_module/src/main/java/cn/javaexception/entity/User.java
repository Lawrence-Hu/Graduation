package cn.javaexception.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Email;

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
    @Email
    private String email;

    /**
     * '创建时间'
     */
    private LocalDateTime createdTime;

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
    private LocalDateTime lastLoginTime;

    /**
     * '是否认证 0 否 1是'
     */
    private String cerification;

    /**
     * '生日'
     */
    private LocalDateTime birthday;

    /**
     * '角色'
     */
    private String role;

    /**
     * '个人简历'
     */
    private String profile;

    /**
     * '默认收货地址'
     */
    private String deliveryAddressId;
    /**
     * '验证码'
     *
     */
    private String identifyingCode;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
