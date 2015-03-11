package com.louisapps.action;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
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

public class EditPreview extends Activity implements OnClickListener {
	
	TextView textActionName;
	TextView textActionAddress;
	TextView textActionDate;
	TextView textActionDescrip;
	Button btnOk;
	Button btnCancel;
	ImageView templ_image;
	ImageView logoPrev;
	String edit_category;
	String edit_nameAction;
	String edit_action_start;
	String edit_action_end;
	String edit_descrip;
	String edit_number;
	String edit_nameCompany;
	String login;
	String edit_adress;
	String objectId;
	String userId;
	String url;
	ImageLoader imageLoader;
	int edit_numberImage;
	Integer[] userActionImages = {
		    R.drawable.image1,
		    R.drawable.image2,
		    R.drawable.image3,
		    R.drawable.image4,
		    R.drawable.image5,
		    R.drawable.image6,
		    R.drawable.image7,
		    R.drawable.image8,
		    R.drawable.image9,
		    R.drawable.image10,
		    R.drawable.image11	    
		  };
	private String[] actionColors = {
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
	
	  @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.preview);
	        setTitle ("Предварительный просмотр");
	        
	        imageLoader = ImageLoader.getInstance();
			imageLoader.init(ImageLoaderConfiguration.createDefault(this));
			logoPrev = (ImageView) findViewById(R.id.logoPrev);
			
	        userId = Backendless.UserService.loggedInUser();	        
	        getProperty (userId, new BackendlessCallback<BackendlessCollection<Users>>() {

				@Override
				public void handleResponse(BackendlessCollection<Users> arg) {
					for (Users task: arg.getCurrentPage()){
						login = task.getLogin();	
						url = task.getPhoto();
						edit_nameCompany = task.getCompanyName();
				        imageLoader.displayImage(url, logoPrev);	
					}					
				} 
				
				@Override
	    	    public void handleFault( BackendlessFault backendlessFault )
	    	    {
	    	        Toast.makeText( EditPreview.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
	    	    }
	    		
        	});
	        
	        Intent intent = getIntent();
	        edit_category = intent.getStringExtra("category");
	        edit_nameAction = intent.getStringExtra("nameAction");
	        edit_action_start = intent.getStringExtra("action_start");
	        edit_action_end = intent.getStringExtra("action_end");
	        edit_descrip = intent.getStringExtra("descrip");
	        edit_number = intent.getStringExtra("number");
	        edit_adress = intent.getStringExtra("adress");
	        objectId = intent.getStringExtra("objectId");
	    	
	        edit_numberImage = Integer.parseInt(edit_number);;
	    	
	    	templ_image = (ImageView) findViewById(R.id.templ_image);
	    	textActionName = (TextView) findViewById(R.id.textActionName);
	    	textActionAddress = (TextView) findViewById(R.id.textActionAddress);
	    	textActionDate = (TextView) findViewById(R.id.textActionDate);
	    	textActionDescrip = (TextView) findViewById(R.id.textActionDescrip);
	    	btnOk = (Button) findViewById(R.id.ok_action);
	        btnCancel = (Button) findViewById(R.id.cancel);
	        
	        btnOk.setOnClickListener(this);
	        btnCancel.setOnClickListener(this);
	    	
	        switch (edit_numberImage){
	    	case 0:
	    		createPreview (userActionImages[0], actionColors[0]);
	    		break;
	    	case 1:
	    		createPreview (userActionImages[1], actionColors[1]);
	    		break;
	    	case 2:
	    		createPreview (userActionImages[2], actionColors[2]);
	    		break;
	    	case 3:
	    		createPreview (userActionImages[3], actionColors[3]);
	    		break;
	    	case 4:
	    		createPreview (userActionImages[4], actionColors[4]);
	    		break;
	    	case 5:
	    		createPreview (userActionImages[5], actionColors[5]);
	    		break;
	    	case 6:
	    		createPreview (userActionImages[6], actionColors[6]);
	    		break;
	    	case 7:
	    		createPreview (userActionImages[7], actionColors[7]);
	    		break;
	    	case 8:
	    		createPreview (userActionImages[8], actionColors[8]);
	    		break;
	    	case 9:
	    		createPreview (userActionImages[9], actionColors[9]);
	    		break;
	    	case 10:
	    		createPreview (userActionImages[10], actionColors[10]);
	    		break;
	    	default:
	    		break;

	    	}
	    				
	  }
	  
