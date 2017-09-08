package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
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

        getDialog().setTitle("Countdown");

        minutePicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int value, int i1) {
                minute = value;
                countdownDialogPresenter.setCountdownTimeInSeconds();
            }
        });

        secondPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int value, int i1) {
                second = value;
                countdownDialogPresenter.setCountdownTimeInSeconds();
            }
        });

        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void setMinuteValuesInView() {
        minutePicker.setMinValue((int) countdownDialogPresenter.getMinMaxMinutes()[0]);
        minutePicker.setMaxValue((int) countdownDialogPresenter.getMinMaxMinutes()[1]);
    }

    @Override
    public void setSecondValuesInView() {
        secondPicker.setMinValue((int) countdownDialogPresenter.getMinMaxSeconds()[0]);
        secondPicker.setMaxValue((int) countdownDialogPresenter.getMinMaxSeconds()[1]);
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
