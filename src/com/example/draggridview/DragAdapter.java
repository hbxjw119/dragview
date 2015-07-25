package com.example.draggridview;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import com.example.framework.DragGridBaseAdapter;
//import com.example.longdemo.R;
import com.example.framework.DragGridView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @blog http://blog.csdn.net/xiaanming
 * 
 * @author 落
 *
 */
public class DragAdapter extends BaseAdapter implements DragGridBaseAdapter {
	private List<HashMap<String, Object>> list;
	private LayoutInflater mInflater;
	private int mHidePosition = -1;
	

	// X图标
	private View deleteView;
	private boolean isShowDelete;
	private ImageView xicon;

	public DragAdapter(Context context, List<HashMap<String, Object>> list) {
		this.list = list;
		mInflater = LayoutInflater.from(context);
		/*
		 * View convertView = mInflater.inflate(R.layout.grid_item, null);
		 * deleteView = convertView.findViewById(R.id.delete_markView);
		 * 
		 * deleteView.setOnClickListener(new OnClickListener() {
		 * 
		 * @Override public void onClick(View v) { // TODO Auto-generated method
		 * stub Toast.makeText(v.getContext(), "我爱你",
		 * Toast.LENGTH_SHORT).show();; } });
		 */
		// xicon = (ImageView)
	}

	@Override
	public int getCount() {
		return list.size();
	}

	public void addItem(Object img, String des) {
		HashMap<String, Object> itemHashMap = new HashMap<String, Object>();
		itemHashMap.put("item_image", img);

		itemHashMap.put("item_text", des);
		list.add(itemHashMap);
		notifyDataSetChanged();
	}

	public void setIsShowDelete(boolean isShowDelete) {
		this.isShowDelete = isShowDelete;
		notifyDataSetChanged();
	}
	public boolean getIsShowDelete() {	
		return this.isShowDelete;
	}
	public void deleteItem(int position) {

		Log.e("main", ""+position);
		Log.e("main", ""+getIsShowDelete());
		if (position >= 0 && isShowDelete) {
			list.remove(position);
			notifyDataSetChanged();	
		}
		
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	/**
	 * 由于复用convertView导致某些item消失了，所以这里不复用item，
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(R.layout.grid_item, null);
		ImageView mImageView = (ImageView) convertView.findViewById(R.id.item_image);
		TextView mTextView = (TextView) convertView.findViewById(R.id.item_text);

		// delete view
		deleteView = convertView.findViewById(R.id.delete_markView);
		deleteView.setVisibility(isShowDelete ? View.VISIBLE : View.GONE);
		
		
		mImageView.setImageResource((Integer) list.get(position).get("item_image"));
		mTextView.setText((CharSequence) list.get(position).get("item_text"));

		if (position == mHidePosition) {
			convertView.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

	@Override
	public void reorderItems(int oldPosition, int newPosition) {
		HashMap<String, Object> temp = list.get(oldPosition);
		if (oldPosition < newPosition) {
			for (int i = oldPosition; i < newPosition; i++) {
				Collections.swap(list, i, i + 1);
			}
		} else if (oldPosition > newPosition) {
			for (int i = oldPosition; i > newPosition; i--) {
				Collections.swap(list, i, i - 1);
			}
		}

		list.set(newPosition, temp);
	}

	@Override
	public void setHideItem(int hidePosition) {
		this.mHidePosition = hidePosition;
		notifyDataSetChanged();
	}

}