	  public void createPreview (Integer img, String color) {
		  textActionName.setText(edit_nameAction);
		  textActionName.setTextColor(Color.parseColor(color));
		  textActionAddress.setText(edit_adress);
		  textActionAddress.setTextColor(Color.parseColor(color));
		  textActionDate.setText(edit_action_start + " - " + edit_action_end);
		  textActionDate.setTextColor(Color.parseColor(color));
		  textActionDescrip.setText(edit_descrip);
		  textActionDescrip.setTextColor(Color.parseColor(color));
		  templ_image.setImageResource(img);
	  }

	@Override
	public void onClick(View v) {
	     switch (v.getId()) {
	     case R.id.ok_action:

	    	 getTasks (objectId, new BackendlessCallback<BackendlessCollection<ActionCreation>>() {

				@Override
				public void handleResponse(BackendlessCollection<ActionCreation> taskBackendlessCollection) {
					ActionCreation actionCreation = taskBackendlessCollection.getCurrentPage().get(0);
			    	actionCreation.setCategory( edit_category);
			    	actionCreation.setNameAction( edit_nameAction );
			    	actionCreation.setAction_start( edit_action_start );
			    	actionCreation.setAction_end( edit_action_end );
			    	actionCreation.setDescrip( edit_descrip);
			    	actionCreation.setNumberImage( edit_numberImage );
			    	actionCreation.setAdress(edit_adress);
			    	actionCreation.setNameCompany(edit_nameCompany);
			    	actionCreation.setLogin(login);
			    	
			    	Backendless.Persistence.save( actionCreation, new AsyncCallback<ActionCreation>() {
			    		 
			    		 @Override
			    	    public void handleResponse( ActionCreation response )
			    	    {
			    			 Toast.makeText(EditPreview.this, "Акция успешно обновлена", Toast.LENGTH_LONG).show();
			    			 startActivity( new Intent(getBaseContext(), PrivateOffice.class) );
			    	    }

						@Override
						public void handleFault(BackendlessFault arg0) {
							Toast.makeText(EditPreview.this, "Ошибка обновления акции! Повторите попытку", Toast.LENGTH_LONG).show();
							
						}
			    	  });
				}
				
	      	    @Override
	      	    public void handleFault( BackendlessFault backendlessFault )
	      	    {
	      	        Toast.makeText( EditPreview.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
	      	    }
	        	
	        });
	    	 
//	    	// save object asynchronously
//	    	 Backendless.Persistence.save( actionCreation, new AsyncCallback<ActionCreation>() {
//	    		 
//	    		 @Override
//	    	    public void handleResponse( ActionCreation response )
//	    	    {
//	    			 startActivity( new Intent(getBaseContext(), PrivateOffice.class) );
//	    	    }
//
//				@Override
//				public void handleFault(BackendlessFault arg0) {
//					Toast.makeText(EditPreview.this, "Ошибка создания акции! Повторите попытку", Toast.LENGTH_LONG).show();
//					
//				}
//	    	  });
	       break;
	     case R.id.cancel:
	    	 
	    	 startActivity( new Intent(getBaseContext(), PrivateOffice.class) );
	    	 
	       break;
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
    
    public static void getProperty( final String objectId, final AsyncCallback<BackendlessCollection<Users>> responder )
	  {
		String whereClause = "objectId = '" + objectId + "'";
		BackendlessDataQuery dataQuery = new BackendlessDataQuery();
		dataQuery.setWhereClause( whereClause );
	    Backendless.Persistence.of( Users.class ).find( dataQuery, new BackendlessCallback<BackendlessCollection<Users>>()
	    {
	      @Override
	      public void handleResponse( BackendlessCollection<Users> tasksBackendlessCollection )
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

