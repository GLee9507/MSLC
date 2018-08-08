package com.glee.mslc;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.ArraySet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static android.view.View.NO_ID;


/**
 * by liji
 * 多状态布局控制器
 * 不可用于生产环境，仍未完善
 */
public class MultiStateLayoutController {
    private ViewGroup viewGroup;
    private SparseArray<View> stateViewSet;
    private LayoutInflater inflater;
    private SparseArray<View> contentViewSet;
    private List<View> viewList;

    public static MultiStateLayoutController setupToViewGroup(@NonNull ViewGroup viewGroup) {

        return new MultiStateLayoutController(viewGroup);
    }

    public static MultiStateLayout set(@NonNull final ViewGroup viewGroup) {

        return (MultiStateLayout) Proxy.newProxyInstance(MultiStateLayout.class.getClassLoader(), new Class[]{MultiStateLayout.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
                Log.d("glee9507", method.getName());
                return null;
            }
        });

    }


    private MultiStateLayoutController(final ViewGroup viewGroup) {
        this.viewGroup = viewGroup;
        inflater = LayoutInflater.from(viewGroup.getContext());
        stateViewSet = new SparseArray<>();
        contentViewSet = new SparseArray<>();

    }

    public void showContent() {
        removeAllStateView();

    }


    @Nullable
    public View findViewById(@LayoutRes int layoutId, @IdRes int viewId) {
        View view = stateViewSet.get(layoutId);
        return view == null ? null : view.findViewById(viewId);
    }

//    /**
//     * 恢复状态
//     */
//    private void recovery() {
//        int childCount = viewGroup.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = viewGroup.getChildAt(i);
//            int state = contentViewStatus.get(getKey(child), -100);
//            if (state != -100) {
//                child.setVisibility(state);
//            }
//        }
//    }

    private int getKey(View view) {
        int key = view.getId();
        if (key == NO_ID) {
            key = view.hashCode();
        }
        return key;
    }

    /**
     * 移除所有状态View
     */
    private void removeAllStateView() {
        int size = stateViewSet.size();
        for (int i = 0; i < size; i++) {
            viewGroup.removeView(stateViewSet.valueAt(i));
        }
    }

    public void show(@LayoutRes int layoutRes) {
        View view = getView(layoutRes);
        dismissAll();
        removeAllStateView();
        viewGroup.addView(view, viewGroup.getChildCount());
    }

//    /**
//     * 隐藏所有View，并保存Visibility值
//     */
//    private void dismissAll() {
//        int childCount = viewGroup.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            View child = viewGroup.getChildAt(i);
//            contentViewStatus.put(getKey(child), child.getVisibility());
//            if (viewGroup instanceof LinearLayout) {
//                child.setVisibility(View.GONE);
//            } else {
//                child.setVisibility(View.INVISIBLE);
//            }
//        }
//    }


    private void dismissAll() {
        int count = viewGroup.getChildCount();
        for (int i = 0; i < count; i++) {
            View child = viewGroup.getChildAt(i);
            contentViewSet.put(getKey(child), child);
        }
        viewGroup.removeAllViews();
    }

    /**
     * 通过资源id获取View
     *
     * @param layoutRes 资源id
     * @return View
     */
    private View getView(@LayoutRes int layoutRes) {
        View view = stateViewSet.get(layoutRes);
        if (view == null) {
            view = inflater.inflate(layoutRes, viewGroup, false);
            setViewLayoutParams(view);
            stateViewSet.put(layoutRes, view);
        }
        return view;
    }

    private void setViewLayoutParams(View view) {
        view.setLayoutParams(new ViewGroup.MarginLayoutParams(-1, -1));
    }
}

