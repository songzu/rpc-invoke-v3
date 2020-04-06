package com.songzuedu;

import com.songzuedu.discovery.IServiceDiscovery;
import com.songzuedu.discovery.ServiceDiscoveryWithZk;

import java.lang.reflect.Proxy;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public class RpcProxyClient {

    private IServiceDiscovery serviceDiscovery = new ServiceDiscoveryWithZk();

    public <T> T clientProxy(final Class<T> interfaceCls, String version) {
        return (T) Proxy.newProxyInstance(interfaceCls.getClassLoader(), new Class<?>[]{interfaceCls},
                new RemoteInvocationHandler(serviceDiscovery, version));
    }

}
