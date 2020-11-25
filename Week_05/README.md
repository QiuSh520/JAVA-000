学习笔记

### 9_1、（选做）使Java里的动态代理，实现一个简单的AOP

代码路径为【代码/spring01/src/main/java/io/kimmking/homework9_1】

JDK自带的Proxy实现AOP：io.kimmking.homework9_1.SchoolJdkProxyAop

CGlib实现的AOP代理：io.kimmking.homework9_1.SchoolCGLibProxyAop

### 9_2、（必做）写代码实现Spring Bean的装配，方式越多越好（XML、Annotation都可以）,提

交到Github

代码路径为【代码/spring01/src/main/java/io/kimmking/homework9_2】

Spring Bean的装配实现了四种方式：

完全xml方式：子包【xml】中，完全基于xml配置文件实现

混合方式：子包【mixed】中，对象的属性注入即使用xml也使用注解@Autowired

annotation方式：子包【annotation】中，通过@Component+@Autowired实现

configuration方式：子包【configuration】中，通过@Configuration+@Bean+@Autowired实现

### 10_3（必做）给前面课程提供的Student/Klass/School实现自动配置和Starter

starter代码路径为【代码/demo-spring-boot-starter】，使用starter代码路径为【代码/springboot01】。demo-spring-boot-starter的自动配置在包【io.kimmking.springboot01.autoconfigure】中，bean在包【io.kimmking.springboot01.domain】中。springboot01在pom中引入前者的依赖，在主类【io.kimmking.springboot01.DemoApplication】中是测试代码，在配置文件【application.properties】中可配置bean的属性

### 10_6（必做）研究一下JDBC接口和数据库连接池，掌握它们的设计和用法

1）使用JDBC原生接口，实现数据库的增删改查操作。
2）使用事务，PrepareStatement方式，批处理方式，改进上述操作。
3）配置Hikari连接池，改进上述操作。提交代码到Github。

代码在【代码/springboot01】中，具体包为【io.kimmking.springboot01.jdbc】，其中【io.kimmking.springboot01.jdbc.JdbcNativeExample】为第一步的 代码；【io.kimmking.springboot01.jdbc.JdbcNativeWithTransactionExample】为第二步代码；【io.kimmking.springboot01.jdbc.JdbcNativeWithDataSourceExample】为第三步代码