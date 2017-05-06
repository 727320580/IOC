package com.xika.iocdemo.ico;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/6
 * Vwesion 1.0
 * Dsscription:  IOC 的方法名 注解接口
 */
// @Target(ElementType.FIELD) 代表注解Annotation的位置, FIELD属性上面, METHOD方法上面, TYPE类上, CONSTRUTOR构造函数上
@Target(ElementType.METHOD)
// @Retention(RetentionPolicy.RUNTIME) 代表注解Annotation什么时候生效 RUNTIME 运行的时候 CLASS 编译的时候 SOURCE 源码资源的时候
@Retention(RetentionPolicy.RUNTIME)
public @interface OnClick {

    // --> @OnClick({R.id.xxx,R.id.xxx}); (映射的是对应的R.id.textView的集合);
    int[] value();
}
