package com.louisapps.action.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.backendless.persistence.local.UserTokenStorageFactory;
import com.louisapps.action.LogIn;
import com.louisapps.action.PrivateOffice;
import com.louisapps.action.R;

public class MyActions extends Fragment {
	
	public MyActions() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.my_actions, container,
                false);
        String userToken = UserTokenStorageFactory.instance().getStorage().get();
    	if( userToken != null && !userToken.equals( "" ) ){
        	Intent myIntent1 = new Intent(getActivity(), PrivateOffice.class);
            this.startActivity(myIntent1);
    	}
    	else {
        	Intent myIntent1 = new Intent(getActivity(), LogIn.class);
            this.startActivity(myIntent1);       		
    	}
        
        return rootView;
    }

}
