# spring
spring学习笔记
spring是基于ioc（控制反转）和aop（面向切面）的框架，搭建spring环境需要6个jarspring-aop.jar		开发AOP特性时需要的JAR
spring-beans.jar	处理Bean的jar			<bean>
spring-context.jar	处理spring上下文的jar		<context>
spring-core.jar		spring核心jar
spring-expression.jar	spring表达式 
commons-logging.jar	日志
然后编写配置文件，通常名为applicationContext.xml。  

控制反转：将 创建对象、属性值 的方式 进行了翻转，从new、setXxx()  翻转为了 从springIOC容器getBean()
依赖注入：将属性值 注入给了属性，将属性 注入给了bean，将bean注入给了ioc容器；无论要什么对象，都可以直接去springioc容器中获取，而不需要自己操作（new\setXxx()）
因此之后的ioc分为2步：1 先给springioc中存放对象并赋值（可以在配置文件中配置bean，也可以使用注解自动装配）   2 拿对象  

# IOC
## 1、配置bean的方式  

类似于：  

    <!--该文件中产生的对象，被spring放入了ioc容器中-->
    <bean id="student" class="com.entity.Student">
        <property name="id" value="10001"/>
        <property name="age" value="18"/>
        <property name="name" value="zhangsan"/>
    </bean>  
    
给对象类型赋值null：  

		<property name="name" >  
		    <null/>      
		</property>  
注意，没有<'value'>这个属性

赋空值 " "  

		<property name="name" >  
		    <value></value>  
		</property>
在ioc中定义bean的前提：该bean的类 必须提供了 无参构造    
如果一个类中引用了另一个类，可以使用自动装配的方式（只适用于 ref类型 ）  
可以在头文件中 一次性将该ioc容器的所有bean  统一设置成自动装配：
<beans xmlns="http://www.springframework.org/schema/beans"
        ...  default-autowire="byName">  
byName本质是byId  
byName:  自动寻找：其他bean的id值=该Course类的属性名  
byType:  其他bean的类型(class)  是否与 该Course类的ref属性类型一致  （注意，此种方式 必须满足：当前Ioc容器中 只能有一个Bean满足条件  ）  
constructor： 其他bean的类型(class)  是否与 该Course类的构造方法参数 的类型一致；此种方式的本质就是byType


## 2、注解方式  

<context:component-scan base-package="org.lanqiao.dao">
</context:component-scan>Spring在启动的时候，会根据base-package在 该包中扫描所有类，扫描器 会将 指定的包 中的  @Componet @Service  @Respository   @Controller修饰的类产生的对象 增加到IOC容器中

@Component细化：  

dao层注解：@Repository  
service层注解：@Service  
控制器层注解：@Controller  

# AOP  
## 1、继承接口  
需要的jar包  
	aopaliance.jar
	aspectjweaver.jar  
一个普通的类	->	有特定功能的类  
	a.继承类  b.实现接口  c.注解  d.配置  
前置通知，后置通知，异常通知和环绕通知，分别继承于MethodBeforeAdvice，AfterReturningAdvice，ThrowsAdvice，MethodInterceptor四个接口，然后在配置文件中用bean注册各类通知
再通过配置<aop:config>使其生效，类似于：  

    <!--aop（将通知所在类与通知进行关联）-->
    <aop:config proxy-target-class="true">
        <!--配置前置通知切入点（在哪里执行通知）-->
        <aop:pointcut id="pointcut" expression="execution(public void com.service.StudentServiceImpl.deleteStudent()) or execution(public void com.service.StudentServiceImpl.addStudent(com.entity.Student))"/>
        <!--链接切入点和切面的线-->
        <aop:advisor advice-ref="logBefore" pointcut-ref="pointcut"/>
    </aop:config>  
    
环绕通知： 在目标方法的前后、异常发生时、最终等各个地方都可以 进行的通知，最强大的一个通知；
	可以获取目标方法的 全部控制权（目标方法是否执行、执行之前、执行之后、参数、返回值等）

	在使用环绕通知时，目标方法的一切信息 都可以通过invocation参数获取到
	环绕通知 底层是通过拦截器实现的。  
  
## 2、注解  
在类上加上注解@Aspect  声明该类是一个通知，@Aspect不需要 加入扫描器，只需要开启即可：<aop:aspectj-autoproxy></aop:aspectj-autoproxy>，实现接口形式、注解形式 只捕获声明的特定类型的异常，而其他类型异常不捕获  

## 3、配置  

基于Schema配置，类似于实现接口的方式，类似于  

    <!--配置schema形式的通知-->
    <aop:config>
        <aop:pointcut id="logschema1" expression="execution(public * com.service.StudentServiceImpl.addStudent(com.entity.Student))"/>
        <aop:aspect ref="logschema">
            <aop:before method="before" pointcut-ref="logschema1"/>
            <aop:after-returning method="afterreturning" pointcut-ref="logschema1"/>
        </aop:aspect>
    </aop:config>  

# spring开发web  
当服务启动时（tomcat），通过监听器将SpringIOC容器初始化一次（该监听器 spring-web.jar已经提供）
因此用spring开发web项目 至少需要7个jar： spring-java的6个jar + spring-web.jar，注意：web项目的jar包 是存入到WEB-INF/lib中。web项目启动时 ，会自动加载web.xml，因此需要在web.xml中加载 监听器（ioc容器初始化）。  
Web项目启动时，启动实例化Ioc容器，在web.xml中配置：  
 <!-- 指定 Ioc容器（applicationContext.xml）的位置-->
  <context-param>
  		<!--  监听器的父类ContextLoader中有一个属性contextConfigLocation，该属性值 保存着 容器配置文件applicationContext.xml的位置 -->
  		<param-name>contextConfigLocation</param-name>
  		<param-value>classpath:applicationContext.xml</param-value>
  </context-param>  
  <listener>
  	<!-- 配置spring-web.jar提供的监听器，此监听器 可以在服务器启动时 初始化Ioc容器。
  		初始化Ioc容器（applicationContext.xml） ，
  			1.告诉监听器 此容器的位置：context-param
  			2.默认约定的位置	:WEB-INF/applicationContext.xml
  	 -->
  	<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
  </listener>  
  
  具体步骤，jsp页面先请求一个servlet，该servlet要纳入spring的管理，在类中通过ioc容器将servlet要调用的service层注入给servlet，该service必须在servlet中有set方法，然后在servlet的init方法中获取此service实例，然后就和jsp中的正常操作一样了，调service拿数据，setAttribute，forward跳转等。
