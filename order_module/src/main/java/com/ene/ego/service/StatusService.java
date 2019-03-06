package com.ene.ego.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ene.ego.beens.Order;
import com.ene.ego.beens.Status;

public interface StatusService extends IService<Status> {

    Order UpdateOrSta(int id, int status);//修改订单状态
}
