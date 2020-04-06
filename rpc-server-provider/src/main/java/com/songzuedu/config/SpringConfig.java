package com.songzuedu.config;

import com.songzuedu.Constant;
import com.songzuedu.RpcServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
@Configuration
@ComponentScan(basePackages = "com.songzuedu.impl")
public class SpringConfig {

    @Bean("rpcServer")
    public RpcServer gpRpcServer() {
        return new RpcServer(Constant.PORT);
    }

}
