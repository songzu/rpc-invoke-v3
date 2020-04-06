package com.songzuedu.impl;

import com.songzuedu.IPaymentService;
import com.songzuedu.annotation.RpcService;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
@RpcService(IPaymentService.class)
public class PaymentServiceImpl implements IPaymentService {

    @Override
    public Boolean doPay() {
        System.out.println("执行doPay方法");
        return true;
    }

}
