package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import presentation.CountdownDialogPresenter;

/**
 * Created by lukas on 9/6/17.
 */

public class EditCountdownDialog extends DialogFragment implements EditCountdownDialogInterface {

    private NumberPicker minutePicker;
    private NumberPicker secondPicker;
    private CountdownDialogPresenter countdownDialogPresenter;
    private long minute;
    private long second;

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
        minutePicker = view.findViewById(R.id.numberPickerMinutes);
        secondPicker = view.findViewById(R.id.numberPickerSeconds);
        Button okayBtn = view.findViewById(R.id.okayBtn);

        setMinuteValuesInView();
        setSecondValuesInView();

        setCurrentCountDownTimeInListViews(countdownDialogPresenter.getCountdownTimeInSeconds());

        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int val1, int val2) {
                updateCountdownTime();
            }
        });

        secondPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int val1, int val2) {
                updateCountdownTime();
            }
        });

        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateCountdownTime();
                dismiss();
            }
        });

        return view;
    }

    private void updateCountdownTime(){
        minute = minutePicker.getValue();
        second = secondPicker.getValue();
        countdownDialogPresenter.setCountdownTimeInSeconds();
    }

    public String[] getStringArrayForMinMaxValues(int min, int max){
        int numOfElements = max-min+1;
        String[] elementArray = new String[numOfElements];
        int value = min;
        for(int i = 0; i<elementArray.length; i++){
            elementArray[i]=String.format("%02d",value);
            value++;
        }
        return elementArray;
    }

    @Override
    public void setMinuteValuesInView() {
        int min = countdownDialogPresenter.getMinMaxMinutes()[0];
        int max = countdownDialogPresenter.getMinMaxMinutes()[1];
        minutePicker.setMinValue(min);
        minutePicker.setMaxValue(max);
        minutePicker.setDisplayedValues(getStringArrayForMinMaxValues(min, max));
    }

    @Override
    public void setSecondValuesInView() {
        int min = countdownDialogPresenter.getMinMaxSeconds()[0];
        int max = countdownDialogPresenter.getMinMaxSeconds()[1];
        secondPicker.setMinValue(min);
        secondPicker.setMaxValue(max);
        secondPicker.setDisplayedValues(getStringArrayForMinMaxValues(min, max));
    }

    private void setCurrentCountDownTimeInListViews(long countDownTimeInSeconds) {
        long minutes = (int) countDownTimeInSeconds / 60;
        long seconds = countDownTimeInSeconds - minutes * 60;
        minutePicker.setValue((int) minutes);
        secondPicker.setValue((int) seconds);
    }

    private long getMinutesInSeconds() {
        return minute * 60;
    }

    private long getSeconds() {
        return second;
    }

    private long calculateCountdownTimeInSeconds() {
        return getMinutesInSeconds() + getSeconds();
    }

    @Override
    public long getCountdownTimeInSeconds() {
        return calculateCountdownTimeInSeconds();
    }

}
