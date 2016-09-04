package com.app.song.library;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

/**
 * Created by song on 2016/9/4.
 */
public class FlowMenu extends LinearLayout {

    private static final String TAG = "FlowMenu";

    private PopupWindow mPopupWindow;
    private Button btShow;
    private Context mContext;


    public FlowMenu(Context context) {
        super(context);
        initMenu(context);
    }

    public FlowMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMenu(context);
    }

    public FlowMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMenu(context);
    }

    private void initMenu(Context context) {

        mContext = context;

        View view = LayoutInflater.from(context).inflate(R.layout.pop_menu,null);
        mPopupWindow = new PopupWindow(view,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        Button btShow = new Button(mContext);
        btShow.setText("显示MENU");
        LayoutParams mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        btShow.setLayoutParams(mLayoutParams);
        this.addView(btShow);
        btShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.showAsDropDown(view);
                Log.d(TAG,"show");
            }
        });
    }



}
