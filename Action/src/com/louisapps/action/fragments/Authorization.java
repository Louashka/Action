package com.louisapps.action.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.louisapps.action.R;

public class Authorization extends Fragment {
	
    public Authorization() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.authorization, container,
                false);
        
        return rootView;
    }

}

