package com.xika.iocdemo.ico;

import android.app.Activity;
import android.view.View;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/6
 * Vwesion 1.0
 * Dsscription:    IOC 注解类的辅助类(获取需要反射的类的对象 和找到类中的控件View)
 */

public class ViewFinder {
    private Activity mActivity;

    private View mView;

    public ViewFinder(Activity activity) {
        this.mActivity = activity;
    }

    public ViewFinder(View view) {
        this.mView = view;
    }

    public View findViewById(int viewId) {
        return mActivity != null ? mActivity.findViewById(viewId) : mView.findViewById(viewId);
    }


}
