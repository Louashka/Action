package com.louisapps.action;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
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

public class Subscription extends Activity {
	
	ProgressDialog progressDialog;
	private GridView userSubscrip;
	ArrayList<CategoriesItems> userActionsList = new ArrayList<CategoriesItems>();
	ArrayList<String> actionsObjectID = new ArrayList<String>();
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subscription);
        setTitle("Подписки");
        
        progressDialog = ProgressDialog.show( Subscription.this, "", "Loading", true );
        getSubs( new BackendlessCallback<BackendlessCollection<UserFavourites>>()
        	    {
        	      @Override
        	      public void handleResponse( BackendlessCollection<UserFavourites> taskBackendlessCollection )
        	      {
        	    	  for( UserFavourites task : taskBackendlessCollection.getCurrentPage()){
        	    		  userActionsList.add(new CategoriesItems (task.getFavouriteAction().getNumberImage(), task.getFavouriteAction().getNameCompany(),
    		    				  "c " + task.getFavouriteAction().getAction_start() + "\nпо " + task.getFavouriteAction().getAction_end(), task.getFavouriteAction().getNameAction(), task.getFavouriteAction().getLogo()));
        	    		  actionsObjectID.add(task.getObjectId());
        	    	  }
        	    	  if (!userActionsList.isEmpty()){
        	    		userSubscrip = (GridView) findViewById( R.id.userSubscrip );
        	    		userSubscrip.setAdapter( new ItemHorizontalListAdapter(Subscription.this, userActionsList) );
        	    		userSubscrip.setOnItemClickListener(new OnItemClickListener() {

	        				@Override
	        				public void onItemClick(AdapterView<?> parent, View view,
	        						int position, long id) {
	        		        	Intent intent = new Intent(Subscription.this, OpenViewInSub.class);
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
        	        Toast.makeText( Subscription.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
        	      }
        	    } );
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
	 
	    public static void getSubs(final AsyncCallback<BackendlessCollection<UserFavourites>> responder )
		  {
			String whereClause = "ownerId = '" + Backendless.UserService.loggedInUser() + "'";
			BackendlessDataQuery dataQuery = new BackendlessDataQuery();
			dataQuery.setWhereClause( whereClause );
		    Backendless.Persistence.of( UserFavourites.class ).find( dataQuery, new BackendlessCallback<BackendlessCollection<UserFavourites>>()
		    {
		      @Override
		      public void handleResponse( BackendlessCollection<UserFavourites> tasksBackendlessCollection )
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
