package com.louisapps.action.fragments;

import java.util.ArrayList;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.louisapps.action.ActionCreation;
import com.louisapps.action.AllCategoriesItems;
import com.louisapps.action.R;
import com.louisapps.action.adapters.ItemListUserActionsAdapter;

public class Disks extends Fragment {
	
	ProgressDialog progressDialog;
	private ListView listView;
	ArrayList<AllCategoriesItems> userActionsList = new ArrayList<AllCategoriesItems>();

    public Disks() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.disks, container,
                false);
        
        progressDialog = ProgressDialog.show( getActivity(), "", "Loading", true );
        listView = (ListView) rootView.findViewById( R.id.disks );
        
        getDisksTasks( new BackendlessCallback<BackendlessCollection<ActionCreation>>()
        	    {
        	      @Override
        	      public void handleResponse( BackendlessCollection<ActionCreation> taskBackendlessCollection )
        	      {
        	    	  for( ActionCreation task : taskBackendlessCollection.getCurrentPage())
        	    		  userActionsList.add(new AllCategoriesItems (task.getNumberImage(), task.getNameAction()
        	    				  + "\n" + task.getNameCompany() + "\nñ " + task.getAction_start() 
        	    				  + "\nïî " + task.getAction_end() + "\n" + task.getAdress() + "\n" + task.getDescrip()));       	    	
        	        listView.setAdapter( new ItemListUserActionsAdapter(getActivity(), userActionsList) );
        	        progressDialog.cancel();
        	      }
        	      
        	      @Override
        	      public void handleFault( BackendlessFault backendlessFault )
        	      {
        	        progressDialog.cancel();
        	        Toast.makeText( getActivity(), backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
        	      }
        	    } );

        return rootView;
    }
    
	public static void getDisksTasks( final AsyncCallback<BackendlessCollection<ActionCreation>> responder )
	  {
		String whereClause = "category = 'CD, DVD, Blue-Ray'";
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

