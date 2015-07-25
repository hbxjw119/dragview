package com.example.draggridview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.framework.DragGridView;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * @blog http://blog.csdn.net/xiaanming 
 * 
 * @author Âä
 *
 */
public class MainActivity extends RelativeLayout {
	private List<HashMap<String, Object>> dataSourceList = new ArrayList<HashMap<String, Object>>();
	private DragAdapter mDragAdapter;
	private Button add;
	private DragGridView mDragGridView;
	
	public MainActivity(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.activity_main2, this);
		
		// TODO Auto-generated constructor stub
		 mDragGridView = (DragGridView) findViewById(R.id.dragGridView);
		 
			for (int i = 0; i < 10; i++) {
				HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
				itemHashMap.put("item_image",R.drawable.com_tencent_open_notice_msg_icon_big);
				itemHashMap.put("item_text", "ÍÏ×§ " + Integer.toString(i));
				
				dataSourceList.add(itemHashMap);
			}
//			Toast.makeText(this.getContext(), "´´½¨", Toast.LENGTH_SHORT).show();
			mDragAdapter = new DragAdapter(this.getContext(), dataSourceList);
			
			mDragGridView.setAdapter(mDragAdapter);
			
			add = (Button) findViewById(R.id.add);
			add.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
				
					mDragAdapter.addItem(R.drawable.com_tencent_open_notice_msg_icon_big, "ÄãºÃ");
					
				}
			});
			
			mDragGridView.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					// TODO Auto-generated method stub
					if(mDragAdapter.getIsShowDelete())
						mDragAdapter.deleteItem(position);
				}
			});
		
	
	}

	/*
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		
		
		
	}*/

	/*
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		if(mDragAdapter.getIsShowDelete()) {
			
			mDragAdapter.setIsShowDelete(false);
		} else 
			super.onBackPressed();
	}
	*/
	

}
