package com.louisapps.action.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.louisapps.action.CategoriesItems;
import com.louisapps.action.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class ItemHorizontalListAdapter7 extends BaseAdapter {

private static ArrayList<CategoriesItems> userActionsList;
private Context context;
	
	private Integer[] userActions = {
		    R.drawable.image1_1,
		    R.drawable.image2_1,
		    R.drawable.image3_1,
		    R.drawable.image4_1,
		    R.drawable.image5_1,
		    R.drawable.image6_1,
		    R.drawable.image7_1,
		    R.drawable.image8_1,
		    R.drawable.image9_1,
		    R.drawable.image10_1,
		    R.drawable.image11_1	    
		  };
	
	private String[] userActionsColors = {
			"#FFFFFF",
			"#CEA502",
			"#FF0000",
			"#008080",
			"#2DE59F",
			"#FFE600",
			"#FF8C00",
			"#008000",	
			"#B491D8",
			"#800080",
			"#BAF3F3"		
	};

	private LayoutInflater l_InflaterUA;
	
	public ItemHorizontalListAdapter7 (Context context, ArrayList<CategoriesItems> results){
		userActionsList = results;
		l_InflaterUA = LayoutInflater.from(context);
		this.context = context;
	}


	@Override
	public int getCount() {
		return userActionsList.size();
	}

	@Override
	public Object getItem(int position) {
		return userActionsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null){
			holder = new ViewHolder();
			convertView = l_InflaterUA.inflate(R.layout.horizontal_listview, null);
			holder = new ViewHolder();
			holder.name_on_image = (TextView) convertView.findViewById(R.id.name_on_image);
			holder.date_on_image = (TextView) convertView.findViewById(R.id.date_on_image);
			holder.horizontal_text = (TextView) convertView.findViewById(R.id.horizontal_text);
			holder.horizontal_image = (ImageView) convertView.findViewById(R.id.horizontal_image);
			holder.logoHor = (ImageView) convertView.findViewById(R.id.logoHor);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name_on_image.setText(userActionsList.get(position).getImageTextCompany());
		holder.name_on_image.setTextSize(10);
		holder.name_on_image.setTextColor(Color.parseColor(userActionsColors[userActionsList.get(position).getImageNumber()]));
		holder.name_on_image.setGravity(Gravity.RIGHT);
		holder.name_on_image.setTypeface(Typeface.SERIF, Typeface.BOLD);
		
		holder.date_on_image.setText(userActionsList.get(position).getImageTextDate());
		holder.date_on_image.setTextSize(10);
		holder.date_on_image.setTextColor(Color.parseColor(userActionsColors[userActionsList.get(position).getImageNumber()]));
		holder.date_on_image.setGravity(Gravity.RIGHT);
		holder.date_on_image.setTypeface(Typeface.SERIF, Typeface.BOLD);
		
		holder.horizontal_text.setText(userActionsList.get(position).getImageTextName());
		holder.horizontal_text.setTextSize(12);
		holder.horizontal_text.setTextColor(Color.WHITE);
		holder.horizontal_text.setGravity(Gravity.RIGHT);
		holder.horizontal_text.setTypeface(Typeface.SERIF, Typeface.BOLD);
		
		holder.horizontal_image.setImageResource(userActions[userActionsList.get(position).getImageNumber()]);
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        imageLoader.displayImage(userActionsList.get(position).getImageLogo(), holder.logoHor);
		
		return convertView;

	}
	
	static class ViewHolder {
		ImageView horizontal_image;
		ImageView logoHor;
		TextView name_on_image;
		TextView date_on_image;
		TextView horizontal_text;
	}


}



