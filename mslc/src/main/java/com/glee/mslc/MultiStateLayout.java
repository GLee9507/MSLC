package com.glee.mslc;

import android.view.ViewManager;
import android.view.ViewParent;

public interface MultiStateLayout extends ViewParent, ViewManager {
    void showContent();
}
