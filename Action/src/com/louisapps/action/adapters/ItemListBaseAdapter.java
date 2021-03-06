package com.louisapps.action.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.louisapps.action.ItemDetails;
import com.louisapps.action.R;

public class ItemListBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsrrayList;
	
	private Integer[] freeTemplates = {
		    R.drawable.image1,
		    R.drawable.image2,
		    R.drawable.image3,
		    R.drawable.image4		    
		  };
	
	private LayoutInflater l_Inflater;
	
	public ItemListBaseAdapter (Context context, ArrayList<ItemDetails> results){
		itemDetailsrrayList = results;
		l_Inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return itemDetailsrrayList.size();
	}

	@Override
	public Object getItem(int position) {
		return itemDetailsrrayList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null){
			convertView = l_Inflater.inflate(R.layout.item_details_view, null);
			holder = new ViewHolder();
			holder.itemImage = (ImageView) convertView.findViewById(R.id.templ);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.itemImage.setImageResource(freeTemplates[itemDetailsrrayList.get(position).getImageNumber()-1]);
		
		return convertView;
	}
	
	static class ViewHolder {
		ImageView itemImage;
	}

}
