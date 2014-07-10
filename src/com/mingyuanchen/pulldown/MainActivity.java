package com.mingyuanchen.pulldown;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {

    private List<String> numberList; // 历史号码
	private ListView mListView; // 号码列表控件
	private EditText etNumber;
	private NumberAdapter adapter;
	private PopupWindow pw; // 号码列表对话框

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        etNumber = (EditText) findViewById(R.id.et_number);
        findViewById(R.id.ib_select_number).setOnClickListener(this);
    }

	@Override
	/**
	 * 弹出popupwindow, 让用户选择号码
	 */
	public void onClick(View v) {
		
		// 1. 初始化Listview
		initListView();

		// 2. 把ListView塞给PopupWindow, 让poupWindow显示出号码
		showNumberListDialog();
	}
	
	/**
	 * 初始化Listview
	 */
	private void initListView() {

		
		mListView = new ListView(this);
		mListView.setBackgroundResource(R.drawable.listview_background);
		mListView.setDivider(null);
		mListView.setDividerHeight(0);
		
		mListView.setOnItemClickListener(this);
		numberList = new ArrayList<String>();
		for (int i = 0; i < 20; i++) {
			numberList.add("1888999" + i);
		}
		
		adapter = new NumberAdapter();
		mListView.setAdapter(adapter);
		
	}
	/**
	 * 弹出选择号码的对话框
	 * 抽取变量 alt + shift + L
	 */
	private void showNumberListDialog() {
		pw = new PopupWindow(mListView, etNumber.getWidth() - 4, 200);
		
		// 点击外部可以被关闭
//		pw.setOutsideTouchable(true); 
		pw.setBackgroundDrawable(new BitmapDrawable());
		
		pw.setFocusable(true);// 设置PopupWindow允许获得焦点, 默认情况下popupWindow中的控件不可以获得焦点, 例外: Button, ImageButton..
		pw.showAsDropDown(etNumber, 2, -5);
	}

	

	class NumberAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return numberList.size();
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = null;
			if(convertView == null) {
				view = View.inflate(MainActivity.this, R.layout.number_item, null);
			} else {
				view = convertView;
			}
			
			TextView tvNumber = (TextView) view.findViewById(R.id.tv_number_item_num);
			ImageButton ibDelete = (ImageButton) view.findViewById(R.id.ib_number_item_delete);
			
			tvNumber.setText(numberList.get(position));
			ibDelete.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
//					System.out.println("删除: " + position);
					
					numberList.remove(position);
					// 刷新界面
					adapter.notifyDataSetChanged();
					
					// 如果一条数据都没了, 应该关闭popupWindow
					if(numberList.size() == 0) {
						pw.dismiss(); // 关闭
					}
				}
			});
			
			return view;
		}
		
		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
//		System.out.println("onItemClick 被调用了");
		
		String number = numberList.get(position);
		etNumber.setText(number);
		etNumber.setSelection(number.length());
		pw.dismiss();
	}
}
