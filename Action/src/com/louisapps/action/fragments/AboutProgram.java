package com.louisapps.action.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.louisapps.action.R;

public class AboutProgram extends Fragment {

    public AboutProgram() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.about_program, container,
                false);

        return rootView;
    }

}
