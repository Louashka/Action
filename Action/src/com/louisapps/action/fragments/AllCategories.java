package com.louisapps.action.fragments;

import java.lang.reflect.Type;
import java.util.ArrayList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.BackendlessCollection;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.louisapps.action.ActionCreation;
import com.louisapps.action.CategoriesItems;
import com.louisapps.action.Defaults;
import com.louisapps.action.GlobalVariable;
import com.louisapps.action.HorizontalListView;
import com.louisapps.action.OpenView;
import com.louisapps.action.R;
import com.louisapps.action.adapters.ItemHorizontalListAdapter;
import com.louisapps.action.adapters.ItemHorizontalListAdapter1;
import com.louisapps.action.adapters.ItemHorizontalListAdapter10;
import com.louisapps.action.adapters.ItemHorizontalListAdapter11;
import com.louisapps.action.adapters.ItemHorizontalListAdapter12;
import com.louisapps.action.adapters.ItemHorizontalListAdapter13;
import com.louisapps.action.adapters.ItemHorizontalListAdapter14;
import com.louisapps.action.adapters.ItemHorizontalListAdapter15;
import com.louisapps.action.adapters.ItemHorizontalListAdapter16;
import com.louisapps.action.adapters.ItemHorizontalListAdapter17;
import com.louisapps.action.adapters.ItemHorizontalListAdapter18;
import com.louisapps.action.adapters.ItemHorizontalListAdapter19;
import com.louisapps.action.adapters.ItemHorizontalListAdapter2;
import com.louisapps.action.adapters.ItemHorizontalListAdapter20;
import com.louisapps.action.adapters.ItemHorizontalListAdapter21;
import com.louisapps.action.adapters.ItemHorizontalListAdapter22;
import com.louisapps.action.adapters.ItemHorizontalListAdapter3;
import com.louisapps.action.adapters.ItemHorizontalListAdapter4;
import com.louisapps.action.adapters.ItemHorizontalListAdapter5;
import com.louisapps.action.adapters.ItemHorizontalListAdapter6;
import com.louisapps.action.adapters.ItemHorizontalListAdapter7;
import com.louisapps.action.adapters.ItemHorizontalListAdapter8;
import com.louisapps.action.adapters.ItemHorizontalListAdapter9;


public class AllCategories extends Fragment {
	
    ProgressDialog progressDialog;   
    ArrayList<CategoriesItems> userItemsList = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList1 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList2 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList3 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList4 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList5 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList6 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList7 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList8 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList9 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList10 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList11 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList12 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList13 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList14 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList15 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList16 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList17 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList18 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList19 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList20 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList21 = new ArrayList<CategoriesItems>();
    ArrayList<CategoriesItems> specItemsList22 = new ArrayList<CategoriesItems>();
    
    ArrayList<String> values = new ArrayList<String>();  ArrayList<String> values1 = new ArrayList<String>();
    ArrayList<String> values2 = new ArrayList<String>();  ArrayList<String> values3 = new ArrayList<String>();
    ArrayList<String> values4 = new ArrayList<String>();  ArrayList<String> values5 = new ArrayList<String>();
    ArrayList<String> values6 = new ArrayList<String>();  ArrayList<String> values7 = new ArrayList<String>();
    ArrayList<String> values8 = new ArrayList<String>();  ArrayList<String> values9 = new ArrayList<String>();
    ArrayList<String> values10 = new ArrayList<String>();  ArrayList<String> values11 = new ArrayList<String>();
    ArrayList<String> values12 = new ArrayList<String>();  ArrayList<String> values13 = new ArrayList<String>();
    ArrayList<String> values14 = new ArrayList<String>();  ArrayList<String> values15 = new ArrayList<String>();
    ArrayList<String> values16 = new ArrayList<String>();  ArrayList<String> values17 = new ArrayList<String>();
    ArrayList<String> values18 = new ArrayList<String>();  ArrayList<String> values19 = new ArrayList<String>();
    ArrayList<String> values20 = new ArrayList<String>();  ArrayList<String> values21 = new ArrayList<String>();
    ArrayList<String> values22 = new ArrayList<String>();

