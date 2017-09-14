package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;

import presentation.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private MainActivityPresenter presenter;
    private TextView countDownTxtView;
    private Button throwBtn;
    private ToggleButton toggleTimerBtn;
    private Button timerFinishedBtn;
    private boolean isTimerRunning = false;
    private String[] topSidesOfDices;
    private GridAdapter gridAdapter;
    private GridView diceView;

    private void initTextViews() {
        countDownTxtView = (TextView) findViewById(R.id.countdownTxtView);
    }

    private void initButtons() {
        throwBtn = (Button) findViewById(R.id.throwbtn);
        timerFinishedBtn = (Button) findViewById(R.id.timerfinishedbtn);
        timerFinishedBtn.setVisibility(View.INVISIBLE);
        toggleTimerBtn = (ToggleButton) findViewById(R.id.toggleTimerBtn);
    }

    private void initDiceView(){
        mixDices();
        diceView = findViewById(R.id.diceGridView);
        gridAdapter = new GridAdapter(topSidesOfDices);
        diceView.setAdapter(gridAdapter);
    }

    private void initContent(){
        mixDices();
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
        initDiceView();
        initContent();



        throwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mixDices();
                gridAdapter.notifyDataSetChanged();
                diceView.notifyAll();
                //diceView.invalidate();
//                diceView.setAdapter(new GridAdapter(topSidesOfDices));
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
    public void mixDices() {
        int i = 1;
        if ( i == 1) {
            topSidesOfDices = presenter.getMixedDices();
        } else {
        String [] array = {"A", "B", "C", "D"};
        topSidesOfDices = array;}
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