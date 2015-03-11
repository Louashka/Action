package com.louisapps.action.fragments;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.louisapps.action.EditPreview;
import com.louisapps.action.ItemDetails;
import com.louisapps.action.R;
import com.louisapps.action.adapters.ItemListPaidBaseAdapter;

public class EditPaidTemplate extends Fragment {

	String objectId;
	String category;
	String nameAction;
	String action_start;
	String action_end;
	String descrip;
	String number;
	String adress;
	
	public EditPaidTemplate() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.paid_template, container,
                false);
        
        Intent intent = getActivity().getIntent();
        objectId = intent.getStringExtra("objectId");
        category = intent.getStringExtra("category");
    	nameAction = intent.getStringExtra("nameAction");
    	action_start = intent.getStringExtra("action_start");
    	action_end = intent.getStringExtra("action_end");
    	descrip = intent.getStringExtra("descrip");
    	number = intent.getStringExtra("number");
    	adress = intent.getStringExtra("adress");
        
        
        ArrayList<ItemDetails> image_templ_paid = GetSearchResults();
        
        final ListView lv = (ListView)rootView.findViewById(R.id.paidForms);
        lv.setAdapter(new ItemListPaidBaseAdapter (getActivity(), image_templ_paid));
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				switch (position){
				case 0:
					Intent intent1 = new Intent();
		            intent1.setClass(getActivity(), EditPreview.class);
		            intent1.putExtra("number", "4");
		            intent1.putExtra("category", category);
		            intent1.putExtra("nameAction", nameAction);
		            intent1.putExtra("action_start", action_start);
		            intent1.putExtra("action_end", action_end);
		            intent1.putExtra("descrip", descrip);
		            intent1.putExtra("adress", adress);
		            intent1.putExtra("objectId", objectId);
		            startActivity(intent1);
		            break;
				case 1:
					Intent intent2 = new Intent();
		            intent2.setClass(getActivity(), EditPreview.class);
		            intent2.putExtra("number", "5");
		            intent2.putExtra("category", category);
		            intent2.putExtra("nameAction", nameAction);
		            intent2.putExtra("action_start", action_start);
		            intent2.putExtra("action_end", action_end);
		            intent2.putExtra("descrip", descrip);
		            intent2.putExtra("adress", adress);
		            intent2.putExtra("objectId", objectId);
		            startActivity(intent2);
		            break;
				case 2:
					Intent intent3 = new Intent();
		            intent3.setClass(getActivity(), EditPreview.class);
		            intent3.putExtra("number", "6");
		            intent3.putExtra("category", category);
		            intent3.putExtra("nameAction", nameAction);
		            intent3.putExtra("action_start", action_start);
		            intent3.putExtra("action_end", action_end);
		            intent3.putExtra("descrip", descrip);
		            intent3.putExtra("adress", adress);
		            intent3.putExtra("objectId", objectId);
		            startActivity(intent3);
		            break;
				case 3:
					Intent intent4 = new Intent();
		            intent4.setClass(getActivity(), EditPreview.class);
		            intent4.putExtra("number", "7");
		            intent4.putExtra("category", category);
		            intent4.putExtra("nameAction", nameAction);
		            intent4.putExtra("action_start", action_start);
		            intent4.putExtra("action_end", action_end);
		            intent4.putExtra("descrip", descrip);
		            intent4.putExtra("adress", adress);
		            intent4.putExtra("objectId", objectId);
		            startActivity(intent4);
		            break;
				case 4:
					Intent intent5 = new Intent();
					intent5.setClass(getActivity(), EditPreview.class);
					intent5.putExtra("number", "8");
					intent5.putExtra("category", category);
					intent5.putExtra("nameAction", nameAction);
					intent5.putExtra("action_start", action_start);
					intent5.putExtra("action_end", action_end);
					intent5.putExtra("descrip", descrip);
					intent5.putExtra("adress", adress);
					intent5.putExtra("objectId", objectId);
		            startActivity(intent5);
		            break;
				case 5:
					Intent intent6 = new Intent();
					intent6.setClass(getActivity(), EditPreview.class);
					intent6.putExtra("number", "9");
					intent6.putExtra("category", category);
					intent6.putExtra("nameAction", nameAction);
					intent6.putExtra("action_start", action_start);
					intent6.putExtra("action_end", action_end);
					intent6.putExtra("descrip", descrip);
					intent6.putExtra("adress", adress);
					intent6.putExtra("objectId", objectId);
		            startActivity(intent6);
		            break;
				case 6:
					Intent intent7 = new Intent();
					intent7.setClass(getActivity(), EditPreview.class);
					intent7.putExtra("number", "10");
					intent7.putExtra("category", category);
					intent7.putExtra("nameAction", nameAction);
					intent7.putExtra("action_start", action_start);
					intent7.putExtra("action_end", action_end);
					intent7.putExtra("descrip", descrip);
					intent7.putExtra("adress", adress);
					intent7.putExtra("objectId", objectId);
		            startActivity(intent7);
		            break;
		        default:
		        	break;	            	
				}							
			}        	
        });

        return rootView;
    }

	private ArrayList<ItemDetails> GetSearchResults() {
		
		ArrayList<ItemDetails> results = new ArrayList<ItemDetails>();
		
		ItemDetails image_templ_paid = new ItemDetails();
		image_templ_paid.setImageNumber(1);
		results.add(image_templ_paid);
		
		image_templ_paid = new ItemDetails();
		image_templ_paid.setImageNumber(2);
		results.add(image_templ_paid);
		
		image_templ_paid = new ItemDetails();
		image_templ_paid.setImageNumber(3);
		results.add(image_templ_paid);
		
		image_templ_paid = new ItemDetails();
		image_templ_paid.setImageNumber(4);
		results.add(image_templ_paid);
		
		image_templ_paid = new ItemDetails();
		image_templ_paid.setImageNumber(5);
		results.add(image_templ_paid);
		
		image_templ_paid = new ItemDetails();
		image_templ_paid.setImageNumber(6);
		results.add(image_templ_paid);
		
		image_templ_paid = new ItemDetails();
		image_templ_paid.setImageNumber(7);
		results.add(image_templ_paid);

		return results;
	}


}
