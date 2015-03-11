package com.louisapps.action;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.local.UserTokenStorageFactory;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class OpenView extends Activity {
	
	Boolean existence = false;
	String objectId;
	ImageView imgAction;
	ImageView logoOpen;
	ImageView imgAdd;
	TextView textAcComp;
	TextView textAcName;
	TextView textAcAddress;
	TextView textAcDate;
	TextView textAcDescrip;
	ActionCreation action;
	private String userToken;
	DisplayImageOptions doption=null;
	ProgressDialog progressDialog;
    private ImageLoadingListener animateFirstListener =null;
    String YOUR_APP_ID = "149BBA0B-F8B9-CD77-FF47-C9C80D8F8500";
    String url = "https://api.backendless.com/" + YOUR_APP_ID + "/v1/files/logo/logo2.png";
	Integer[] userActions = {
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
	        setContentView(R.layout.open_view);
	        userToken = UserTokenStorageFactory.instance().getStorage().get();
	        Intent intent = getIntent();
	        objectId = intent.getStringExtra("objectId");
	        
	        imgAction = (ImageView) findViewById(R.id.imgAction);
	        imgAdd = (ImageView) findViewById(R.id.imgAdd);
	        logoOpen = (ImageView) findViewById(R.id.logoOpen);
	        textAcComp = (TextView) findViewById(R.id.textAcComp);
	        textAcName = (TextView) findViewById(R.id.textAcName);
	        textAcAddress = (TextView) findViewById(R.id.textAcAddress);
	        textAcDate = (TextView) findViewById(R.id.textAcDate);
	        textAcDescrip = (TextView) findViewById(R.id.textAcDescrip);
	        imgAdd.setImageResource(R.drawable.ic_action_not_important);
	        doption=new DisplayImageOptions.Builder().cacheInMemory(true).cacheOnDisc(true).displayer(new RoundedBitmapDisplayer(20)).build();
	        animateFirstListener = new AnimateFirstDisplayListener();
	        progressDialog = ProgressDialog.show( this, "", "Loading", true );
	        
	        getTasks(objectId, new BackendlessCallback<BackendlessCollection<ActionCreation>>() {

				@Override
				public void handleResponse(
						BackendlessCollection<ActionCreation> taskBackendlessCollection) {

					action = taskBackendlessCollection.getCurrentPage().get(0);
					int number = action.getNumberImage();					
					String nameAction = action.getNameAction();
					String address = action.getAdress();
					String date = action.getAction_start() + " - " + action.getAction_end();
					String description = action.getDescrip();
					String nameCompany = action.getNameCompany();
					
					ImageLoader imageLoader = ImageLoader.getInstance();
					imageLoader.init(ImageLoaderConfiguration.createDefault(OpenView.this));
			        imageLoader.displayImage(action.getLogo(), logoOpen);

					textAcComp.setText(nameCompany);
					imgAction.setImageResource(userActions[number]);	
					textAcName.setText(nameAction);
					textAcName.setTextColor(Color.parseColor(actionColors[number]));
					textAcAddress.setText(address);
					textAcAddress.setTextColor(Color.parseColor(actionColors[number]));
					textAcDate.setText(date);
					textAcDate.setTextColor(Color.parseColor(actionColors[number]));
					textAcDescrip.setText(description);	
					textAcDescrip.setTextColor(Color.parseColor(actionColors[number]));
					progressDialog.cancel();
					
				}
				
	      	    @Override
	      	    public void handleFault( BackendlessFault backendlessFault )
	      	    {
	      	        Toast.makeText( OpenView.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
	      	      progressDialog.cancel();
	      	    }
	        	
	        });
	        
	        getSubs (new BackendlessCallback<BackendlessCollection<UserFavourites>>() {
	        	
	        	  @Override
			      public void handleResponse( BackendlessCollection<UserFavourites> taskBackendlessCollection )
			      {
	        		 for (UserFavourites task: taskBackendlessCollection.getCurrentPage()){
		        		 if (task.getFavouriteAction().getObjectId().equalsIgnoreCase(objectId)) {
		    			     imgAdd.setImageResource(R.drawable.ic_action_important);
		    			     existence = true;
		        		 }
	        		 }       		 
			      }
	
	        	  @Override
			      public void handleFault( BackendlessFault fault )
			      {
			    	  imgAdd.setImageResource(R.drawable.ic_action_not_important);
			    	  existence = false;
			      }
	        });
	        
	  }
	  
	  public void onClickAdd (View v) {
		  if( userToken != null && !userToken.equals( "" ) ){
			  if (!existence) {
				  UserFavourites subscription = new UserFavourites();
				  subscription.setFavouriteAction(action);
				  
				  Backendless.Persistence.save( subscription, new AsyncCallback<UserFavourites>() {
					    public void handleResponse( UserFavourites response )
					    {
					    	imgAdd.setImageResource(R.drawable.ic_action_important);
					    	Toast.makeText( OpenView.this, "Акция добавлена в Подписки", Toast.LENGTH_LONG ).show();
					    }
					 
					     public void handleFault( BackendlessFault backendlessFault )
					    {
					    	 Toast.makeText( OpenView.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
					    }
					  });
			  } else {
				  Toast.makeText( OpenView.this, "Акция уже добавлена", Toast.LENGTH_LONG ).show();
			  }
		  } else {
			  Toast.makeText( OpenView.this, "Авторизуйтесь, чтобы добавить акцию в Подписки", Toast.LENGTH_LONG ).show();
		  }
	  }
	  
	    public static void getTasks( final String ID, final AsyncCallback<BackendlessCollection<ActionCreation>> responder )
		  {
			String whereClause = "objectId = '" + ID + "'";
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
	    
	    private static class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
	    	 
	        static final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
	 
	        @Override
	        public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
	            if (loadedImage != null) {
	                ImageView imageView = (ImageView) view;
	                boolean firstDisplay = !displayedImages.contains(imageUri);
	                if (firstDisplay) {
	                    FadeInBitmapDisplayer.animate(imageView, 500);
	                    displayedImages.add(imageUri);
	                }
	            }
	        }
	    }
}
