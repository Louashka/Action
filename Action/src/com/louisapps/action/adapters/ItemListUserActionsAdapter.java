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
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.TextView;

import com.louisapps.action.AllCategoriesItems;
import com.louisapps.action.R;


public class ItemListUserActionsAdapter extends BaseAdapter {
	
private static ArrayList<AllCategoriesItems> userActionsList;
	
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
	
	private Integer[] userActionsGravity = {
			
			Gravity.RIGHT,
			Gravity.CENTER,
			Gravity.LEFT,
			Gravity.RIGHT,
			Gravity.RIGHT,
			Gravity.LEFT,
			Gravity.CENTER,
			Gravity.RIGHT
			
	};
	
	private LayoutInflater l_InflaterUA;
	
	public ItemListUserActionsAdapter (Context context, ArrayList<AllCategoriesItems> results){
		userActionsList = results;
		l_InflaterUA = LayoutInflater.from(context);
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
		TextView tView = null;
		if (convertView == null){
			convertView = l_InflaterUA.inflate(R.layout.item_details_user_categories, null);
			holder = new ViewHolder();
			holder.itemTextAC = (TextView) convertView.findViewById(R.id.textUserActions);
			holder.itemTextAC2 = (TextView) convertView.findViewById(R.id.textUserActionsAlign);
			holder.itemImageAC = (ImageView) convertView.findViewById(R.id.imageUserActions);
			
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		switch (userActionsList.get(position).getImageNumber()){
		case 0:
			tView = holder.itemTextAC2;
			holder.itemTextAC.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.30f));
			holder.itemTextAC.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.70f));
			break;
		case 1:
			tView = holder.itemTextAC2;
			holder.itemTextAC.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0f));
			holder.itemTextAC.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1f));
			break;
		case 2:
			tView = holder.itemTextAC;
			holder.itemTextAC2.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.30f));
			holder.itemTextAC2.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.70f));
			break;
		case 3:
			tView = holder.itemTextAC2;
			holder.itemTextAC.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.20f));
			holder.itemTextAC.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.80f));
			break;
		case 4:
			tView = holder.itemTextAC2;
			holder.itemTextAC.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.5f));
			holder.itemTextAC.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.5f));
			break;
		case 5:
			tView = holder.itemTextAC;
			holder.itemTextAC2.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.40f));
			holder.itemTextAC2.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.60f));
			break;
		case 6:
			tView = holder.itemTextAC2;
			holder.itemTextAC.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0f));
			holder.itemTextAC.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 1f));
			break;
		case 7:
			tView = holder.itemTextAC2;
			holder.itemTextAC.setText("");
			tView.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.30f));
			holder.itemTextAC.setLayoutParams(new TableRow.LayoutParams(
                    LayoutParams.MATCH_PARENT,
                    LayoutParams.MATCH_PARENT, 0.70f));
			break;
    	default:
    		break;
		}

		tView.setText(userActionsList.get(position).getImageText());
		tView.setTextSize(8);
		tView.setTextColor(Color.parseColor(userActionsColors[userActionsList.get(position).getImageNumber()]));
		tView.setGravity(userActionsGravity[userActionsList.get(position).getImageNumber()]);
		tView.setTypeface(Typeface.SERIF, Typeface.BOLD);
		holder.itemImageAC.setImageResource(userActions[userActionsList.get(position).getImageNumber()]);
		
		return convertView;

	}
	
	static class ViewHolder {
		ImageView itemImageAC;
		TextView itemTextAC;
		TextView itemTextAC2;
	}


}
