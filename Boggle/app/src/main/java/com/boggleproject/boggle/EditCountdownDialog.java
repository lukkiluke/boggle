package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import presentation.CountdownDialogPresenter;

/**
 * Created by lukas on 9/6/17.
 */

public class EditCountdownDialog extends DialogFragment {

    private EditText editTextMinutes;
    private EditText editTextSeconds;
    private CountdownDialogPresenter countdownDialogPresenter;


    public EditCountdownDialog() {
        // Empty constructor required for DialogFragment
        countdownDialogPresenter = new CountdownDialogPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_countdown, container);
        editTextMinutes = (EditText) view.findViewById(R.id.editTextMinutes);
        editTextSeconds = (EditText) view.findViewById(R.id.editTextSeconds);

        getDialog().setTitle("Countdown");

        return view;
    }
}
