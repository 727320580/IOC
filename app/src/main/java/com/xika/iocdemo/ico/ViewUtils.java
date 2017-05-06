package com.xika.iocdemo.ico;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/6
 * Vwesion 1.0
 * Dsscription:    通过反射和动态代理实现IOC 注解类体
 */

public class ViewUtils {
    // TODO: 2017/5/6 1 初始化注解对象  对象(Acitivity类,View布局)

    /**
     * Activity 直接的初始化
     *
     * @param activity
     */
    public static void inject(Activity activity) {
        inject(new ViewFinder(activity), activity);
    }

    /**
     * 一些视图的注解生成器(Fragment , View)
     *
     * @param view
     */
    public static void inject(View view) {
        inject(new ViewFinder(view), view);
    }

    private static void inject(ViewFinder finder, Object object) {
        injectFiled(finder, object);
        injectEvent(finder, object);
    }

    // TODO: 2017/5/6  2 属性注解   中心思想(filed.set(属性类.反射对应的view的id) )
    private static void injectFiled(ViewFinder finder, Object object) {
        // 获取需要反射的属性的类
        Class<?> clazz = object.getClass();
        // 获取该类中所有属性 存放在属性的集合中
        Field[] fileds = clazz.getDeclaredFields();
        // 循环遍历所有属性进行反射赋值
        for (Field filed : fileds) {
            // 通过直接类 获取注解类中的内容
            ViewById viewById = filed.getAnnotation(ViewById.class);
            // 筛选出没有注解的属性
            if (viewById != null) {
                int viewId = viewById.value();
                // 通过辅类 获取直接中的 ResId 对应的View;
                View view = finder.findViewById(viewId);
                // 检测注解的属性的id 时候存在
                if (view != null) {
                    try {
                        // field 能够注入所有的控件前面的修饰符 private public
                        filed.setAccessible(true);
                        filed.set(object, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    // TODO: 2017/5/6  3 方法注解
    private static void injectEvent(ViewFinder finder, Object object) {
        // 获取类对象
        Class<?> clazz = object.getClass();
        // 获取全部的方法
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            // 获取方法注解中的注解对象
            OnClick onClick = method.getAnnotation(OnClick.class);
            // 筛除其他没有注解的方法
            if (onClick != null) {
                int[] viewId = onClick.value();
                // 循环设置没有点击时间的view的内容
                for (int id : viewId) {
                    View view = finder.findViewById(id);
                    // 筛除注解中不存在的viewId的view
                    if (view != null) {
                        view.setOnClickListener(new DeclaredOnClickListener(method, object));

                    }
                }
            }
        }
    }

    // 创建动态代理点击类
    private static class DeclaredOnClickListener implements View.OnClickListener {
        private Method mMethod;
        private Object mObject;

        public DeclaredOnClickListener(Method method, Object object) {
            mMethod = method;
            mObject = object;
        }

        @Override
        public void onClick(View v) {
            try {
                // 可以设置任何修饰的方法
                mMethod.setAccessible(true);
                // 通过反射来设置方法
                mMethod.invoke(mObject, v);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                try {
                    // 防止传入的view 为空 程序崩溃;
                    mMethod.invoke(mMethod,null);
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    e1.printStackTrace();
                }
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
