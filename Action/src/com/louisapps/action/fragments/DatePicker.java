package com.louisapps.action.fragments;

import java.util.Calendar;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Button;
import android.widget.EditText;

import com.louisapps.action.R;

public class DatePicker extends DialogFragment
implements DatePickerDialog.OnDateSetListener {

@Override
public Dialog onCreateDialog(Bundle savedInstanceState) {

    // ���������� ������� ����
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);

    // ������� DatePickerDialog � ���������� ���
    Dialog picker = new DatePickerDialog(getActivity(), this, 
            year, month+1, day);
    picker.setTitle(getResources().getString(R.string.choose_date));

    return picker;
}
@Override
public void onStart() {
    super.onStart();
    // ��������� ��������� ����� ��� ������
    Button nButton =  ((AlertDialog) getDialog())
            .getButton(DialogInterface.BUTTON_POSITIVE);
    nButton.setText(getResources().getString(R.string.ready));

}

@Override
public void onDateSet(android.widget.DatePicker datePicker, int year, 
        int month, int day) {
	EditText actionStart = (EditText) getActivity().findViewById(R.id.actionStart);
	if (month < 9){
	    actionStart.setText(day + ".0" + ++month + "." + year);
	} else {
		actionStart.setText(day + "." + ++month + "." + year);
	}
}
}

