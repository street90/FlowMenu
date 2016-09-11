package com.app.song.library;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    private ListView lvMenu1,lvMenu2;

    private List<String> mDataList1,mDataList2;//

    private FlowMenuAdapter mAdapter1,mAdapter2;

    private int showNum = 6;//listView显示的数量

    private int itemHight = 40;//listView 单个item的高度 dp

    private int firstMenuWith = -1;//第一个listView宽度

    TypedArray ta;//属性设置


    private ViewGroup.LayoutParams mLayoutParams;//布局的属性

    private ItemClickListener mItemClickListener;


    public FlowMenu(Context context) {
        super(context);
    }

    public FlowMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMenu(context, attrs);
    }

    public FlowMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMenu(context, attrs);
    }

    private void initMenu(Context context,AttributeSet attrs) {

        mContext = context;

        ta = mContext.obtainStyledAttributes(attrs,R.styleable.FlowMenu);

        showNum = ta.getInt(R.styleable.FlowMenu_show_num,6);

        firstMenuWith = (int) ta.getDimension(R.styleable.FlowMenu_first_menu_width,-1);

        ta.recycle();

        View view = LayoutInflater.from(context).inflate(R.layout.pop_menu,null);

        lvMenu1 = (ListView) view.findViewById(R.id.lv_menu1);
        lvMenu2 = (ListView) view.findViewById(R.id.lv_menu2);
        mPopupWindow = new PopupWindow(view,LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT,true);
        mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lvMenu1.setSelection(0);
                setLvData2(null);

            }
        });

        mAdapter1 = FlowMenuAdapter.newInstance();
        mAdapter2 = FlowMenuAdapter.newInstance();

        itemHight = utils.dip2px(mContext,itemHight);


        lvMenu1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                if(mItemClickListener != null)
                    mItemClickListener.firstListViewClick(view,mAdapter2,i);

                lvMenu2.setSelection(0);

            }
        });

        lvMenu2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(mItemClickListener != null)
                    mItemClickListener.secondListViewClick(view,null,i);

                setPopupWindowShowOrHide();
            }
        });





        lvMenu1.setAdapter(mAdapter1);
        lvMenu2.setAdapter(mAdapter2);

        btShow = new Button(mContext);


        ColorStateList mColorStateList = mContext.getResources().getColorStateList(R.color.item_text_color);
        btShow.setBackgroundResource(R.drawable.item_bg);
        btShow.setTextColor(mColorStateList);

        btShow.setText("显示MENU");
        mLayoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        btShow.setLayoutParams(mLayoutParams);
        this.addView(btShow);
        btShow.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {


                setMenuLayoutParams();

                setPopupWindowShowOrHide();

            }
        });


    }


    /**
     * 当Menu只显示一个的时候调用
     *
     * 设置Menu的隐藏或者显示
     */
    public void setPopupWindowShowOrHide() {
        if(mPopupWindow== null)
            return;

        if( !mPopupWindow.isShowing())
        {
            mAdapter1.setData(mDataList1);

            mPopupWindow.showAsDropDown(btShow);

        }
        else
        {
            mPopupWindow.dismiss();
        }
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
     * 设置点击监听事件
     * @param mItemClickListener
     */
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


    private void setMenuLayoutParams()
    {
        setFirstMenuLayoutParams();
        setSecondMenuLayoutParams();
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
     * 是否只显示一个ListView
     * @param isShow  true 显示一个  false 显示两个
     */
    public void showOneMenu(boolean isShow)
    {
        if(isShow)
            lvMenu2.setVisibility(View.GONE);
    }

    /**
     * 获得Button设置属性
     * @return
     */
    public Button getButton()
    {
        return btShow;
    }



    public void setFirstMenuWidth(int width)
    {

        if(width < 20)
            return;

        firstMenuWith = width;


    }


    /**
     * 设置第一个ListView的属性
     */

    private LinearLayout.LayoutParams mFirstParams;

    private void setFirstMenuLayoutParams()
    {

        if(mFirstParams == null)
            mFirstParams = (LinearLayout.LayoutParams) lvMenu1.getLayoutParams();

        if(firstMenuWith == -1) {
            mFirstParams.weight = 1;
            mFirstParams.width = utils.dip2px(mContext, 0);
        }
        else
        {
            mFirstParams.weight = 0;
            mFirstParams.width = utils.dip2px(mContext, firstMenuWith);
        }

        mFirstParams.height = itemHight * showNum;

        lvMenu1.setLayoutParams(mFirstParams);

    }


    /**
     * 设置第二个ListView的属性
     */

    private LinearLayout.LayoutParams mSecondParams;

    private void setSecondMenuLayoutParams()
    {

        if(mSecondParams == null)
            mSecondParams = (LayoutParams) lvMenu2.getLayoutParams();

        mSecondParams.height = itemHight * showNum;

        lvMenu2.setLayoutParams(mSecondParams);

    }




}
