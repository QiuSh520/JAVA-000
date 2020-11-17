package io.kimmking.homework9_1;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理只能基于接口进行代码，不能基于类
 */
public class SchoolJdkProxyAop implements InvocationHandler {
    private ISchool school;

    public SchoolJdkProxyAop(ISchool school) {
        this.school = school;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("SchoolJdkProxyAop begin");

        Object invoke = method.invoke(school, args);

        System.out.println("SchoolJdkProxyAop end");

        return invoke;
    }

    public static void main(String[] args) {
        ISchool school=new School();

        InvocationHandler schoolProxy=new SchoolJdkProxyAop(school);

        ISchool proxyInstance= (ISchool) Proxy.newProxyInstance(schoolProxy.getClass().getClassLoader(),school.getClass().getInterfaces(),schoolProxy);

        proxyInstance.ding();
    }
}
