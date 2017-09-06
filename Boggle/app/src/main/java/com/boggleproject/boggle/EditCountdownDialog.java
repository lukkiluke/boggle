package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import presentation.CountdownDialogPresenter;

/**
 * Created by lukas on 9/6/17.
 */

public class EditCountdownDialog extends DialogFragment implements EditCountdownDialogInterface {

    private Spinner minuteSpinner;
    private Spinner secondSpinner;
    private CountdownDialogPresenter countdownDialogPresenter;

    public EditCountdownDialog() {
        // Empty constructor required for DialogFragment
    }

    @Override
    public void setCountdownDialogPresenter(CountdownDialogPresenter countdownDialogPresenter) {
        this.countdownDialogPresenter = countdownDialogPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_countdown, container);
        minuteSpinner = view.findViewById(R.id.minutesSpinner);
        secondSpinner = view.findViewById(R.id.secondsSpinner);

        setMinutes();
        setSeconds();

        getDialog().setTitle("Countdown");

        return view;
    }

    private  ArrayAdapter<Long> getArrayAdapterForArray(ArrayList<Long> dataArray){
        ArrayAdapter<Long> spinnerArrayAdapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_item, dataArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return spinnerArrayAdapter;
    }

    @Override
    public void setMinutes() {
        minuteSpinner.setAdapter(getArrayAdapterForArray(countdownDialogPresenter.getTimeElements()));
    }

    @Override
    public void setSeconds() {
        secondSpinner.setAdapter(getArrayAdapterForArray(countdownDialogPresenter.getTimeElements()));
    }

    //
//    private long getMinutesInSeconds(){
//        long minutes;
//        String minuteText = editTextMinutes.getText().toString();
//        if(TextUtils.isEmpty(minuteText)){
//            minutes = 0;
//        } else {
//            minutes = 60*Long.valueOf(minuteText);
//        }
//        return minutes;
//    }
//
//    private long getSeconds(){
//        long seconds;
//        String secondText = editTextSeconds.getText().toString();
//        if(TextUtils.isEmpty(secondText)){
//            seconds = 0;
//        } else {
//            seconds = Long.valueOf(secondText);
//        }
//        return seconds;
//    }

    private long calculateCountdownTimeInSeconds(){
//        return getMinutesInSeconds() + getSeconds();
        return 0;
    }


    @Override
    public long getCountdownTimeInSeconds() {
        return calculateCountdownTimeInSeconds();
    }

}
