package cn.javaexception.controller;


import cn.javaexception.model.DeliverAddress;
import cn.javaexception.service.DeliverAddressService;
import cn.javaexception.util.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author huchao
 * @since 2019-03-02
 */
@RestController
@RequestMapping("/deliverAddress")
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
    public JsonData addAddress(@RequestBody @Valid DeliverAddress address) {
        return deliverAddressService.addAddress(address) ? JsonData.buildSuccess() : JsonData.buildError("添加失败");
    }

    /**
     * @param addresses
     * @return JsonData
     * @author huchao
     * @description 删除收货地址
     */
    @PostMapping("/delete")
    public JsonData deleteAddress(@RequestBody @Valid DeliverAddress[] addresses) {
        return deliverAddressService.deleteAddress(addresses) ? JsonData.buildSuccess() : JsonData.buildError("删除失败，请刷新后重试！");
    }
}

