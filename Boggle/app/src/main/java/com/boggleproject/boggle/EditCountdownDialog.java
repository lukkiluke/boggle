package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import presentation.CountdownDialogPresenter;

/**
 * Created by lukas on 9/6/17.
 */

public class EditCountdownDialog extends DialogFragment implements EditCountdownDialogInterface {

    private ListView minuteListView;
    private ListView secondListView;
    private CountdownDialogPresenter countdownDialogPresenter;
    private long minutes;
    private long seconds;

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
        minuteListView = view.findViewById(R.id.listViewMinutes);
        secondListView = view.findViewById(R.id.listViewSeconds);
        Button okayBtn = view.findViewById(R.id.okayBtn);

        setMinuteValuesInView();
        setSecondValuesInView();

        getDialog().setTitle("Countdown");

        minuteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                minutes = (long) minuteListView.getAdapter().getItem(position);
                countdownDialogPresenter.setCountdownTimeInSeconds();
            }
        });

        secondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                seconds = (long) secondListView.getAdapter().getItem(position);
                countdownDialogPresenter.setCountdownTimeInSeconds();
            }
        });

        okayBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                countdownDialogPresenter.setCountdownTimeInSeconds();
                dismiss();
            }
        });

        return view;
    }

    private  ArrayAdapter<Long> getArrayAdapterForArray(ArrayList<Long> dataList){
        ArrayAdapter<Long> listViewArrayAdapter = new ArrayAdapter<>(this.getContext(), R.layout.list_item_countdown, dataList); //selected item will look like a spinner set from XML
        return listViewArrayAdapter;
    }

    @Override
    public void setMinuteValuesInView() {
        minuteListView.setAdapter(getArrayAdapterForArray(countdownDialogPresenter.getTimeElements()));
    }

    @Override
    public void setSecondValuesInView() {
        secondListView.setAdapter(getArrayAdapterForArray(countdownDialogPresenter.getTimeElements()));
    }


    private long getMinutesInSeconds(){
        return minutes*60;
    }

    private long getSeconds(){
        return seconds;
    }

    private long calculateCountdownTimeInSeconds(){
        return getMinutesInSeconds()+getSeconds();
    }


    @Override
    public long getCountdownTimeInSeconds() {
        return calculateCountdownTimeInSeconds();
    }

}
