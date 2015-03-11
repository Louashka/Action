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

import com.louisapps.action.ItemDetails;
import com.louisapps.action.Preview;
import com.louisapps.action.R;
import com.louisapps.action.adapters.ItemListBaseAdapter;

public class FreeTemplate extends Fragment {
	
	String category;
	String nameAction;
	String action_start;
	String action_end;
	String descrip;
	String number;
	String login;
	String adress;
	
	public FreeTemplate() {
    }
	

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.free_template, container,
                false);
        
        Intent intent = getActivity().getIntent();
        category = intent.getStringExtra("category");
    	nameAction = intent.getStringExtra("nameAction");
    	action_start = intent.getStringExtra("action_start");
    	action_end = intent.getStringExtra("action_end");
    	descrip = intent.getStringExtra("descrip");
    	number = intent.getStringExtra("number");
	    login = intent.getStringExtra("login");
	    adress = intent.getStringExtra("adress");
        
        ArrayList<ItemDetails> image_templ = GetSearchResults();
        
        final ListView lv = (ListView)rootView.findViewById(R.id.freeForms);
        lv.setAdapter(new ItemListBaseAdapter (getActivity(), image_templ));
        
        lv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				
				switch (position){
				case 0:
					Intent intent1 = new Intent();
		            intent1.setClass(getActivity(), Preview.class);
		            intent1.putExtra("number", "0");
		            intent1.putExtra("category", category);
		            intent1.putExtra("nameAction", nameAction);
		            intent1.putExtra("action_start", action_start);
		            intent1.putExtra("action_end", action_end);
		            intent1.putExtra("descrip", descrip);
		            intent1.putExtra("login", login);
		            intent1.putExtra("adress", adress);
		            startActivity(intent1);
		            break;
				case 1:
					Intent intent2 = new Intent();
		            intent2.setClass(getActivity(), Preview.class);
		            intent2.putExtra("number", "1");
		            intent2.putExtra("category", category);
		            intent2.putExtra("nameAction", nameAction);
		            intent2.putExtra("action_start", action_start);
		            intent2.putExtra("action_end", action_end);
		            intent2.putExtra("descrip", descrip);
		            intent2.putExtra("login", login);
		            intent2.putExtra("adress", adress);
		            startActivity(intent2);
		            break;
				case 2:
					Intent intent3 = new Intent();
		            intent3.setClass(getActivity(), Preview.class);
		            intent3.putExtra("number", "2");
		            intent3.putExtra("category", category);
		            intent3.putExtra("nameAction", nameAction);
		            intent3.putExtra("action_start", action_start);
		            intent3.putExtra("action_end", action_end);
		            intent3.putExtra("descrip", descrip);
		            intent3.putExtra("login", login);
		            intent3.putExtra("adress", adress);
		            startActivity(intent3);
		            break;
				case 3:
					Intent intent4 = new Intent();
		            intent4.setClass(getActivity(), Preview.class);
		            intent4.putExtra("number", "3");
		            intent4.putExtra("category", category);
		            intent4.putExtra("nameAction", nameAction);
		            intent4.putExtra("action_start", action_start);
		            intent4.putExtra("action_end", action_end);
		            intent4.putExtra("descrip", descrip);
		            intent4.putExtra("login", login);
		            intent4.putExtra("adress", adress);
		            startActivity(intent4);
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
		
		ItemDetails image_templ = new ItemDetails();
		image_templ.setImageNumber(1);
		results.add(image_templ);
		
		image_templ = new ItemDetails();
		image_templ.setImageNumber(2);
		results.add(image_templ);
		
		image_templ = new ItemDetails();
		image_templ.setImageNumber(3);
		results.add(image_templ);
		
		image_templ = new ItemDetails();
		image_templ.setImageNumber(4);
		results.add(image_templ);

		return results;
	}

}
