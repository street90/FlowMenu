package com.app.song.library;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.app.song.library.adapter.FlowMenuAdapter;
import com.app.song.library.utils.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by song on 2016/9/4.
 */
public class FlowMenu extends LinearLayout {

    private static final String TAG = "FlowMenu";

    private PopupWindow mPopupWindow;
    private Button btShow;
    private Context mContext;
    private ListView lvMenu1,lvMenu2,lvMenu3;
    private List<String> mDataList1,mDataList2,mDataList3;
    private FlowMenuAdapter mAdapter1,mAdapter2,mAdapter3;
    private int showNum = 6;//listView显示的数量
    private int itemHight = 40;//listView 单个item的高度 dp

    private LinearLayout.LayoutParams mLayoutParams;


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
        lvMenu1 = (ListView) view.findViewById(R.id.lv_menu1);
        lvMenu2 = (ListView) view.findViewById(R.id.lv_menu2);
        lvMenu3 = (ListView) view.findViewById(R.id.lv_menu3);
        mPopupWindow = new PopupWindow(view,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mAdapter1 = FlowMenuAdapter.newInstance();
        mAdapter2 = FlowMenuAdapter.newInstance();
        mAdapter3 = FlowMenuAdapter.newInstance();

        itemHight = utils.dip2px(mContext,itemHight);

        initLvLp();

//        mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
//        mLayoutParams.width = 0;
//        mLayoutParams.height = itemHight*showNum;
//        mLayoutParams.weight = 1;
//
//        lvMenu1.setLayoutParams(mLayoutParams);
//        lvMenu2.setLayoutParams(mLayoutParams);
//        lvMenu3.setLayoutParams(mLayoutParams);

        lvMenu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                mDataList2.clear();
                for (int j = 0; j < 20; j++) {
                    mDataList2.add(i+" item " + j);
                }

                mAdapter2.setData(mDataList2);

                mDataList3.clear();

                mAdapter3.setData(mDataList3);
            }
        });

        lvMenu2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                mDataList3.clear();

                for (int j = 0; j < 20; j++) {
                    mDataList3.add(i+" item" + j);
                }

                mAdapter3.setData(mDataList3);


            }
        });


        lvMenu3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(mContext,i+" selected ",Toast.LENGTH_SHORT).show();
            }
        });


        mDataList1 = new ArrayList<String>();
        mDataList2 = new ArrayList<String>();
        mDataList3 = new ArrayList<String>();
        for (int i = 0; i < 10; i++) {
            mDataList1.add(i+"item");
            mDataList2.add(i+"item");
            mDataList3.add(i+"item");
        }

        lvMenu1.setAdapter(mAdapter1);
        lvMenu2.setAdapter(mAdapter2);
        lvMenu3.setAdapter(mAdapter3);

        btShow = new Button(mContext);
        btShow.setText("显示MENU");
        mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        btShow.setLayoutParams(mLayoutParams);
        this.addView(btShow);
        btShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {



                if(mPopupWindow== null)
                    return;

                initLvLp();

                if( !mPopupWindow.isShowing())
                {
                    mAdapter1.setData(mDataList1);

                    mPopupWindow.showAsDropDown(view);

                }
                else
                {
                    mPopupWindow.dismiss();
                }


                Log.d(TAG,"show");
            }
        });



    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);


    }


    public void setLvData1()
    {

    }
    public void setLvData2()
    {

    }
    public void setLvData3()
    {

    }


    /**
     * 设置显示的数量
     */
    public void setShowItemNum(int showNum)
    {
        if(showNum <= 0)
            return ;
        this.showNum = showNum;

    }

    public void setItemHight(int itemHight)
    {
        if(itemHight <= 0)
            return;

        this.itemHight = itemHight;

    }


    private void initLvLp()
    {
        mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        mLayoutParams.width = 0;
        mLayoutParams.height = itemHight*showNum;
        mLayoutParams.weight = 1;

        lvMenu1.setLayoutParams(mLayoutParams);
        lvMenu2.setLayoutParams(mLayoutParams);
        lvMenu3.setLayoutParams(mLayoutParams);
    }


}
