package com.app.song.library.adapter;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.song.library.R;

import java.util.List;

/**
 * Created by song on 2016/9/5.
 */
public class FlowMenuAdapter extends BaseAdapter {

    private List<String> mDataList;


    private  FlowMenuAdapter()
    {}

    public static FlowMenuAdapter newInstance()
    {
        return new FlowMenuAdapter();
    }

    public void setData(List<String> mNewDataList)
    {
        mDataList = mNewDataList;
        notifyDataSetChanged();
    }



    @Override
    public int getCount() {

        if(mDataList == null)
            return 0;

        return mDataList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if(view == null)
        {
            holder = new ViewHolder();
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item,null);
            holder.tvItem = (TextView) view.findViewById(R.id.tv_item);
            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }

        holder.tvItem.setText(mDataList.get(i));


        Drawable drawable = viewGroup.getContext().getResources().getDrawable(R.drawable.arrow_right,null);

        drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());

        if(i == 4)
        {
            holder.tvItem.setCompoundDrawables(null,null,drawable,null);
        }
        else
        {
            holder.tvItem.setCompoundDrawables(null,null,null,null);
        }


        return view;
    }

    class ViewHolder{
        TextView tvItem;
    }

}
