package com.louisapps.action;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;

public class LogIn extends Activity implements OnClickListener {
	
	EditText username;
	EditText pswrd;
	TextView forgetPassword;
	Button btnLogin;
	Button registration;
	boolean stayLoggedIn;
	String rLogin;
	String rPswrd;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.authorization);
        setTitle("Вход в систему");
        String appVersion = "v1";
        String YOUR_APP_ID = "149BBA0B-F8B9-CD77-FF47-C9C80D8F8500";
        String YOUR_SECRET_KEY = "3AFEEA72-40D3-4F4D-FF31-E4208FC91D00";
        Backendless.initApp( this, YOUR_APP_ID, YOUR_SECRET_KEY, appVersion );
        
        username = (EditText) findViewById(R.id.username);
        pswrd = (EditText) findViewById(R.id.password);
        forgetPassword = (TextView) findViewById(R.id.forgetPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        registration = (Button) findViewById(R.id.registration);     
        
        btnLogin.setOnClickListener(this);
        registration.setOnClickListener(this);
        forgetPassword.setOnClickListener(this);

	   }

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
        case R.id.btnLogin:
        	
            rLogin = username.getText().toString();
            rPswrd = pswrd.getText().toString();
            stayLoggedIn = true;
            
        	if (TextUtils.isEmpty(rLogin)
        	        || TextUtils.isEmpty(rPswrd)) {
        		  Toast.makeText(this, "Заполните все поля", Toast.LENGTH_LONG).show();
        	      return;
        	    } else {
        	       	Backendless.UserService.login( rLogin, rPswrd, new BackendlessCallback<BackendlessUser>() 
        	  	    		  {
        	  	    		      @Override
        	  	    		      public void handleResponse( BackendlessUser backendlessUser )
        	  	    		      {  	    		    	  
        	  	        		      Intent intent = new Intent(getBaseContext(), PrivateOffice.class);
        	  	      		          intent.putExtra("login", rLogin);
        	  	      		          startActivity(intent);  	      		     
        	  	    		      }
        	  	    		      
        	  	    		      @Override
        	  	    		      public void handleFault( BackendlessFault fault )
        	  	    		      {
        	  	    		    	  Toast.makeText(LogIn.this, "Неправильная пара логин/пароль", Toast.LENGTH_LONG).show();    		    
        	  	    		      }

        	  	    		  }, stayLoggedIn );
        	    }
            return;
        case R.id.registration:
        	Intent myIntent2 = new Intent(this, Registration.class);
            this.startActivity(myIntent2);
            return;
        case R.id.forgetPassword:
        	Intent myIntent3 = new Intent(this, PasswordRecovery.class);
            this.startActivity(myIntent3);
        	return;
        default:
            return;
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

}
