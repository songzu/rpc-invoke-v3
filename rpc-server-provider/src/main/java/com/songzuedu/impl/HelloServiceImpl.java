package com.songzuedu.impl;

import com.songzuedu.IHelloService;
import com.songzuedu.User;
import com.songzuedu.annotation.RpcService;

/**
 * <p></p>
 *
 * @author gengen.wang
 * @version $$ Id: HelloServiceImpl.java, V 0.1 2020/3/27 上午11:04 wanggengen Exp $$
 **/
@RpcService(value = IHelloService.class, version = "v1.0")
public class HelloServiceImpl implements IHelloService {
    @Override
    public String sayHello(double money) {
        System.out.println("【V1.0】request in sayHello:" + money);
        return "【V1.0】Say Hello:" + money;
    }

    @Override
    public String saveUser(User user) {
        System.out.println("【V1.0】request in saveUser:" + user);
        return "【V1.0】SUCCESS";
    }
}
