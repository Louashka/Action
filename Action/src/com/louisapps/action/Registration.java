package com.louisapps.action;

import java.io.FileNotFoundException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.files.BackendlessFile;


public class Registration extends Activity implements OnClickListener {
	
	EditText regLogin;
	EditText regPswd;
	EditText regCnPswd;
	EditText email;
	EditText nameComp;
	Button Ok;
	ImageButton uploadImage;
	String rLogin = "";
	String rPswd = "";
	String rCnPswd = "";
	String rEmail = "";
	String rNameCompany = "";
	Bitmap newBm = null;
	ProgressDialog progressDialog; 
	private static int RESULT_LOAD_IMAGE = 1;
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);
        setTitle("Регистрация");
        String appVersion = "v1";
        String YOUR_APP_ID = "149BBA0B-F8B9-CD77-FF47-C9C80D8F8500";
        String YOUR_SECRET_KEY = "3AFEEA72-40D3-4F4D-FF31-E4208FC91D00";
        Backendless.initApp( this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion );
        
        regLogin = (EditText) findViewById(R.id.regLogin);
        regPswd = (EditText) findViewById(R.id.regPswd);
        regCnPswd = (EditText) findViewById(R.id.regCnPswd);
        email = (EditText) findViewById(R.id.email);
        nameComp = (EditText) findViewById(R.id.nameComp);
        Ok = (Button) findViewById(R.id.ok);
        uploadImage = (ImageButton) findViewById(R.id.uploadImage);
                       
        Ok.setOnClickListener(this);
        uploadImage.setOnClickListener(this);
	}
	
	@Override
    public void onClick(View v) {
		
		switch(v.getId()) {
		case R.id.uploadImage:
			Intent i = new Intent(
					Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
					 
		    startActivityForResult(i, RESULT_LOAD_IMAGE);
			break;
		case R.id.ok:
			 rLogin = regLogin.getText().toString();
			 rPswd = regPswd.getText().toString();
			 rCnPswd = regCnPswd.getText().toString();
			 rEmail = email.getText().toString();
			 rNameCompany = nameComp.getText().toString();
			
	    	if (TextUtils.isEmpty(rLogin)
	    	        || TextUtils.isEmpty(rPswd)
	    	        || TextUtils.isEmpty(rCnPswd)
	    	        || TextUtils.isEmpty(rEmail)
	    	        || TextUtils.isEmpty(rNameCompany)
	    	        || newBm == null) {
	    		  Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
	    	      return;
	    	    } 
	    	else {
	    	   	if (!rPswd.equals(rCnPswd)){
	        		Toast.makeText(this, "Пароли не совпадают", Toast.LENGTH_LONG).show();
	      	        return;
	        	}
	    	   	else {
	    	   		progressDialog = ProgressDialog.show( this, "", "Loading", true );
	    	   		Backendless.Files.Android.upload( newBm, Bitmap.CompressFormat.PNG, 100, rLogin + "_mylogo.png", "logo", new AsyncCallback<BackendlessFile>()
	   				{
	   				    @Override
	   				    public void handleResponse( final BackendlessFile backendlessFile )
	   				    {
	   				    	BackendlessUser user = new BackendlessUser();
	   		            	user.setProperty("login", rLogin);
	   		            	user.setEmail(rEmail);
	   		            	user.setPassword(rPswd);
	   		            	user.setProperty("companyName", rNameCompany);
	   		            	user.setProperty("photo", Defaults.LogoUrl + rLogin + "_mylogo.png");
	   		            	    		 
	   		            	    		  Backendless.UserService.register( user, new BackendlessCallback<BackendlessUser>() 
	   		            	    		  {
	   		            	    		      @Override
	   		            	    		      public void handleResponse( BackendlessUser backendlessUser )
	   		            	    		      {
	   		            	    		    	  progressDialog.cancel();
	   		            	    		    	  startActivity( new Intent(getBaseContext(), LogIn.class) );
	   		            	    		    	  Toast.makeText(Registration.this, "Вы успешно зарегистрированы! Войдите в систему", Toast.LENGTH_LONG).show();
	   		            	    		      }
	   		            	    		      
	   		            	    		      @Override
	   		            	    		      public void handleFault( BackendlessFault fault )
	   		            	    		      {
	   		            	    		    	  progressDialog.cancel();
	   		            	    		    	  Toast.makeText(Registration.this, "Ошибка регистрации! Попробуйте снова", Toast.LENGTH_LONG).show();
	   		            	    		    	  return;
	   		            	    		      }
	   		            	    		  } );
	   				    }
	   				 
	   				    @Override
	   				    public void handleFault( BackendlessFault backendlessFault )
	   				    {
	   				         Toast.makeText( Registration.this, backendlessFault.toString(), Toast.LENGTH_SHORT ).show();
	   				    }
	   				});
	            	
	    	   	}
	 	
	    	}
			break;
		}
		
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
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	       super.onActivityResult(requestCode, resultCode, data);
	       final int REQUIRED_SIZE = 120;
	        
	       if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
//	           Uri selectedImage = data.getData();
//	           Bitmap mBitmap = null;
//	           try {
//				   mBitmap = decodeBitmap(selectedImage, this);
//				   uploadImage.setImageBitmap(mBitmap);
//			   } catch (FileNotFoundException e) {
//				   // TODO Auto-generated catch block
//				   Toast.makeText(this, "Failed loading image from gallery", Toast.LENGTH_LONG).show();
//	               return;
//			   }
	    	   
	           try {
	               Uri selectedImage = data.getData();
	               ContentResolver cr = this.getContentResolver();
	               Bitmap mBitmap = null;
	               mBitmap = Media.getBitmap(cr, selectedImage);
	               int width = mBitmap.getWidth();
	               int height = mBitmap.getHeight();
	               while (true) {
	   	             if (width / 2 < REQUIRED_SIZE || height / 2 < REQUIRED_SIZE) {
	   	                 break;
	   	             }
	   	             width /= 2;
	   	             height /= 2;
	               }
	               newBm = Bitmap.createScaledBitmap(mBitmap, width,
	            			height, false);
	               uploadImage.setImageBitmap(newBm);
	           } catch(Exception e){
	        	   Toast.makeText(this, "Failed loading image from gallery", Toast.LENGTH_LONG).show();
	               return;
	           }
//	           String picturePath = Defaults.getPath(this, selectedImage);
//	           String[] filePathColumn = { MediaStore.Images.Media.DATA };
//	   
//	           Cursor cursor = getContentResolver().query(selectedImage,
//	                   filePathColumn, null, null, null);
//	           cursor.moveToFirst();
//	   
//	           int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//	           String picturePath = cursor.getString(columnIndex);
//	           cursor.close();            
//	           
//	           
//	           uploadImage.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//	                        
//	            String picturePath contains the path of selected Image
	       }

	   }
	   
	   public static Bitmap decodeBitmap(Uri selectedImage, Context context)
	            throws FileNotFoundException {
	        BitmapFactory.Options o = new BitmapFactory.Options();
	        o.inJustDecodeBounds = true;
	        BitmapFactory.decodeStream(context.getContentResolver()
	                .openInputStream(selectedImage), null, o);

	        final int REQUIRED_SIZE = 90;

	        int width_tmp = o.outWidth, height_tmp = o.outHeight;
	        if (width_tmp > height_tmp) {
	        	width_tmp = height_tmp;
	        } else {
	        	height_tmp = width_tmp;
	        }
	        int scale = 1;
	        while (true) {
	            if (width_tmp / 2 < REQUIRED_SIZE || height_tmp / 2 < REQUIRED_SIZE) {
	                break;
	            }
	            width_tmp /= 2;
	            height_tmp /= 2;
	            scale *= 2;
	        }

	        BitmapFactory.Options o2 = new BitmapFactory.Options();
	        o2.inSampleSize = scale;
	        return BitmapFactory.decodeStream(context.getContentResolver()
	                .openInputStream(selectedImage), null, o2);
	    }
 
}
