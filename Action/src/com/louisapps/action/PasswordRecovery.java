package com.louisapps.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;

public class PasswordRecovery extends Activity {
	
	EditText textRecovery;
	String textRec;
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.password_recovery);
	        
	        textRecovery = (EditText) findViewById(R.id.textRecovery);
	 }
	 
	 public void onclick(View view)  
	 {  
		 textRec = textRecovery.getText().toString();
		 if (TextUtils.isEmpty(textRec)) {
     		  Toast.makeText(this, "Вы не ввели логин", Toast.LENGTH_LONG).show();
     	      return;
     	    } 
		 Backendless.UserService.restorePassword( textRec, new AsyncCallback<Void>()
				 {
				 public void handleResponse( Void response )
				  {
					 Toast.makeText(PasswordRecovery.this, "Мы выслали Вам пароль", Toast.LENGTH_LONG).show();
					 Intent intent = new Intent(PasswordRecovery.this, LogIn.class);
	      		     startActivity(intent);
				  }
				  
				  public void handleFault( BackendlessFault fault )
				  {
				    // password revovery failed, to get the error code call fault.getCode()
				  }
				 });
	 } 

}
