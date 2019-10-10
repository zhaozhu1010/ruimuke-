//package com.ks.ks.aop;
//
//import com.alibaba.fastjson.JSON;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.Signature;
//import org.aspectj.lang.annotation.*;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestAttribute;
//import org.springframework.web.context.request.RequestAttributes;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.thymeleaf.expression.Maps;
//
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.util.Enumeration;
//import java.util.HashMap;
//import java.util.Map;
//
//
//@Component
//@Aspect     //Aop注解
//public class aopUntil {
//
//                //匹配com.ks.ks.Cntroller包以及其子包下面所有类的所有方法
//    @Pointcut("execution(* com.ks.ks.controller..*.*(..))")
//    public void executeService(){}
//
//    //前置通知：方法调用前被调用
//    @Before("executeService()")
//    public void doBeforeAdvice(JoinPoint joinPoint){
//        System.out.println("前置通知--》");
//        Object[] obj=joinPoint.getArgs();   //获取目标方法的参数信息
//        joinPoint.getThis();    //AOP代理类信息
//        joinPoint.getTarget();  //代理的目标对象
//        Signature signature=joinPoint.getSignature();   //用的最多，通知的签名
//        System.out.println("代理的方法是："+signature.getName());
//        System.out.println("AOP代理的名字是："+signature.getDeclaringTypeName());
//        signature.getDeclaringType();   //AOP代理类的类信息
//
//        //   通过RequestContextHolder获取请求信息，如session 信息 ;
//        RequestAttributes requestAttributes= RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request=(HttpServletRequest)requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
//        HttpSession session=(HttpSession)requestAttributes.resolveReference(RequestAttributes.REFERENCE_SESSION);
//        System.out.println("请求-》"+request+",HttpSession->"+session);
//        Enumeration<String> enumerations=request.getParameterNames();
//        Map<String,String> parameterMaps=new HashMap<>();
//
//        while (enumerations.hasMoreElements()){
//            String parameter=enumerations.nextElement();
//            parameterMaps.put(parameter,request.getParameter(parameter));
//        }
//                    //阿里巴巴的fastJson
//        String str= JSON.toJSONString(parameterMaps);
//        if(obj.length>0){
//            System.out.println("请求参数信息为——》"+str);
//        }
//
//    }
//
//
//    //后置
//    @After("executeService()")
//    public void doAfterAdvice(JoinPoint joinPoint){
//        System.out.println("后置通知~~~~~~~~~");
//    }
//
//    //后置返回：参数为Object类型执行
//    @AfterReturning(value = "executeService()",returning = "resultObj")
//    public void afterAdviceReturn(Object resultObj){
//        System.out.println("后置返回通知总Obj开始~~~");
//        System.out.println("==>"+JSON.toJSONString(resultObj));
//    }
//
//    //后置返回：参数为String类型执行
//    @AfterReturning(value = "executeService()",returning = "resultRes")
//    public void afterAdviceReturn(String resultRes){
//        System.out.println("后置返回通知Res开始~~~");
//        System.out.println("==>"+JSON.toJSONString(resultRes));
//    }
//
//
//    //环绕监听：
//    @Around("execution(* com.ks.ks.controller..*.*(..))")
//    public Object doAroundService(ProceedingJoinPoint proceedingJoinPoint){
//        System.out.println("");
//        System.out.println("环绕通知=》方法名："+proceedingJoinPoint.getSignature().getName());
//        System.out.println("");
//        try{
//            Object object=proceedingJoinPoint.proceed();
//            return object;
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
//        return null;
//    }
//
//
//
//
//}
