package com.louisapps.action;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.louisapps.action.fragments.DatePicker;
import com.louisapps.action.fragments.DatePicker1;

public class Create_action extends ActionBarActivity implements OnItemSelectedListener {
	
	private Spinner spinner;
	EditText name_of_action;
	EditText description;
	EditText actionStart;
	EditText actionEnd;
	EditText acAdress;
	String category;
	String nameAction;
	String action_start;
	String action_end;
	String descrip;
	String login;
	String adress;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.create_action);
	        setTitle("Создать акцию");
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        spinner = (Spinner) findViewById(R.id.categories);
	        spinner.setOnItemSelectedListener(this);
	     // Create an ArrayAdapter using the string array and a default spinner layout
	     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
	             R.array.views_array, R.layout.spinner_row);
	     // Specify the layout to use when the list of choices appears
	     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	     // Apply the adapter to the spinner
	     spinner.setAdapter(adapter);
	     
	     name_of_action = (EditText) findViewById(R.id.name_of_action);
	     description = (EditText) findViewById(R.id.describtion);
	     actionStart = (EditText) findViewById(R.id.actionStart);
	     actionEnd = (EditText) findViewById(R.id.actionEnd);
	     acAdress = (EditText) findViewById(R.id.acAdress);
	     
	     Intent intent = getIntent();
	     login = intent.getStringExtra("login");
	     
	    }

		public void onItemSelected(AdapterView<?> parent, View view, int position,
				long id) {
			String[] choose = getResources().getStringArray(R.array.views_array);
			category = choose[position];
		}

		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub		
		}
	   
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        // Respond to the action bar's Up/Home button
	            case android.R.id.home:
	                NavUtils.navigateUpFromSameTask(this);
	                return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	    
	    public void onClick(View v) {
	        DialogFragment dateDialog = new DatePicker();
	        dateDialog.show(getSupportFragmentManager(), "datePicker");
	    }
	    
	    public void onClick1(View v) {
	        DialogFragment dateDialog1 = new DatePicker1();
	        dateDialog1.show(getSupportFragmentManager(), "datePicker");
	    }
	    
	    public void onClick2(View v) {
	    	
	    	nameAction = name_of_action.getText().toString();
	    	action_start = actionStart.getText().toString();
	    	action_end = actionEnd.getText().toString();
	    	descrip = description.getText().toString();
	    	adress = acAdress.getText().toString();
            
        	if (TextUtils.isEmpty(nameAction)
        	        || TextUtils.isEmpty(action_start)
        	        || TextUtils.isEmpty(action_end)
        	        || TextUtils.isEmpty(descrip)
        	        || TextUtils.isEmpty(adress)) {
        		  Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
        	      return;
        	    }    
        	else {
    		    Intent intent = new Intent(this, Template.class);
    		    intent.putExtra("category", category);
    		    intent.putExtra("nameAction", nameAction);
    		    intent.putExtra("action_start", action_start);
    		    intent.putExtra("action_end", action_end);
    		    intent.putExtra("descrip", descrip);
    		    intent.putExtra("login", login);
    		    intent.putExtra("adress", adress);
    		    startActivity(intent);
        	}

	    }

}
