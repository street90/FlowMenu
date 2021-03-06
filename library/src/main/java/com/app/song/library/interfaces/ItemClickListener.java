package com.app.song.library.interfaces;

import android.view.View;

/**
 * Created by song on 2016/9/9.
 */
public interface ItemClickListener {

    /**
     * 第一个listView点击监听
     * @param view
     * @param o
     * @param num
     */
    void firstListViewClick(View view,Object o,int num);


    /**
     * 第二个listView点击监听
     * @param view
     * @param o
     * @param num
     */
    void secondListViewClick(View view,Object o,int num);



}
