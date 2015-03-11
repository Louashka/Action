package com.louisapps.action;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
import com.louisapps.action.util.IabHelper;
import com.louisapps.action.util.IabResult;
import com.louisapps.action.util.Inventory;
import com.louisapps.action.util.Purchase;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

public class Preview extends Activity implements OnClickListener {
	
	TextView textActionName;
	TextView textActionAddress;
	TextView textActionDate;
	TextView textActionDescrip;
	Button btnOk;
	Button btnCancel;
	ImageView templ_image;
	ImageView logoPrev;
	String category;
	String nameCompany;
	String nameAction;
	String action_start;
	String action_end;
	String descrip;
	String number;
	String login;
	String adress;
	String url;
	ImageLoader imageLoader;
	private String userId;
	int numberImage;
	boolean instance;
	static final String SKU_GAS0 = "com.louisapps.template_0";
	static final String SKU_GAS1 = "com.louisapps.template_1";
	static final String SKU_GAS2 = "com.louisapps.template_2";
	static final String SKU_GAS3 = "com.louisapps.template_3";
	static final String SKU_GAS4 = "com.louisapps.template_4";
	static final String SKU_GAS5 = "com.louisapps.template_5";
	static final String SKU_GAS6 = "com.louisapps.template_6";
	IabHelper mHelper;
    String base64EncodedPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkWGcCW1hKx4yfMO+SUTKg13NuvEvqasyEaPEdrspcQ54mZhMghymKkLB5sOzHwvb6WFKruu54TUZtZ6PojTs+vzr9e7z6TbIG4PdhvukGLWdfW7DAvAJPFBgQDOixwy4aJlZlpQ9f3Qp9nNJ31S/J8aaK3z2I5JPwf03sfkILrP6e6AXJtBjI2t40MLSmsT0dPOLyP8pJualRZ9CLFwbagJS+dZAGer2JGZWh6CP5mdgi6RBB49HYA4RIf2VEeiLtAf51EKOvPuWDYWOA/P5824AjMCkj2CMG1gefZXIJzbaTrxcEm7760DBO5Dk1n3N7VJY+HJ1DdP2hmQX+4hL5wIDAQAB";
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
	        
