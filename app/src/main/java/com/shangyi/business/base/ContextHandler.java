package com.shangyi.business.base;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Stack;

/**
 * data: 2020/3/18 9:26
 * auther: Dz
 * function: 上下文管理类
 */
public class ContextHandler {

    //Activity堆栈
    private static Stack<Activity> activityStack = new Stack<>();

    //存储当前Fragment
    //private static BaseFragment fragment;

    //APPLICATION
    private static Application application;

    //传递参数临时存储
    private static Map param = new HashMap();

    /**
     * 保存Application
     */
    public static void saveApplication(Application application) {
        ContextHandler.application = application;
    }

    /**
     * 添加Activity到堆栈
     */
    public static void addActivity(Activity activity) {
        activityStack.push(activity);
        //清空fragment
        // fragment = null;
    }

    /**
     * 设置fragment
     */
    /*public static void saveFragment(BaseFragment fragment) {
        ContextHandler.fragment = fragment;
    }*/

    /**
     * 获得当前运行的Activity
     */
    public static Activity currentActivity() {
        return activityStack.peek();
    }

    /**
     * 获得Application
     */
    public static Application getApplication() {
        return application;
    }

    /**
     * 获得当前对象
     */
    /*public static Object current() {
        return fragment != null ? fragment : currentActivity();
    }
*/
    /**
     * 移除最后的Activity
     */
    public static void finish() {
        currentActivity().finish();
        activityStack.pop();
    }

    /**
     * 移除特殊组的Activity
     *
     * @param group 同一个类之间打多个标签时，中间用逗号隔开
     */
//    public static void finishGroup(String group) {
//        Iterator<Activity> iterator = activityStack.listIterator();
//        while (iterator.hasNext()) {
//            Activity activity = iterator.next();
//            if (ReflectUtil.isHaveAnnotation(activity.getClass(), InjectGroup.class)) {
//                if (ReflectUtil.getAnnotation(activity.getClass(), InjectGroup.class).value().contains(group)) {
//                    iterator.remove();
//                    activity.finish();
//                }
//            }
//        }
//    }

    /**
     * 移除特殊组的Activity
     */
    public static void goForwardFinishAllGroup(Class activityClass) {
        //LogUtil.e("========================>>activityStack数量====" + activityStack.size());
        if (activityStack.size() > 0) {
            toActivity(activityClass);
            Iterator<Activity> iterator = activityStack.listIterator();
            while (iterator.hasNext()) {
                Activity activity = iterator.next();
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 移除指定activity
     */
    public static void finishAppointActivity(Class activityClass) {
        Iterator<Activity> iterator = activityStack.listIterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            if (activity.getClass() == activityClass) {
                iterator.remove();
                activity.finish();
            }
        }
    }

    /**
     * 移除activity
     */
    public static void finishAllActivity() {
        Iterator<Activity> iterator = activityStack.listIterator();
        while (iterator.hasNext()) {
            Activity activity = iterator.next();
            iterator.remove();
            activity.finish();
        }
    }

    /**
     * 获取指定的activity
     */
    public static Activity getAppointActivity(Class activityClass) {
        for (Activity activity : activityStack) {
            if (activity.getClass() == activityClass) {
                return activity;
            }
        }
        return null;
    }


    /**
     * 移除特殊组的Activity,并且跳转到新的Activity
     */
//    public static void goForwardFinishGroup(Class activityClass, String group) {
//        Intent intent = new Intent(currentActivity(), activityClass);
//        currentActivity().startActivity(intent);
//        Iterator<Activity> iterator = activityStack.listIterator();
//        while (iterator.hasNext()) {
//            Activity activity = iterator.next();
//            if (ReflectUtil.isHaveAnnotation(activity.getClass(), InjectGroup.class)) {
//                if (ReflectUtil.getAnnotation(activity.getClass(), InjectGroup.class).value().equals(group)) {
//                    iterator.remove();
//                    activity.finish();
//                }
//            }
//        }
//    }

    /**
     * 跳转界面，可选择参数为关闭标志（boolean），传递状态（State）
     */
    public static void toActivity(Class activityClass) {
        toActivity(activityClass,null);
    }
    public static void toActivity(Class activityClass, boolean flag) {
        toActivity(activityClass,0,null,flag);
    }
    public static void toActivity(Class activityClass, Bundle bundle) {
        toActivity(activityClass,0,bundle);
    }
    public static void toActivity(Class activityClass, int requestCode) {
        toActivity(activityClass,requestCode,null);
    }
    public static void toActivity(Class activityClass, int requestCode, Bundle bundle) {
        toActivity(activityClass,requestCode,bundle,false);
    }

    public static void toActivity(Class activityClass, int requestCode, Bundle bundle, boolean flag) {
        // LogUtil.e("currentActivity() = " + currentActivity());
        Intent intent = new Intent(currentActivity(), activityClass);
        if (bundle != null){
            intent.putExtras(bundle);
        }
        //先移除顶部相同的activity
        if (currentActivity().getClass().equals(activityClass)) {
            finish();
        }
        //打开activity
        //LogUtil.e("requestCode = " + requestCode);
        currentActivity().startActivityForResult(intent,requestCode);
        if (flag) {
            finish();
        }
    }

    /**
     * 关闭当前界面，响应原界面并反向传递参数
     */
//    public static void response(State state) {
//        //保存传递参数
//        param = state.post;
//        //关闭当前界面
//        finish();
//    }


    /**
     * 获取临时参数对象
     */
//    public static void fillStateParam(State state) {
//        if (state != null) {
//            state.fillState(param);
//            param.clear();
//        }
//    }
}
