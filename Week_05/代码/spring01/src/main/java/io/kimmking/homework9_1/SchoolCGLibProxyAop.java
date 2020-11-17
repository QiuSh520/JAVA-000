package io.kimmking.homework9_1;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class SchoolCGLibProxyAop implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("SchoolCGLibProxyAop begin");

        Object invoke = methodProxy.invokeSuper(o, objects);

        System.out.println("SchoolCGLibProxyAop end");

        return invoke;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(School.class);
        enhancer.setCallback(new SchoolCGLibProxyAop());
        School schoolProxy = (School) enhancer.create();
        schoolProxy.ding();
    }
}
