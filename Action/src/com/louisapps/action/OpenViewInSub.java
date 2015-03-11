package com.louisapps.action;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class OpenViewInSub extends Activity {

	final int DIALOG_DELETE = 1;
	DataBase db;
	String my_objectId;
	ImageView imgMyAction;
	ImageView logoOpInPo;
	TextView textMyAcName;
	TextView textMyAcComp;
	TextView textMyAcAddress;
	TextView textMyAcDate;
	TextView textMyAcDescrip;
	Integer[] myActions = {
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
	private String[] myActionColors = {
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
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.open_view_in_po);
	        
	        db = new DataBase(this);
	        db.open();
	        
	        Intent intent = getIntent();
	        my_objectId = intent.getStringExtra("objectId");
	        
	        imgMyAction = (ImageView) findViewById(R.id.imgMyAction);
	        logoOpInPo = (ImageView) findViewById(R.id.logoOpInPo);
	        textMyAcName = (TextView) findViewById(R.id.textMyAcName);
	        textMyAcComp = (TextView) findViewById(R.id.textMyAcComp);
	        textMyAcAddress = (TextView) findViewById(R.id.textMyAcAddress);
	        textMyAcDate = (TextView) findViewById(R.id.textMyAcDate);
	        textMyAcDescrip = (TextView) findViewById(R.id.textMyAcDescrip);
	        
	        getSubs(my_objectId, new BackendlessCallback<BackendlessCollection<UserFavourites>>() {

				@Override
				public void handleResponse(
						BackendlessCollection<UserFavourites> taskBackendlessCollection) {
					int number = 1;
					String nameCompany = null;
					String nameAction = null;
					String address = null;
					String date = null;
					String description = null;
					String url = null;
					for( UserFavourites task : taskBackendlessCollection.getCurrentPage()) {					
				        number = task.getFavouriteAction().getNumberImage();
				        nameCompany = task.getFavouriteAction().getNameCompany();
				        nameAction = task.getFavouriteAction().getNameAction();
				        address = task.getFavouriteAction().getAdress();
				        date = task.getFavouriteAction().getAction_start() + " - " + task.getFavouriteAction().getAction_end();
				        description = task.getFavouriteAction().getDescrip();
				        url = task.getFavouriteAction().getLogo();
					}
					
					ImageLoader imageLoader = ImageLoader.getInstance();
					imageLoader.init(ImageLoaderConfiguration.createDefault(OpenViewInSub.this));
			        imageLoader.displayImage(url, logoOpInPo);
			        
					imgMyAction.setImageResource(myActions[number]);	
					textMyAcComp.setText(nameCompany);
					textMyAcName.setText(nameAction);
					textMyAcName.setTextColor(Color.parseColor(myActionColors[number]));
					textMyAcAddress.setText(address);
					textMyAcAddress.setTextColor(Color.parseColor(myActionColors[number]));
					textMyAcDate.setText(date);
					textMyAcDate.setTextColor(Color.parseColor(myActionColors[number]));
					textMyAcDescrip.setText(description);
					textMyAcDescrip.setTextColor(Color.parseColor(myActionColors[number]));
				}
				
	      	    @Override
	      	    public void handleFault( BackendlessFault backendlessFault )
	      	    {
	      	        Toast.makeText( OpenViewInSub.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
	      	    }
	        	
	        });
	 }
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	      getMenuInflater().inflate(R.menu.favourites, menu);
	      return true;
	    }
	    
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	        // Respond to the action bar's Up/Home button
	            case android.R.id.home:
	                NavUtils.navigateUpFromSameTask(this);
	                return true;
	            case R.id.delete_sub:
	            	AlertDialog dialog = onCreateDialog(DIALOG_DELETE);
	                dialog.show();
	                return true;
	        }
	        return super.onOptionsItemSelected(item);
	    }
	    
	    public static void getSubs(final String MyID, final AsyncCallback<BackendlessCollection<UserFavourites>> responder )
		  {
	    	String whereClause = "objectId = '" + MyID + "'";
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
	    
		protected AlertDialog onCreateDialog(int id) {
	    	
	    	if (id == DIALOG_DELETE) {
	    		AlertDialog.Builder adb = new AlertDialog.Builder(this);
		        adb.setTitle(R.string.delete_favourites_title); 
		        adb.setMessage(R.string.delete_favourites);
		        adb.setCancelable(true);
		        adb.setPositiveButton(R.string.yes, myClickListener);
		        adb.setNegativeButton(R.string.no, myClickListener);
		        return adb.create();
	    	} else {
	    		return null;
	    	}
	    }	    
	    OnClickListener myClickListener = new OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) {
	          switch (which) {
	          // положительная кнопка
	          case Dialog.BUTTON_POSITIVE:   		  								
	        	    getSubs(my_objectId, new BackendlessCallback<BackendlessCollection<UserFavourites>>() {

						@Override
						public void handleResponse(BackendlessCollection<UserFavourites> taskBackendlessCollection) {
						    for( final UserFavourites task : taskBackendlessCollection.getCurrentPage()) {
				            	Backendless.Persistence.of( UserFavourites.class ).remove( task, new AsyncCallback<Long>(){

									@Override
									public void handleFault(BackendlessFault arg0) {
										Toast.makeText( OpenViewInSub.this, arg0.getMessage(), Toast.LENGTH_LONG ).show();
									}

									@Override
									public void handleResponse(Long arg0) {
										Toast.makeText( OpenViewInSub.this, "Акция успешно удалена", Toast.LENGTH_LONG ).show();		
										Intent intent = new Intent(OpenViewInSub.this, Subscription.class);
			        		        	startActivity(intent);
									}				            		
				            	});
							}
						}
						
			      	    @Override
			      	    public void handleFault( BackendlessFault backendlessFault )
			      	    {
			      	        Toast.makeText( OpenViewInSub.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
			      	    }
	            	});

	        	dialog.dismiss();
	            break;
	          // негаитвная кнопка
	          case Dialog.BUTTON_NEGATIVE:
	        	dialog.dismiss();
	            break;
	          }
	        }
	      };

}
