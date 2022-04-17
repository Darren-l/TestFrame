package cn.gd.snm.frametest.aboutdesign.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogHandler implements InvocationHandler {
    private Object targetObject;
    public Object newProxyInstance(Object targetObject){
        this.targetObject=targetObject;
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(),
                targetObject.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable {
        System.out.println("start-->>");
        System.out.println("method=" + method.getName());
        for(int i=0;i<args.length;i++){
            System.out.println(args[i]);
        }
        Object ret=null;
        try{
            System.out.println("satrt-->>");
            ret=method.invoke(targetObject, args);
            System.out.println("success-->>");
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error-->>");
            throw e;
        }
        return ret;
    }
}
