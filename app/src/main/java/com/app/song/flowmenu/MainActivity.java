package com.app.song.flowmenu;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.app.song.library.FlowMenu;
import com.app.song.library.adapter.FlowMenuAdapter;
import com.app.song.library.interfaces.ItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    List<String> mDataList1,mDataList2,mDataList3;

    @Bind(R.id.bt_show)
    Button mBtShow;
    @Bind(R.id.fl_show)
    FlowMenu mFlShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        ButterKnife.bind(this);

        mDataList1 = new ArrayList<String>();
        mDataList2 = new ArrayList<String>();
        mDataList3 = new ArrayList<String>();

        mBtShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mBtShow.setVisibility(View.INVISIBLE);
        mFlShow.setShowItemNum(8);

        for (int i = 0; i < 10; i++) {
            mDataList3.add( " item "+i);
        }

        mFlShow.setLvData1(mDataList3);

        mFlShow.setOnItemClickListener(new ItemClickListener() {
            @Override
            public void firstListViewClick(View view, Object o, int item) {
                mDataList1.clear();

                for (int i = 0; i < 10; i++) {
                    mDataList1.add(item + " item "+i);
                }

                ( (FlowMenuAdapter)o).setData(mDataList1);

            }

            @Override
            public void secondListViewClick(View view, Object o, int item) {
                mDataList2.clear();

                for (int i = 0; i < 10; i++) {
                    mDataList2.add(item + " item "+i);
                }

                ( (FlowMenuAdapter)o).setData(mDataList2);
            }

            @Override
            public void thirdListViewClick(View view, Object o, int item) {
                Toast.makeText(MainActivity.this,"show item " + item,Toast.LENGTH_SHORT).show();
            }
        });
    }
}
