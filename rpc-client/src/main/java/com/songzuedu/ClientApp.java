package com.songzuedu;

import com.songzuedu.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public class ClientApp {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        RpcProxyClient rpcProxyClient = context.getBean(RpcProxyClient.class);

        IHelloService helloService = rpcProxyClient.clientProxy(IHelloService.class, "v2.0");
        for (int i = 0; i < 100; i++) {
            Thread.sleep(1000);
            String result = helloService.sayHello(100);
            System.out.println(result);
        }
    }

}
