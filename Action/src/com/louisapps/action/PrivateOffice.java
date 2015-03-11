package com.louisapps.action;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.louisapps.action.adapters.ItemHorizontalListAdapter;

public class PrivateOffice extends Activity {

	String objectId;
	String ownerId;
	ProgressDialog progressDialog;
	private GridView gridView;
	ArrayList<CategoriesItems> userActionsList = new ArrayList<CategoriesItems>();
	ArrayList<String> actionsObjectID = new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.private_office);
	        	        
	        setTitle("Личный кабинет");
	        getActionBar().setDisplayHomeAsUpEnabled(true);

	        progressDialog = ProgressDialog.show( PrivateOffice.this, "", "Loading", true );
	        getUserTasks( new BackendlessCallback<BackendlessCollection<ActionCreation>>()
	        	    {
	        	      @Override
	        	      public void handleResponse( BackendlessCollection<ActionCreation> taskBackendlessCollection )
	        	      {
	        	    	  for( ActionCreation task : taskBackendlessCollection.getCurrentPage()){
	        	    		  userActionsList.add(new CategoriesItems (task.getNumberImage(), task.getNameCompany(),
	    		    				  "c " + task.getAction_start() + "\nпо " + task.getAction_end(), task.getNameAction(), task.getLogo()));
	        	    		  actionsObjectID.add(task.getObjectId());
	        	    	  }
	        	    	  if (!userActionsList.isEmpty()){
	              	        gridView = (GridView) findViewById( R.id.userActions );
		        	        gridView.setAdapter( new ItemHorizontalListAdapter(PrivateOffice.this, userActionsList) );
		        	        gridView.setOnItemClickListener(new OnItemClickListener() {

		        				@Override
		        				public void onItemClick(AdapterView<?> parent, View view,
		        						int position, long id) {
		        		        	Intent intent = new Intent(PrivateOffice.this, OpenViewInPO.class);
		        		        	intent.putExtra("objectId", actionsObjectID.get(position));
		        		        	startActivity(intent);
		        				}
		        	        	
		        	        }); 
	        	    	  }
	        	        progressDialog.cancel();
	        	      }
	        	      
	        	     
	        	      @Override
	        	      public void handleFault( BackendlessFault backendlessFault )
	        	      {
	        	        progressDialog.cancel();
	        	        Toast.makeText( PrivateOffice.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
	        	      }
	        	    } );
	        }
	
    protected void onDestroy() {
        super.onDestroy();
      }

      protected void onPause() {
        super.onPause();
      }

      protected void onRestart() {
        super.onRestart();
      }

      protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
      }

      protected void onResume() {
        super.onResume();
      }

      protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
      }

      protected void onStart() {
        super.onStart();
      }

      protected void onStop() {
        super.onStop();
      }
	
	public static void getUserTasks( final AsyncCallback<BackendlessCollection<ActionCreation>> responder )
	  {
		String whereClause = "ownerId = '" + Backendless.UserService.loggedInUser() + "'";
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

		@Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	      getMenuInflater().inflate(R.menu.actions, menu);
	      return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        // Respond to the action bar's Up/Home button
	            case android.R.id.home:
	                NavUtils.navigateUpFromSameTask(this);
	                return true;
	            case R.id.add_action:
	            	Intent intent = new Intent(this, Create_action.class);
	                startActivity(intent);
	                break;
	            case R.id.favour:
	            	Intent intent1 = new Intent(this, Subscription.class);
	                startActivity(intent1);
	                break;          	   
	        }
	        return super.onOptionsItemSelected(item);
	    }

}
