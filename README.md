# FlowMenu


FlowMemu for Android 实现了分类选择

<img src="https://github.com/street90/FlowMenu/blob/master/pic/screen.gif" width="270" height="450"/>

usage
-----
**xml**

	    <com.app.song.library.FlowMenu
        android:id="@+id/fl_show"
        android:layout_below="@+id/bt_show"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        />

**Java代码设置**

		mFlShow.setShowItemNum(8);//设置显示的Item数量
		mFlShow.setLvData1(mDataList3);
		mFlShow.setFirstMenuWidth(100);//设置带个menu的宽度
		mFlShow.setOnItemClickListener(new ItemClickListener() {
		@Override
        public void firstListViewClick(View view, Object o, int item) {//第一个listView点击事件

        ((FlowMenuAdapter)o).setData(mDataList1);//第一个返回的是第二个ListView的adapter可以进行赋值

         }

        @Override
        public void secondListViewClick(View view, Object o, int item) {//第二个LestView的点击事件

            }

        });

如果您有什么建议或者意见请联系我
---
* 我的Email : songlang90@outlook.com


License
----------
**WTFPL** 