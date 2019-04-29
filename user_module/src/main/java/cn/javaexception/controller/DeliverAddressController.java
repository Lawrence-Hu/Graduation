package cn.javaexception.controller;


import cn.javaexception.entity.DeliverAddress;
import cn.javaexception.service.DeliverAddressService;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import utils.JsonData;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/api/deliverAddress")
@RequiresRoles({"user"})
public class DeliverAddressController {
    @Autowired
    DeliverAddressService deliverAddressService;

    /**
     * @param address
     * @return JsonData
     * @author huchao
     * @description 添加新地址
     */
    @PostMapping("/add")
    public JsonData addAddress(@RequestBody @Valid DeliverAddress address, Errors errors) {
        if (errors.hasErrors()) {
           return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }
        return deliverAddressService.addAddress(address);
    }
    /**
     * @author huchao
     * @description 更新用户收货地址
     * @param  address
     * @return JsonData
     */

    @PostMapping("/update")
    public JsonData updateAddress(@RequestBody DeliverAddress address, Errors errors) {
        if (errors.hasErrors()) {
            return JsonData.buildError(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage());
        }

        return deliverAddressService.updateAddress(address);
    }

    /**
     * @param addressIds
     * @return JsonData
     * @author huchao
     * @description 删除收货地址
     */
    @PostMapping("/delete")
    //考虑一下json格式
    public JsonData deleteAddress(@RequestBody String[] addressIds) {
        if (addressIds.length==0){
            return JsonData.buildError("地址id不能为空！！");
        }
        System.out.println(Arrays.asList(addressIds));
        return deliverAddressService.deleteAddress(addressIds);
    }
}

