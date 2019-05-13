package com.nacos.alibaba.server.CustomAnnotations;

import java.lang.annotation.*;


/**
 * 注解的作用目标：
 *
 * 　　@Target(ElementType.TYPE)              // 接口、类、枚举、注解
 *
 * 　　@Target(ElementType.FIELD)             // 字段、枚举的常量
 *
 * 　　@Target(ElementType.METHOD)            // 方法
 *
 * 　　@Target(ElementType.PARAMETER)         // 方法参数
 *
 * 　　@Target(ElementType.CONSTRUCTOR)       // 构造函数
 *
 * 　　@Target(ElementType.LOCAL_VARIABLE)    // 局部变量
 *
 * 　　@Target(ElementType.ANNOTATION_TYPE)   // 注解
 *
 * 　　@Target(ElementType.PACKAGE)           // 包
 */

@Documented // 注解包含在javadoc中
@Inherited // 注解可以被继承
@Target(ElementType.FIELD) // 注解的作用目标
@Retention(RetentionPolicy.RUNTIME)
public @interface Init {

    public String value() default "123";
}
