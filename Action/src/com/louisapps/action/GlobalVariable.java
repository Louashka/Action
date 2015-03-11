package com.louisapps.action;

import android.app.Application;
import android.content.SharedPreferences;

public class GlobalVariable extends Application {
	
	SharedPreferences sharedPref;
	
	public SharedPreferences getSharedPref(){
		sharedPref = getSharedPreferences("filter", 0);
	    return sharedPref ;
	  }

}
