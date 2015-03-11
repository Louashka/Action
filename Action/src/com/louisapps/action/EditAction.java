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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.louisapps.action.fragments.EditDatePicker;
import com.louisapps.action.fragments.EditDatePicker1;

public class EditAction extends ActionBarActivity implements OnItemSelectedListener {
	
	private Spinner edit_spinner;
	private static String[] categNames;
	String objectId;
	EditText edit_name_of_action;
	EditText edit_description;
	EditText edit_actionStart;
	EditText edit_actionEnd;
	EditText edit_acAddress;
	Button edit_continue_action;
	String edit_category;
	String edit_nameAction;
	String edit_action_start;
	String edit_action_end;
	String edit_descrip;
	String edit_login;
	String edit_address;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.edit_action);
	        
	        Intent intent = getIntent();
	        objectId = intent.getStringExtra("objectId");
	        
	        edit_spinner = (Spinner) findViewById(R.id.edit_categories);
	        edit_spinner.setOnItemSelectedListener(this);
	     
	     edit_name_of_action = (EditText) findViewById(R.id.edit_name_of_action);
	     edit_description = (EditText) findViewById(R.id.edit_description);
	     edit_actionStart = (EditText) findViewById(R.id.edit_actionStart);
	     edit_actionEnd = (EditText) findViewById(R.id.edit_actionEnd);
	     edit_acAddress = (EditText) findViewById(R.id.edit_acAddress);
	     edit_continue_action = (Button) findViewById(R.id.edit_continue_action);
	     categNames = getResources().getStringArray(R.array.views_array);
	     	     
	     getTasks(objectId, new BackendlessCallback<BackendlessCollection<ActionCreation>>() {

			@Override
			public void handleResponse(BackendlessCollection<ActionCreation> taskBackendlessCollection) {
				for( ActionCreation task : taskBackendlessCollection.getCurrentPage()){
					edit_name_of_action.setText(task.getNameAction());
					edit_description.setText(task.getDescrip());
					edit_actionStart.setText(task.getAction_start());
					edit_actionEnd.setText(task.getAction_end());
					edit_acAddress.setText(task.getAdress());
				     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EditAction.this,
				             R.array.views_array, R.layout.spinner_row);
				     // Specify the layout to use when the list of choices appears
				     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				     // Apply the adapter to the spinner
				     edit_spinner.setAdapter(adapter);
				     for (int i = 0; i < categNames.length; i++) {
				    	 if (categNames[i].equals(task.getCategory()))
				    		 edit_spinner.setSelection(i);
				     }
				}
			}
			
     	    @Override
      	    public void handleFault( BackendlessFault backendlessFault )
      	    {
      	        Toast.makeText( EditAction.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
      	    }
	    	 
	     });
	     
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

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		String[] choose = getResources().getStringArray(R.array.views_array);
		edit_category = choose[position];		
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}
	
    public void onClick(View v) {
        DialogFragment dateDialog = new EditDatePicker();
        dateDialog.show(getSupportFragmentManager(), "datePicker");
    }
    
    public void onClick1(View v) {
        DialogFragment dateDialog1 = new EditDatePicker1();
        dateDialog1.show(getSupportFragmentManager(), "datePicker");
    }
    
    public void onClickEdit (View v) {
    	edit_nameAction = edit_name_of_action.getText().toString();
    	edit_action_start = edit_actionStart.getText().toString();
    	edit_action_end = edit_actionEnd.getText().toString();
    	edit_descrip = edit_description.getText().toString();
    	edit_address = edit_acAddress.getText().toString();
        
    	if (TextUtils.isEmpty(edit_nameAction)
    	        || TextUtils.isEmpty(edit_action_start)
    	        || TextUtils.isEmpty(edit_action_end)
    	        || TextUtils.isEmpty(edit_descrip)
    	        || TextUtils.isEmpty(edit_address)) {
    		  Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
    	      return;
    	    }    
    	else {
		    Intent intent = new Intent(this, EditTemplate.class);
		    intent.putExtra("category", edit_category);
		    intent.putExtra("nameAction", edit_nameAction);
		    intent.putExtra("action_start", edit_action_start);
		    intent.putExtra("action_end", edit_action_end);
		    intent.putExtra("descrip", edit_descrip);
		    intent.putExtra("login", edit_login);
		    intent.putExtra("adress", edit_address);
		    intent.putExtra("objectId", objectId);
		    startActivity(intent);
    	}

    }
    
    public static void getTasks( final String MyID, final AsyncCallback<BackendlessCollection<ActionCreation>> responder )
	  {
		String whereClause = "objectId = '" + MyID + "'";
		BackendlessDataQuery dataQuery = new BackendlessDataQuery();
		dataQuery.setWhereClause( whereClause );
	    Backendless.Persistence.of( ActionCreation.class ).find( dataQuery, new BackendlessCallback<BackendlessCollection<ActionCreation>>()
	    {
	      @Override
	      public void handleResponse( BackendlessCollection<ActionCreation> tasksBackendlessCollection )
	      {
	        responder.handleResponse( tasksBackendlessCollection );
	      }

	      public void handleFault( BackendlessFault fault )
	      {
	        responder.handleFault( fault );
	      }
	    } );
	  }	
    
}