	        mHelper = new IabHelper(this, base64EncodedPublicKey);
	        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
	        	   public void onIabSetupFinished(IabResult result) {
	        	      if (!result.isSuccess()) {
	        	    	  Toast.makeText(Preview.this, "Ошибка платежной системы", Toast.LENGTH_LONG).show();
	        	      }
	        	   }
	        	});
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
						nameCompany = task.getCompanyName();
				        imageLoader.displayImage(url, logoPrev);						
					}					
				} 
				
				@Override
	    	    public void handleFault( BackendlessFault backendlessFault )
	    	    {
	    	        Toast.makeText( Preview.this, backendlessFault.getMessage(), Toast.LENGTH_LONG ).show();
	    	    }
	    		
        	});
	        
	        Intent intent = getIntent();
	        category = intent.getStringExtra("category");
	    	nameAction = intent.getStringExtra("nameAction");
	    	action_start = intent.getStringExtra("action_start");
	    	action_end = intent.getStringExtra("action_end");
	    	descrip = intent.getStringExtra("descrip");
	    	number = intent.getStringExtra("number");
	    	adress = intent.getStringExtra("adress");
	    	
	    	numberImage = Integer.parseInt(number);;
	    	
	    	templ_image = (ImageView) findViewById(R.id.templ_image);
	    	textActionName = (TextView) findViewById(R.id.textActionName);
	    	textActionAddress = (TextView) findViewById(R.id.textActionAddress);
	    	textActionDate = (TextView) findViewById(R.id.textActionDate);
	    	textActionDescrip = (TextView) findViewById(R.id.textActionDescrip);
	    	btnOk = (Button) findViewById(R.id.ok_action);
	        btnCancel = (Button) findViewById(R.id.cancel);
	        
	        btnOk.setOnClickListener(this);
	        btnCancel.setOnClickListener(this);
	    	
	    	switch (numberImage){
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
		  textActionName.setText(nameAction);
		  textActionName.setTextColor(Color.parseColor(color));
		  textActionAddress.setText(adress);
		  textActionAddress.setTextColor(Color.parseColor(color));
		  textActionDate.setText(action_start + " - " + action_end);
		  textActionDate.setTextColor(Color.parseColor(color));
		  textActionDescrip.setText(descrip);
		  textActionDescrip.setTextColor(Color.parseColor(color));
		  templ_image.setImageResource(img);
	  }
	  
	  @Override
	  protected void onActivityResult(int requestCode, int resultCode, 
	       Intent data) 
	  {
	        if (!mHelper.handleActivityResult(requestCode, 
	                resultCode, data)) {     
	      	super.onActivityResult(requestCode, resultCode, data);
	        }
	  }
	    
	    @Override
	    public void onDestroy() {
	       super.onDestroy();
	       if (mHelper != null) mHelper.dispose();
	       mHelper = null;
	    } 

	@Override
	public void onClick(View v) {
	     switch (v.getId()) {
	     case R.id.ok_action:
	    	 if (numberImage == 4){
	    		 mHelper.launchPurchaseFlow(this, SKU_GAS0, 10001,   
	       			   mPurchaseFinishedListener, "mypurchasetoken");	 
	    	 } else if (numberImage == 5){
	    		        mHelper.launchPurchaseFlow(this, SKU_GAS1, 10001,   
		       			       mPurchaseFinishedListener, "mypurchasetoken");	 
		    	 } else if (numberImage == 6){
	    		        mHelper.launchPurchaseFlow(this, SKU_GAS2, 10001,   
			       			       mPurchaseFinishedListener, "mypurchasetoken");	 
			    	 } else if (numberImage == 7){
		    		        mHelper.launchPurchaseFlow(this, SKU_GAS3, 10001,   
				       			       mPurchaseFinishedListener, "mypurchasetoken");	 
				    	 } else if (numberImage == 8){
			    		        mHelper.launchPurchaseFlow(this, SKU_GAS4, 10001,   
					       			       mPurchaseFinishedListener, "mypurchasetoken");	 
					    	 } else if (numberImage == 9){
				    		        mHelper.launchPurchaseFlow(this, SKU_GAS5, 10001,   
						       			       mPurchaseFinishedListener, "mypurchasetoken");	 
						    	 } else if (numberImage == 10){
					    		        mHelper.launchPurchaseFlow(this, SKU_GAS6, 10001,   
							       			       mPurchaseFinishedListener, "mypurchasetoken");	 
							    	 } else {
	    		 launchAction();
	    	 }
	    	 
	       break;
	     case R.id.cancel:
	    	 
	    	 startActivity( new Intent(getBaseContext(), PrivateOffice.class) );
	    	 
	       break;
	     }
		
	}
	
    
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener 
	= new IabHelper.OnIabPurchaseFinishedListener() {
	    public void onIabPurchaseFinished(IabResult result, 
                        Purchase purchase) {
	       if (result.isFailure()) {	    	   
	           return;
	       }      
	       else if (purchase.getSku().equals(SKU_GAS0)) {
		        consumeItem();
	       } 
	       else if (purchase.getSku().equals(SKU_GAS1)) {
		        consumeItem();
	       }
	       else if (purchase.getSku().equals(SKU_GAS2)) {
		        consumeItem();
	       }
	       else if (purchase.getSku().equals(SKU_GAS3)) {
		        consumeItem();
	       }
	       else if (purchase.getSku().equals(SKU_GAS4)) {
		        consumeItem();
	       }
	       else if (purchase.getSku().equals(SKU_GAS5)) {
		        consumeItem();
	       }
	       else if (purchase.getSku().equals(SKU_GAS6)) {
		        consumeItem();
	       }
	      
        }
    }; 

    public void consumeItem() {
	    mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }
	
    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener 
       = new IabHelper.QueryInventoryFinishedListener() {
	       public void onQueryInventoryFinished(IabResult result,
	          Inventory inventory) {
		   		   
	          if (result.isFailure()) {	        	  
	          } else {
	        	  if (numberImage == 4) {
	        		  mHelper.consumeAsync(inventory.getPurchase(SKU_GAS0), 
	       			       mConsumeFinishedListener);  
	        	  } else if (numberImage == 5) {
	        		  mHelper.consumeAsync(inventory.getPurchase(SKU_GAS1), 
		       			       mConsumeFinishedListener);  
		        	  } else if (numberImage == 6) {
		        		  mHelper.consumeAsync(inventory.getPurchase(SKU_GAS2), 
			       			       mConsumeFinishedListener);  
			        	  } else if (numberImage == 7) {
			        		  mHelper.consumeAsync(inventory.getPurchase(SKU_GAS3), 
				       			       mConsumeFinishedListener);  
				        	  } else if (numberImage == 8) {
				        		  mHelper.consumeAsync(inventory.getPurchase(SKU_GAS4), 
					       			       mConsumeFinishedListener);  
					        	  } else if (numberImage == 9) {
					        		  mHelper.consumeAsync(inventory.getPurchase(SKU_GAS5), 
						       			       mConsumeFinishedListener);  
						        	  } else if (numberImage == 10) {
						        		  mHelper.consumeAsync(inventory.getPurchase(SKU_GAS6), 
							       			       mConsumeFinishedListener);  
							        	  }
	          }
          }
    };
    
    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
    		  new IabHelper.OnConsumeFinishedListener() {
    		   public void onConsumeFinished(Purchase purchase, 
    	             IabResult result) {

    		 if (result.isSuccess()) {		    	 
    			 launchAction();
    		 } else {
    			 
    		 }
    	  }
    	};
	
	public void launchAction(){
		
   	 ActionCreation actionCreation = new ActionCreation();
   	 actionCreation.setCategory( category);
   	 actionCreation.setNameCompany( nameCompany );
   	 actionCreation.setNameAction( nameAction );
   	 actionCreation.setAction_start( action_start );
   	 actionCreation.setAction_end( action_end );
   	 actionCreation.setDescrip( descrip);
   	 actionCreation.setNumberImage( numberImage );
   	 actionCreation.setAdress(adress);
   	 actionCreation.setLogin(login);
   	 actionCreation.setLogo(url);
   	 
   	// save object asynchronously
   	 Backendless.Persistence.save( actionCreation, new AsyncCallback<ActionCreation>() {
   		 
   		 @Override
   	    public void handleResponse( ActionCreation response )
   	    {
   			 Toast.makeText(Preview.this, "Акция успешно создана", Toast.LENGTH_LONG).show();
   			 startActivity( new Intent(getBaseContext(), PrivateOffice.class) );
   	    }

			@Override
			public void handleFault(BackendlessFault arg0) {
				Toast.makeText(Preview.this, "Ошибка создания акции! Повторите попытку", Toast.LENGTH_LONG).show();
				
			} 
   	  });
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