    ArrayList<String> specItemsListID = new ArrayList<String>();
    HorizontalListView listV[] = new HorizontalListView[23];
    LinearLayout listLayout[] = new LinearLayout[23];
    boolean booleans[] = new boolean[23]; 
    ArrayList<String> actionCateg = new ArrayList<String>();
    LinearLayout listv;
    int k = 0;
    int listValues[] = {
    		R.id.mListview, R.id.mListview1, R.id.mListview2, R.id.mListview3, 
    		R.id.mListview4, R.id.mListview5, R.id.mListview6, R.id.mListview7, 
    		R.id.mListview8, R.id.mListview9, R.id.mListview10, R.id.mListview11, 
    		R.id.mListview12, R.id.mListview13, R.id.mListview14, R.id.mListview15, 
    		R.id.mListview16, R.id.mListview17, R.id.mListview18, R.id.mListview19, 
    		R.id.mListview20, R.id.mListview21, R.id.mListview22   		
    };
    int layoutValues[] = {
    		R.id.listv, R.id.listv1, R.id.listv2, R.id.listv3, R.id.listv4, R.id.listv5,
    		R.id.listv6, R.id.listv7, R.id.listv8, R.id.listv9, R.id.listv10, R.id.listv11,
    		R.id.listv12, R.id.listv13, R.id.listv14, R.id.listv15, R.id.listv16, 
    		R.id.listv17, R.id.listv18, R.id.listv19, R.id.listv20, R.id.listv21, R.id.listv22
    };   
    String categories[] = new String[]{
    		"Авто", "Аптеки и оптика", "Аудио/Видео", "Банки", "Вещи для дома", "Детские товары",
    		"Зоомагазины", "Кафе и Рестораны", "Книжные магазины", "Компьютеры и оргтехника",
            "Косметика и парфюмерия", "Мебель", "Обувь", "Одежда", "Садоводство", 
            "Салоны красоты", "Спорттовары",  "Сувениры", "Сумки и аксессуары", 
            "Текстиль", "Телефоны и аксессуары", "Туризм", "Ювелирные изделия"
    };
    private Integer[] viewVisibility = new Integer[23]; 
    SharedPreferences sharedPref;
    SharedPreferences sharedListV;
    SharedPreferences sharedValues;
    SharedPreferences.Editor listEditor;
    SharedPreferences.Editor valuesEditor;
    String categNames[];
    String jsonRet[] = new String[23];
    String jsonRetVal[] = new String[23];
    Type type[] = new Type[23];
    Type typeVal[] = new Type[23];
    ItemHorizontalListAdapter adapter0; ItemHorizontalListAdapter1 adapter1;
    ItemHorizontalListAdapter2 adapter2; ItemHorizontalListAdapter3 adapter3;
    ItemHorizontalListAdapter4 adapter4; ItemHorizontalListAdapter5 adapter5;
    ItemHorizontalListAdapter6 adapter6; ItemHorizontalListAdapter7 adapter7;
    ItemHorizontalListAdapter8 adapter8; ItemHorizontalListAdapter9 adapter9;
    ItemHorizontalListAdapter10 adapter10; ItemHorizontalListAdapter11 adapter11;
    ItemHorizontalListAdapter12 adapter12; ItemHorizontalListAdapter13 adapter13;
    ItemHorizontalListAdapter14 adapter14; ItemHorizontalListAdapter15 adapter15;
    ItemHorizontalListAdapter16 adapter16; ItemHorizontalListAdapter17 adapter17;
    ItemHorizontalListAdapter18 adapter18; ItemHorizontalListAdapter19 adapter19;
    ItemHorizontalListAdapter20 adapter20; ItemHorizontalListAdapter21 adapter21;
    ItemHorizontalListAdapter22 adapter22; 
    
    public AllCategories() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
        View rootView = inflater.inflate(R.layout.all_categories, container,
                false);

