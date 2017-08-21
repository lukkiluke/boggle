package presentation;

import android.os.CountDownTimer;

import com.boggleproject.boggle.MainActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import model.BoggleBox;
import model.DiceFactory;

/**
 * Created by lukas on 7/25/17.
 */

public class MainActivityPresenter {
    private BoggleBox boggleBox;
    private MainActivity mainActivity;
    private CountDownTimer countDownTimer;
    private boolean timerButtonClicked;

    public MainActivityPresenter(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        createBoggleBox();
    }

    private void createBoggleBox(){
        try {
            InputStream input = mainActivity.getAssets().open("diceSide_ger.txt");
            boggleBox = new BoggleBox(DiceFactory.createDices(input));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public char[] getMixedDices() {
        return boggleBox.shakeBoggleBox();
    }

    public int getRotation(){
        Random rand = new Random();
        int randNum = rand.nextInt(4);
        switch(randNum){
            case 0:
                return 0;
            case 1:
                return 90;
            case 2:
                return 180;
            case 3:
                return 270;
            default:
                return 0;
        }
    }

    public void setCountDownTimer(long time){
        countDownTimer = new CountDownTimerActivity(time, 1000);
    }

    public void timerButtonClick(){
        if(!timerButtonClicked)
            countDownTimer.start();
        else {
            countDownTimer.cancel();
        }
        timerButtonClicked = !timerButtonClicked;
    }

    public class CountDownTimerActivity extends CountDownTimer{

        public CountDownTimerActivity(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            mainActivity.actionOnTimerTick(l);
        }

        @Override
        public void onFinish() {
            mainActivity.actionOnTimerFinish();
        }
    }
}
