package com.xiaohuowa.lh138.base;

import android.view.KeyEvent;

/**
 * Fragment键盘按下事件
 */
public interface OnFragmentKeyDownListener {
    /**
     * 按下监听
     * @param keyCode
     * @param event
     * @return
     */
    public boolean onKeyDown(int keyCode, KeyEvent event);
}
