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
        minuteListView = view.findViewById(R.id.listViewMinutes);
        secondListView = view.findViewById(R.id.listViewSeconds);
        Button okayBtn = view.findViewById(R.id.okayBtn);

        setMinuteValuesInView();
        setSecondValuesInView();

        setCurrentCountDownTimeInListViews(countdownDialogPresenter.getCountdownTimeInSeconds());

        getDialog().setTitle("Countdown");

        minuteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                minute = countdownDialogPresenter.getMinuteAt(position);
                countdownDialogPresenter.setCountdownTimeInSeconds();
            }
        });

        secondListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                second = countdownDialogPresenter.getSecondAt(position);
                countdownDialogPresenter.setCountdownTimeInSeconds();
            }
        });

        okayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countdownDialogPresenter.setCountdownTimeInSeconds();
                dismiss();
            }
        });

        return view;
    }

    private ArrayAdapter<String> getArrayAdapterForList(ArrayList<String> dataList) {
        ArrayAdapter<String> listViewArrayAdapter = new ArrayAdapter<>(this.getContext(), R.layout.list_item_countdown, dataList); //selected item will look like a spinner set from XML
        return listViewArrayAdapter;
    }

    private ArrayList<String> getStringListFromArray(long [] numberArray){
        ArrayList<String> numbersAsStringList = new ArrayList<>();
        for (int i=0; i<numberArray.length; i++){
            numbersAsStringList.add(String.format("%02d", numberArray[i]));
        }
        return numbersAsStringList;
    }

    @Override
    public void setMinuteValuesInView() {
        long [] minutes = countdownDialogPresenter.getMinutesArray();
        minuteListView.setAdapter(getArrayAdapterForList(getStringListFromArray(minutes)));
    }

    @Override
    public void setSecondValuesInView() {
        long [] seconds = countdownDialogPresenter.getSecondsArray();
        secondListView.setAdapter(getArrayAdapterForList(getStringListFromArray(seconds)));
    }

    private void setCurrentCountDownTimeInListViews(long countDownTimeInSeconds){
        long minutes = (int) countDownTimeInSeconds/60;
        long seconds = countDownTimeInSeconds-minutes*60;
        int minutePosition = countdownDialogPresenter.getPositionOfValue(countdownDialogPresenter.getMinutesArray(), minutes);
        int secondPosition = countdownDialogPresenter.getPositionOfValue(countdownDialogPresenter.getSecondsArray(), seconds);
        minuteListView.setSelection(minutePosition);
        secondListView.setSelection(secondPosition);
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
