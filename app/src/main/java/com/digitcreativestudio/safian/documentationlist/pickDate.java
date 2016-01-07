package com.digitcreativestudio.safian.documentationlist;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by faqih_000 on 12/15/2015.
 */
public class PickDate implements View.OnClickListener {
    Activity mActivity;
    DatePickerDialog.OnDateSetListener mDate;
    Calendar mCalendar;
    EditText editText;
    public PickDate(Activity activity){
        mActivity = activity;
        mCalendar = Calendar.getInstance();
        mDate = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                mCalendar.set(Calendar.YEAR, year);
                mCalendar.set(Calendar.MONTH, monthOfYear);
                mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

    }
    @Override
    public void onClick(View view) {
        new DatePickerDialog(mActivity, mDate, mCalendar
                .get(Calendar.YEAR), mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)).show();
        editText = (EditText) view;
    }

    private void updateLabel(){
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");

//        editText.setText(sdf.format(mCalendar.getTime()));
        editText.setText(sdf.format(new Date()));
    }
}