        progressDialog = ProgressDialog.show( getActivity(), "", "Loading", true );
        sharedPref = ((GlobalVariable)getActivity().getApplicationContext()).getSharedPref();
        categNames = getResources().getStringArray(R.array.views_array);
        for (int i = 0; i < listV.length; i++) {
        	listV[i] = (HorizontalListView) rootView.findViewById (listValues[i]);
        	listLayout[i] = (LinearLayout) rootView.findViewById (layoutValues[i]);
        	booleans[i] = sharedPref.getBoolean(categories[i], true);
        }  
    	for (int i = 0; i < viewVisibility.length; i++) {
    		if (booleans[i]) {
    			viewVisibility[i] = View.VISIBLE;
    		} else {
    			viewVisibility[i] = View.GONE;
    		}    	    
    	}
    	for (int i = 0; i < viewVisibility.length; i++)
    		listLayout[i].setVisibility(viewVisibility[i]);
    	
    	sharedListV = getActivity().getSharedPreferences("actionArrays", 0);
    	sharedValues = getActivity().getSharedPreferences("valuesArrays", 0);
    	listEditor = sharedListV.edit();
//    	for (int i = 0; i < categories.length; i++){
//    		if (sharedListV.getString(categories[i], null) != null)
//    			++k;
//    	}
    	
