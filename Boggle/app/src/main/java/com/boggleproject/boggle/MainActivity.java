package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import presentation.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private MainActivityPresenter presenter;
    private TextView countDownTxtView;
    private Button throwBtn;
    private ToggleButton toggleTimerBtn;
    private Button timerFinishedBtn;
    private boolean isTimerRunning = false;
    private String[] topSidesOfDices;

    private void initTextViews() {

        countDownTxtView = (TextView) findViewById(R.id.countdownTxtView);
    }

    private void initButtons() {
        throwBtn = (Button) findViewById(R.id.throwbtn);
        timerFinishedBtn = (Button) findViewById(R.id.timerfinishedbtn);
        timerFinishedBtn.setVisibility(View.INVISIBLE);
        toggleTimerBtn = (ToggleButton) findViewById(R.id.toggleTimerBtn);
        toggleTimerBtn.setText("Timer starten");
    }

    private void initContent(){
        setMixedDicesOnView();
        setCountDownText(presenter.getCountDownTimeInMilliseconds());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        initTextViews();
        initButtons();

        presenter = new MainActivityPresenter(this);
        initContent();
        final GridView gridView = findViewById(R.id.diceGridView);
        gridView.setAdapter(new GridAdapter(presenter.getMixedDices()));

        throwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMixedDicesOnView();
                gridView.setAdapter(new GridAdapter(topSidesOfDices));
            }

        });
        timerFinishedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timerFinishedBtn.setVisibility(View.INVISIBLE);
            }
        });
        toggleTimerBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presenter.timerButtonClick();
                if (isChecked) {
                    throwBtn.setEnabled(false);
                    isTimerRunning = true;
                } else {
                    throwBtn.setEnabled(true);
                    timerFinishedBtn.setVisibility(View.INVISIBLE);
                    isTimerRunning = false;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.timer_settings) {
            if(!isTimerRunning){
                presenter.showCountdownDialog();
                return true;
            } else {
                presenter.cancelTimer();
                presenter.showCountdownDialog();
                toggleTimerBtn.performClick();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showCountdownDialog(EditCountdownDialog editCountdownDialog) {
        editCountdownDialog.show(getSupportFragmentManager(), "fragment_edit_countdown");
    }

    @Override
    public void setMixedDicesOnView() {
        topSidesOfDices = presenter.getMixedDices();


//        for (int i = 0; i < diceTextViewArray.length; i++) {
//            diceTextViewArray[i].setText(Character.toString(topSidesOfDices[i]));
//            diceTextViewArray[i].setRotation(getRotation());
//        }
    }

    public int getRotation() {
        return presenter.getRotation();
    }

    private void setCountDownText(long countDownTimeInMilliseconds){
        countDownTxtView.setText(getTextInMinutesAndSeconds(countDownTimeInMilliseconds));
    }

    private String getTextInMinutesAndSeconds(long timeInMilliseconds){
        long seconds = timeInMilliseconds/1000;
        long minutes = (int) seconds/60;
        seconds = seconds - (minutes*60);
        return String.format("%02d:%02d", minutes, seconds);
    }

    @Override
    public void updateView() {
        setCountDownText(presenter.getCountDownTimeInMilliseconds());
    }

    @Override
    public void actionOnTimerTick(long l) {
        countDownTxtView.setText(getTextInMinutesAndSeconds(l));
    }

    @Override
    public void actionOnTimerFinish() {
        timerFinishedBtn.setVisibility(View.VISIBLE);
//        toggleTimerBtn.setText("Timer zurücksetzen");
    }

    @Override
    public InputStream getInputDice() {
        InputStream input = null;
        try {
            input = this.getAssets().open("diceSides_ger.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}