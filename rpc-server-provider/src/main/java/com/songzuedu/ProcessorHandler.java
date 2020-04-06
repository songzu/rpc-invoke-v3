package com.songzuedu;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * <p></p>
 *
 * @author gengen.wang
 **/
public class ProcessorHandler extends SimpleChannelInboundHandler<RpcRequest> {

    private Map<String, Object> handlerMap;

    public ProcessorHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RpcRequest rpcRequest) throws Exception {
        Object result = invoke(rpcRequest);
        channelHandlerContext.writeAndFlush(result).addListener(ChannelFutureListener.CLOSE);
    }

    private Object invoke(RpcRequest request) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //反射调用
        String serviceName = request.getClassName();
        String version = request.getVersion();
        //增加版本号的判断
        if (!StringUtils.isEmpty(version)) {
            serviceName += "-" + version;
        }

        Object service = handlerMap.get(serviceName);
        if (service == null) {
            throw new RuntimeException("service not found:" + serviceName);
        }
        //拿到客户端请求的参数
        Object[] args = request.getParameters();
        //根据请求的类进行加载
        Class clazz = Class.forName(request.getClassName());
        //找到这个类中的方法
        Method method = clazz.getMethod(request.getMethodName(), request.getParamTypes());
        //反射调用
        Object result = method.invoke(service, args);
        return result;
    }

}