    	if (k == 0) {
    		createView();
    	} else {
    		Gson gsonRet = new Gson();
    		
    		for (int i = 0; i < categories.length; i++) {
    			jsonRet[i] = sharedListV.getString(categories[i], "");
    			type[i] = new TypeToken<ArrayList<CategoriesItems>>(){}.getType();
    			jsonRetVal[i] = sharedValues.getString(categories[i], "");
    			typeVal[i] = new TypeToken<ArrayList<String>>(){}.getType();
    		}   		
    		specItemsList = gsonRet.fromJson(jsonRet[0], type[0]);
    		specItemsList1 = gsonRet.fromJson(jsonRet[1], type[1]);
    		specItemsList2 = gsonRet.fromJson(jsonRet[2], type[2]);
    		specItemsList3 = gsonRet.fromJson(jsonRet[3], type[3]);
    		specItemsList4 = gsonRet.fromJson(jsonRet[4], type[4]);
    		specItemsList5 = gsonRet.fromJson(jsonRet[5], type[5]);
    		specItemsList6 = gsonRet.fromJson(jsonRet[6], type[6]);
    		specItemsList7 = gsonRet.fromJson(jsonRet[7], type[7]);
    		specItemsList8 = gsonRet.fromJson(jsonRet[8], type[8]);
    		specItemsList9 = gsonRet.fromJson(jsonRet[9], type[9]);
    		specItemsList10 = gsonRet.fromJson(jsonRet[10], type[10]);
    		specItemsList11 = gsonRet.fromJson(jsonRet[11], type[11]);
    		specItemsList12 = gsonRet.fromJson(jsonRet[12], type[12]);
    		specItemsList13 = gsonRet.fromJson(jsonRet[13], type[13]);
    		specItemsList14 = gsonRet.fromJson(jsonRet[14], type[14]);
    		specItemsList15 = gsonRet.fromJson(jsonRet[15], type[15]);
    		specItemsList16 = gsonRet.fromJson(jsonRet[16], type[16]);
    		specItemsList17 = gsonRet.fromJson(jsonRet[17], type[17]);
    		specItemsList18 = gsonRet.fromJson(jsonRet[18], type[18]);
    		specItemsList19 = gsonRet.fromJson(jsonRet[19], type[19]);
    		specItemsList20 = gsonRet.fromJson(jsonRet[20], type[20]);
    		specItemsList21 = gsonRet.fromJson(jsonRet[21], type[21]);
    		specItemsList22 = gsonRet.fromJson(jsonRet[22], type[22]); 
    		
    		values = gsonRet.fromJson(jsonRetVal[0], typeVal[0]); 
    		values1 = gsonRet.fromJson(jsonRetVal[1], typeVal[1]);
    		values2 = gsonRet.fromJson(jsonRetVal[2], typeVal[2]);
    		values3 = gsonRet.fromJson(jsonRetVal[3], typeVal[3]);
    		values4 = gsonRet.fromJson(jsonRetVal[4], typeVal[4]);
    		values5 = gsonRet.fromJson(jsonRetVal[5], typeVal[5]);
    		values6 = gsonRet.fromJson(jsonRetVal[6], typeVal[6]);
    		values7 = gsonRet.fromJson(jsonRetVal[7], typeVal[7]);
    		values8 = gsonRet.fromJson(jsonRetVal[8], typeVal[8]);
    		values9 = gsonRet.fromJson(jsonRetVal[9], typeVal[9]);
    		values10 = gsonRet.fromJson(jsonRetVal[10], typeVal[10]);
    		values11 = gsonRet.fromJson(jsonRetVal[11], typeVal[11]);
    		values12 = gsonRet.fromJson(jsonRetVal[12], typeVal[12]);
    		values13 = gsonRet.fromJson(jsonRetVal[13], typeVal[13]);
    		values14 = gsonRet.fromJson(jsonRetVal[14], typeVal[14]);
    		values15 = gsonRet.fromJson(jsonRetVal[15], typeVal[15]);
    		values16 = gsonRet.fromJson(jsonRetVal[16], typeVal[16]);
    		values17 = gsonRet.fromJson(jsonRetVal[17], typeVal[17]);
    		values18 = gsonRet.fromJson(jsonRetVal[18], typeVal[18]);
    		values19 = gsonRet.fromJson(jsonRetVal[19], typeVal[19]);
    		values20 = gsonRet.fromJson(jsonRetVal[20], typeVal[20]);
    		values21 = gsonRet.fromJson(jsonRetVal[21], typeVal[21]);
    		values22 = gsonRet.fromJson(jsonRetVal[22], typeVal[22]);
    		
    		if (specItemsList != null) {
    			if (!specItemsList.isEmpty()) {
        			adapter0 = new ItemHorizontalListAdapter(getActivity(), specItemsList);
        			listV[0].setAdapter(adapter0);
        			clickListener(listV[0], values);
        		} else {
        			listLayout[0].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[0].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList1 != null) {
    			if (!specItemsList1.isEmpty()) {
        			adapter1 = new ItemHorizontalListAdapter1(getActivity(), specItemsList1);
        			listV[1].setAdapter(adapter1);
        			clickListener(listV[1], values1);
        		} else {
        			listLayout[1].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[0].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList2 != null) {
    			if (!specItemsList2.isEmpty()) {
        			adapter2 = new ItemHorizontalListAdapter2(getActivity(), specItemsList2);
        			listV[2].setAdapter(adapter2);
        			clickListener(listV[2], values2);
        		} else {
        			listLayout[2].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[2].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList3 != null) {
    			if (!specItemsList3.isEmpty()) {
        			adapter3 = new ItemHorizontalListAdapter3(getActivity(), specItemsList3);
        			listV[3].setAdapter(adapter3);
        			clickListener(listV[3], values3);
        		} else {
        			listLayout[3].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[3].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList4 != null) {
    			if (!specItemsList4.isEmpty()) {
        			adapter4 = new ItemHorizontalListAdapter4(getActivity(), specItemsList4);
        			listV[4].setAdapter(adapter4);
        			clickListener(listV[4], values4);
        		} else {
        			listLayout[4].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[4].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList5 != null) {
    			if (!specItemsList5.isEmpty()) {
        			adapter5 = new ItemHorizontalListAdapter5(getActivity(), specItemsList5);
        			listV[5].setAdapter(adapter5);
        			clickListener(listV[5], values5);
        		} else {
        			listLayout[5].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[5].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList6 != null) {
    			if (!specItemsList6.isEmpty()) {
        			adapter6 = new ItemHorizontalListAdapter6(getActivity(), specItemsList6);
        			listV[6].setAdapter(adapter6);
        			clickListener(listV[6], values6);
        		} else {
        			listLayout[6].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[6].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList7 != null) {
    			if (!specItemsList7.isEmpty()) {
        			adapter7 = new ItemHorizontalListAdapter7(getActivity(), specItemsList7);
        			listV[7].setAdapter(adapter7);
        			clickListener(listV[7], values7);
        		} else {
        			listLayout[7].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[7].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList8 != null) {
    			if (!specItemsList8.isEmpty()) {
        			adapter8 = new ItemHorizontalListAdapter8(getActivity(), specItemsList8);
        			listV[8].setAdapter(adapter8);
        			clickListener(listV[8], values8);
        		} else {
        			listLayout[8].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[8].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList9 != null) {
    			if (!specItemsList9.isEmpty()) {
        			adapter9 = new ItemHorizontalListAdapter9(getActivity(), specItemsList9);
        			listV[9].setAdapter(adapter9);
        			clickListener(listV[9], values9);
        		} else {
        			listLayout[9].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[9].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList10 != null) {
    			if (!specItemsList10.isEmpty()) {
        			adapter10 = new ItemHorizontalListAdapter10(getActivity(), specItemsList10);
        			listV[10].setAdapter(adapter10);
        			clickListener(listV[10], values10);
        		} else {
        			listLayout[10].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[10].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList11 != null) {
    			if (!specItemsList11.isEmpty()) {
        			adapter11 = new ItemHorizontalListAdapter11(getActivity(), specItemsList11);
        			listV[11].setAdapter(adapter11);
        			clickListener(listV[11], values11);
        		} else {
        			listLayout[11].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[11].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList12 != null) {
    			if (!specItemsList12.isEmpty()) {
        			adapter12 = new ItemHorizontalListAdapter12(getActivity(), specItemsList12);
        			listV[12].setAdapter(adapter12);
        			clickListener(listV[12], values12);
        		} else {
        			listLayout[12].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[12].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList13 != null) {
    			if (!specItemsList13.isEmpty()) {
        			adapter13 = new ItemHorizontalListAdapter13(getActivity(), specItemsList13);
        			listV[13].setAdapter(adapter13);
        			clickListener(listV[13], values13);
        		} else {
        			listLayout[13].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[13].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList14 != null) {
    			if (!specItemsList14.isEmpty()) {
        			adapter14 = new ItemHorizontalListAdapter14(getActivity(), specItemsList14);
        			listV[14].setAdapter(adapter14);
        			clickListener(listV[14], values14);
        		} else {
        			listLayout[14].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[14].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList15 != null) {
    			if (!specItemsList15.isEmpty()) {
        			adapter15 = new ItemHorizontalListAdapter15(getActivity(), specItemsList15);
        			listV[15].setAdapter(adapter15);
        			clickListener(listV[15], values15);
        		} else {
        			listLayout[15].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[15].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList16 != null) {
    			if (!specItemsList16.isEmpty()) {
        			adapter16 = new ItemHorizontalListAdapter16(getActivity(), specItemsList16);
        			listV[16].setAdapter(adapter16);
        			clickListener(listV[16], values16);
        		} else {
        			listLayout[16].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[16].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList17 != null) {
    			if (!specItemsList17.isEmpty()) {
        			adapter17 = new ItemHorizontalListAdapter17(getActivity(), specItemsList17);
        			listV[17].setAdapter(adapter17);
        			clickListener(listV[17], values17);
        		} else {
        			listLayout[17].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[17].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList18 != null) {
    			if (!specItemsList18.isEmpty()) {
        			adapter18 = new ItemHorizontalListAdapter18(getActivity(), specItemsList18);
        			listV[18].setAdapter(adapter18);
        			clickListener(listV[18], values18);
        		} else {
        			listLayout[18].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[18].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList19 != null) {
    			if (!specItemsList19.isEmpty()) {
        			adapter19 = new ItemHorizontalListAdapter19(getActivity(), specItemsList19);
        			listV[19].setAdapter(adapter19);
        			clickListener(listV[19], values19);
        		} else {
        			listLayout[19].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[19].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList20 != null) {
    			if (!specItemsList20.isEmpty()) {
        			adapter20 = new ItemHorizontalListAdapter20(getActivity(), specItemsList20);
        			listV[20].setAdapter(adapter20);
        			clickListener(listV[20], values20);
        		} else {
        			listLayout[20].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[20].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList21 != null) {
    			if (!specItemsList21.isEmpty()) {
        			adapter21 = new ItemHorizontalListAdapter21(getActivity(), specItemsList21);
        			listV[21].setAdapter(adapter21);
        			clickListener(listV[21], values21);
        		} else {
        			listLayout[21].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[21].setVisibility(View.GONE);
    		}
    		
    		if (specItemsList22 != null) {
    			if (!specItemsList22.isEmpty()) {
        			adapter22 = new ItemHorizontalListAdapter22(getActivity(), specItemsList22);
        			listV[22].setAdapter(adapter22);
        			clickListener(listV[22], values22);
        		} else {
        			listLayout[22].setVisibility(View.GONE);
        		}
    		} else {
    			listLayout[22].setVisibility(View.GONE);
    		}
    		progressDialog.cancel();
    	}
                      	       
        return rootView;
    }
    
    public void clickListener (HorizontalListView lv, final ArrayList<String> objId) {
    	lv.setOnItemClickListener(new OnItemClickListener() {            		
       		
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
	        	Intent intent =new Intent(getActivity(), OpenView.class);
	        	intent.putExtra("objectId", objId.get(position));
	        	startActivity(intent);
			}
    	});
    }
    
    public static void createTextView (Context context, HorizontalListView lv,
                                       LinearLayout linearLayout) {
    	
        lv.setVisibility(View.GONE);
		  TextView textView = new TextView(context);
		  textView.setText(R.string.empty_category);
		  textView.setTextColor(Color.WHITE);
		  textView.setBackgroundResource(R.drawable.back);          	    		  
		  textView.setPadding(30, 70, 30, 70);
		  LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		  layoutParams.setMargins(15, 20, 10, 15);
		  layoutParams.gravity = Gravity.CENTER;
		  textView.setLayoutParams(layoutParams);
		  textView.setGravity(Gravity.CENTER_HORIZONTAL);
		  
		  linearLayout.addView(textView);
    }
    
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setRetainInstance(true);
      }
    
    public boolean getCheck (LinearLayout linLay) {
    	boolean value = false;
    	if (linLay.getVisibility() != View.GONE)
    		value = true;
    	return value;
    }
    
    public boolean[] getCheckOfItems () {
    	boolean value[] = new boolean[22];
    	for (int i = 0; i < 22; i++){
    		if (listLayout[0].getVisibility() != View.GONE) {
    			value[i] = true;
    		} else {
    		value[i] = false;
    		}
    	}
    	return value;
    }
    
    public void hideItems (boolean values[]) {
    	for (int i = 0; i < values.length; i++){
    		if (values[i] == false) {
    			listLayout[i].setVisibility(View.GONE);
    		} else {
    			listLayout[i].setVisibility(View.VISIBLE);
    		}
    	}
    }
    
    public ArrayList<CategoriesItems> getAdapter (ArrayList<CategoriesItems> userArraList,
    		                ArrayList<CategoriesItems> specArraList, String category) {
    	specArraList.clear();
    	for (int j = 0; j < userArraList.size(); j++) {
 			if (actionCateg.get(j).equals(category)){
 				specArraList.add(userArraList.get(j));  
 			}
     	}
    	return specArraList;
    }
    
    public void shared(ArrayList<CategoriesItems> specArraList, String category,
                       ArrayList<String> val) {
    	
	        listEditor = sharedListV.edit();
	        Gson gson = new Gson();
	        String json = gson.toJson(specArraList);
	        listEditor.putString(category, json);
	        listEditor.commit(); 
	        
	        listEditor = sharedValues.edit();
	        Gson gsonVal = new Gson();
	        String jsonVal = gsonVal.toJson(val);
	        listEditor.putString(category, jsonVal);
	        listEditor.commit();
    }
    
    public void fillListV (ArrayList<CategoriesItems> userArraList,
    		               ArrayList<CategoriesItems> specArraList, String category,
    		               ArrayList<String> val, BaseAdapter userAdapter,
    		               HorizontalListView lv, LinearLayout linearLayout) {
    	boolean visibility = false;
    	for (int j = 0; j < userArraList.size(); j++) {
 			if (actionCateg.get(j).equals(category)){ 
 				val.add(specItemsListID.get(j));
 				for (int i = 0; i < categories.length; i++) {
 					if (categories[i].equals(actionCateg.get(j)))
 						visibility = booleans[i];
 				}
 			}
     	}
    	lv.setAdapter(userAdapter);
    	shared(specArraList, category, val);
    	if (!specArraList.isEmpty()){
   	        clickListener(lv, val);
   	        if (visibility)
   	        	linearLayout.setVisibility(View.VISIBLE);
    	} else {
    		linearLayout.setVisibility(View.GONE);
    	}
    }
    
    public void createView () {
    	Defaults.getTasks(new BackendlessCallback<BackendlessCollection<ActionCreation>>() {

			@Override
			public void handleResponse(
					BackendlessCollection<ActionCreation> taskBackendlessCollection) {
				for( ActionCreation task : taskBackendlessCollection.getCurrentPage()) {
					 userItemsList.add(new CategoriesItems (task.getNumberImage(), task.getNameCompany(),
		    				  "c " + task.getAction_start() + "\nпо " + task.getAction_end(), task.getNameAction(), task.getLogo()));
					 actionCateg.add(task.getCategory());
					 specItemsListID.add(task.getObjectId());
				}
				
				specItemsList = getAdapter(userItemsList, specItemsList, categNames[0]);
				adapter0 = new ItemHorizontalListAdapter(getActivity(), specItemsList);
				
				fillListV (userItemsList, specItemsList, categNames[0], values, adapter0, 
						   listV[0], listLayout[0]);
				
				specItemsList1 = getAdapter(userItemsList, specItemsList1, categNames[1]);
				adapter1 = new ItemHorizontalListAdapter1(getActivity(), specItemsList1);
				fillListV (userItemsList, specItemsList1, categNames[1], values1, adapter1, 
						   listV[1], listLayout[1]);
				
				specItemsList2 = getAdapter(userItemsList, specItemsList2, categNames[2]);
				adapter2 = new ItemHorizontalListAdapter2(getActivity(), specItemsList2);
				fillListV (userItemsList, specItemsList2, categNames[2], values2, adapter2, 
						   listV[2], listLayout[2]);
		    	
				specItemsList3 = getAdapter(userItemsList, specItemsList3, categNames[3]);
				adapter3 = new ItemHorizontalListAdapter3(getActivity(), specItemsList3);
				fillListV (userItemsList, specItemsList3, categNames[3], values3, adapter3, 
						   listV[3], listLayout[3]);
				
				specItemsList4 = getAdapter(userItemsList, specItemsList4, categNames[4]);
				adapter4 = new ItemHorizontalListAdapter4(getActivity(), specItemsList4);
				fillListV (userItemsList, specItemsList4, categNames[4], values4, adapter4, 
						   listV[4], listLayout[4]);
				
				specItemsList5 = getAdapter(userItemsList, specItemsList5, categNames[5]);
				adapter5 = new ItemHorizontalListAdapter5(getActivity(), specItemsList5);
				fillListV (userItemsList, specItemsList5, categNames[5], values5, adapter5, 
						   listV[5], listLayout[5]);
				
				specItemsList6 = getAdapter(userItemsList, specItemsList6, categNames[6]);
				adapter6 = new ItemHorizontalListAdapter6(getActivity(), specItemsList6);
				fillListV (userItemsList, specItemsList6, categNames[6], values6, adapter6, 
						   listV[6], listLayout[6]);
				
				specItemsList7 = getAdapter(userItemsList, specItemsList7, categNames[7]);
				adapter7 = new ItemHorizontalListAdapter7(getActivity(), specItemsList7);
				fillListV (userItemsList, specItemsList7, categNames[7], values7, adapter7, 
						   listV[7], listLayout[7]);
				
				specItemsList8 = getAdapter(userItemsList, specItemsList8, categNames[8]);
				adapter8 = new ItemHorizontalListAdapter8(getActivity(), specItemsList8);
				fillListV (userItemsList, specItemsList8, categNames[8], values8, adapter8, 
						   listV[8], listLayout[8]);
				
				specItemsList9 = getAdapter(userItemsList, specItemsList9, categNames[9]);
				adapter9 = new ItemHorizontalListAdapter9(getActivity(), specItemsList9);
				fillListV (userItemsList, specItemsList9, categNames[9], values9, adapter9, 
						   listV[9], listLayout[9]);
				
				specItemsList10 = getAdapter(userItemsList, specItemsList10, categNames[10]);
				adapter10 = new ItemHorizontalListAdapter10(getActivity(), specItemsList10);
				fillListV (userItemsList, specItemsList10, categNames[10], values10, adapter10, 
						   listV[10], listLayout[10]);
				
				specItemsList11 = getAdapter(userItemsList, specItemsList11, categNames[11]);
				adapter11 = new ItemHorizontalListAdapter11(getActivity(), specItemsList11);
				fillListV (userItemsList, specItemsList11, categNames[11], values11, adapter11, 
						   listV[11], listLayout[11]);
				
				specItemsList12 = getAdapter(userItemsList, specItemsList12, categNames[12]);
				adapter12 = new ItemHorizontalListAdapter12(getActivity(), specItemsList12);
				fillListV (userItemsList, specItemsList12, categNames[12], values12, adapter12, 
						   listV[12], listLayout[12]);
				
				specItemsList13 = getAdapter(userItemsList, specItemsList13, categNames[13]);
				adapter13 = new ItemHorizontalListAdapter13(getActivity(), specItemsList13);
				fillListV (userItemsList, specItemsList13, categNames[13], values13, adapter13, 
						   listV[13], listLayout[13]);
				
				specItemsList14 = getAdapter(userItemsList, specItemsList14, categNames[14]);
				adapter14 = new ItemHorizontalListAdapter14(getActivity(), specItemsList14);
				fillListV (userItemsList, specItemsList14, categNames[14], values14, adapter14, 
						   listV[14], listLayout[14]);
				
				specItemsList15 = getAdapter(userItemsList, specItemsList15, categNames[15]);
				adapter15 = new ItemHorizontalListAdapter15(getActivity(), specItemsList15);
				fillListV (userItemsList, specItemsList15, categNames[15], values15, adapter15, 
						   listV[15], listLayout[15]);
				
				specItemsList16 = getAdapter(userItemsList, specItemsList16, categNames[16]);
				adapter16 = new ItemHorizontalListAdapter16(getActivity(), specItemsList16);
				fillListV (userItemsList, specItemsList16, categNames[16], values16, adapter16, 
						   listV[16], listLayout[16]);
				
				specItemsList17 = getAdapter(userItemsList, specItemsList17, categNames[17]);
				adapter17 = new ItemHorizontalListAdapter17(getActivity(), specItemsList17);
				fillListV (userItemsList, specItemsList17, categNames[17], values17, adapter17, 
						   listV[17], listLayout[17]);
				
				specItemsList18 = getAdapter(userItemsList, specItemsList18, categNames[18]);
				adapter18 = new ItemHorizontalListAdapter18(getActivity(), specItemsList18);
				fillListV (userItemsList, specItemsList18, categNames[18], values18, adapter18, 
						   listV[18], listLayout[18]);
				
				specItemsList19 = getAdapter(userItemsList, specItemsList19, categNames[19]);
				adapter19 = new ItemHorizontalListAdapter19(getActivity(), specItemsList19);
				fillListV (userItemsList, specItemsList19, categNames[19], values19, adapter19, 
						   listV[19], listLayout[19]);
				
				specItemsList20 = getAdapter(userItemsList, specItemsList20, categNames[20]);
				adapter20 = new ItemHorizontalListAdapter20(getActivity(), specItemsList20);
				fillListV (userItemsList, specItemsList20, categNames[20], values20, adapter20, 
						   listV[20], listLayout[20]);
				
				specItemsList21 = getAdapter(userItemsList, specItemsList21, categNames[21]);
				adapter21 = new ItemHorizontalListAdapter21(getActivity(), specItemsList21);
				fillListV (userItemsList, specItemsList21, categNames[21], values21, adapter21, 
						   listV[21], listLayout[21]);
				
				specItemsList22 = getAdapter(userItemsList, specItemsList22, categNames[22]);
				adapter22 = new ItemHorizontalListAdapter22(getActivity(), specItemsList22);
				fillListV (userItemsList, specItemsList22, categNames[2], values22, adapter22, 
						   listV[22], listLayout[22]);
		    	
				progressDialog.cancel();
			}
			
			@Override
    	    public void handleFault( BackendlessFault backendlessFault )
    	    {
    	        progressDialog.cancel();
    	        Toast.makeText( getActivity(), backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
    	    }
        	
        });
    }

}

