package com.louisapps.action.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.louisapps.action.R;
import com.louisapps.action.VerticalItems;

public class ItemListAllCategoriesAdapter extends BaseAdapter {
	
	private static ArrayList<VerticalItems> userItemList;
	private LayoutInflater l_InflaterUA;
	Context context;
	
	public ItemListAllCategoriesAdapter (Context context, ArrayList<VerticalItems> results){
		userItemList = results;
		l_InflaterUA = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return userItemList.size();
	}

	@Override
	public Object getItem(int position) {
		return userItemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null){
			convertView = l_InflaterUA.inflate(R.layout.vertical_listview, null);
			holder = new ViewHolder();
			holder.categName = (TextView)convertView.findViewById (R.id.category);
		
			holder.adapter = new ItemHorizontalListAdapter(context, userItemList.get(position).getCategoriesItems());
			
			convertView.setTag(holder);
		}
		else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.categName.setText(userItemList.get(position).getCategName());

		return convertView;
	}
	
	static class ViewHolder {
		TextView categName;
		ItemHorizontalListAdapter adapter;
	}

}
