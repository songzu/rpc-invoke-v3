package com.songzuedu;

import com.songzuedu.discovery.IServiceDiscovery;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public class RemoteInvocationHandler implements InvocationHandler {

    private IServiceDiscovery serviceDiscovery;

    private String version;

    public RemoteInvocationHandler(IServiceDiscovery serviceDiscovery, String version) {
        this.serviceDiscovery = serviceDiscovery;
        this.version = version;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //请求数据的包装
        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setVersion(version);
        rpcRequest.setParameters(args);
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setClassName(method.getDeclaringClass().getName());
        rpcRequest.setParamTypes(method.getParameterTypes());

        String serviceName = rpcRequest.getClassName();
        if (!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }
        String serviceAddress = serviceDiscovery.discovery(serviceName);
        //远程通信
        RpcNetTransport netTransport = new RpcNetTransport(serviceAddress);
        Object result = netTransport.send(rpcRequest);

        return result;
    }
}
