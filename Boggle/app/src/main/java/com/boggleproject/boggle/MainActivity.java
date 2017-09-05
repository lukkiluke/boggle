package com.boggleproject.boggle;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.io.IOException;
import java.io.InputStream;

import presentation.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements MainActivityInterface {

    private MainActivityPresenter presenter;
    private TextView[] diceTextViewArray;
    private TextView countDownTxtView;
    private Button throwBtn;
    private ToggleButton toggleTimerBtn;
    private Button timerFinishedBtn;

    private void initTextViews() {
        diceTextViewArray = new TextView[16];
        diceTextViewArray[0] = (TextView) findViewById(R.id.diceTextView1);
        diceTextViewArray[1] = (TextView) findViewById(R.id.diceTextView2);
        diceTextViewArray[2] = (TextView) findViewById(R.id.diceTextView3);
        diceTextViewArray[3] = (TextView) findViewById(R.id.diceTextView4);
        diceTextViewArray[4] = (TextView) findViewById(R.id.diceTextView5);
        diceTextViewArray[5] = (TextView) findViewById(R.id.diceTextView6);
        diceTextViewArray[6] = (TextView) findViewById(R.id.diceTextView7);
        diceTextViewArray[7] = (TextView) findViewById(R.id.diceTextView8);
        diceTextViewArray[8] = (TextView) findViewById(R.id.diceTextView9);
        diceTextViewArray[9] = (TextView) findViewById(R.id.diceTextView10);
        diceTextViewArray[10] = (TextView) findViewById(R.id.diceTextView11);
        diceTextViewArray[11] = (TextView) findViewById(R.id.diceTextView12);
        diceTextViewArray[12] = (TextView) findViewById(R.id.diceTextView13);
        diceTextViewArray[13] = (TextView) findViewById(R.id.diceTextView14);
        diceTextViewArray[14] = (TextView) findViewById(R.id.diceTextView15);
        diceTextViewArray[15] = (TextView) findViewById(R.id.diceTextView16);

        countDownTxtView = (TextView) findViewById(R.id.countdownTxtView);
    }

    private void initButtons() {
        throwBtn = (Button) findViewById(R.id.throwbtn);
        timerFinishedBtn = (Button) findViewById(R.id.timerfinishedbtn);
        timerFinishedBtn.setVisibility(View.INVISIBLE);
        toggleTimerBtn = (ToggleButton) findViewById(R.id.toggleTimerBtn);
        toggleTimerBtn.setText("Timer starten");
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
        presenter.setCountDownTimer(180000);

        throwBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMixedDicesOnView();
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
                    toggleTimerBtn.setTextOn("Timer abbrechen");
                    throwBtn.setEnabled(false);
                } else {
                    toggleTimerBtn.setTextOff("Timer neustarten");
                    throwBtn.setEnabled(true);
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
        if(id == R.id.timer_settings){
            countDownTxtView.setText("181");
            showCountdownDialog();
//            showTimePickerDialog();
        }

        return super.onOptionsItemSelected(item);
    }

//    public void showTimePickerDialog(){
//        DialogFragment fragment = new TimePickerFragment();
//        fragment.show(getSupportFragmentManager(), "timePicker");
//    }

    private void showCountdownDialog() {
        EditCountdownDialog editCountdownDialog= new EditCountdownDialog();
        editCountdownDialog.show(getSupportFragmentManager(), "fragment_edit_countdown");
    }

    @Override
    public void setMixedDicesOnView() {
        char[] topSidesOfDices = presenter.getMixedDices();
        ;
        for (int i = 0; i < diceTextViewArray.length; i++) {
            diceTextViewArray[i].setText(Character.toString(topSidesOfDices[i]));
            diceTextViewArray[i].setRotation(getRotation());
        }
    }

    public int getRotation() {
        return presenter.getRotation();
    }

    @Override
    public void actionOnTimerTick(long l) {
        countDownTxtView.setText("" + l / 1000);
    }

    @Override
    public void actionOnTimerFinish() {
        timerFinishedBtn.setVisibility(View.VISIBLE);
        toggleTimerBtn.setText("Timer neustarten");
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