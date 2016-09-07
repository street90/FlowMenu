package com.app.song.library.utils;

import android.content.Context;

/**
 * Created by song on 2016/9/7.
 */
public class utils {

    private utils()
    {}

    /**
     *  dip转换成px
     * @param context
     * @param dipValue
     * @return
     */
    public static int dip2px(Context context,float dipValue)
    {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int)(pxValue / scale + 0.5f);
    }

}
