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

public class ItemListPaidBaseAdapter extends BaseAdapter {
	private static ArrayList<ItemDetails> itemDetailsrrayList1;
	
	private Integer[] paidTemplates = {
		    R.drawable.image5,
		    R.drawable.image6,
		    R.drawable.image7,
		    R.drawable.image8,    
		    R.drawable.image9,
		    R.drawable.image10,
		    R.drawable.image11
		  };
	
	private LayoutInflater l_Inflater1;
	
	public ItemListPaidBaseAdapter (Context context, ArrayList<ItemDetails> results){
		itemDetailsrrayList1 = results;
		l_Inflater1 = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return itemDetailsrrayList1.size();
	}

	@Override
	public Object getItem(int position) {
		return itemDetailsrrayList1.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null){
			convertView = l_Inflater1.inflate(R.layout.item_details_view_paid, null);
			holder = new ViewHolder();
			holder.itemImage1 = (ImageView) convertView.findViewById(R.id.templ_paid);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.itemImage1.setImageResource(paidTemplates[itemDetailsrrayList1.get(position).getImageNumber()-1]);
		
		return convertView;
	}
	
	static class ViewHolder {
		ImageView itemImage1;
	}

}

