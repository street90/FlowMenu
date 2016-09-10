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

import com.app.song.library.adapter.FlowMenuAdapter;
import com.app.song.library.interfaces.ItemClickListener;
import com.app.song.library.utils.utils;

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

    private LinearLayout.LayoutParams mLayoutParams;//布局的属性

    private ItemClickListener mItemClickListener;


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

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
              lvMenu1.setSelection(0);
                setLvData2(null);
                setLvData3(null);
            }
        });

        mAdapter1 = FlowMenuAdapter.newInstance();
        mAdapter2 = FlowMenuAdapter.newInstance();
        mAdapter3 = FlowMenuAdapter.newInstance();

        itemHight = utils.dip2px(mContext,itemHight);

        setLvLp();

        lvMenu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                setLvData3(null);

                if(mItemClickListener != null)
                    mItemClickListener.firstListViewClick(view,mAdapter2,i);

                lvMenu2.setSelection(0);
            }
        });

        lvMenu2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {



                if(mItemClickListener != null)
                    mItemClickListener.secondListViewClick(view,mAdapter3,i);

                lvMenu3.setSelection(0);
            }
        });


        lvMenu3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(mItemClickListener != null)
                    mItemClickListener.thirdListViewClick(view,null,i);
            }
        });


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

                setLvLp();

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


    /**
     * 设置 第一个listView数据
     * @param mDataList1
     */
    public void setLvData1(List<String> mDataList1)
    {
        this.mDataList1 = mDataList1;

        if(mAdapter1 != null)
            mAdapter1.setData(mDataList1);
    }

    /**
     * 设置 第二个listView数据
     * @param mDataList2
     */
    public void setLvData2(List<String> mDataList2)
    {
        this.mDataList2 = mDataList2;

        if(mAdapter2 != null)
            mAdapter2.setData(mDataList2);
    }


    /**
     * 设置 第三个listView数据
     * @param mDataList3
     */
    public void setLvData3(List<String> mDataList3)
    {

        this.mDataList3 = mDataList3;
        if(mAdapter3 != null)
            mAdapter3.setData(mDataList3);
    }


    public void setOnItemClickListener(ItemClickListener mItemClickListener)
    {
        this.mItemClickListener = mItemClickListener;
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


    /**
     * 设置ListItem高度
     * @param itemHight
     */
    private void setItemHight(int itemHight)
    {
        if(itemHight <= 0)
            return;

        this.itemHight = itemHight;

    }


    /**
     * 设置布局
     */
    private void setLvLp()
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
