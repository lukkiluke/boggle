package presentation;

import android.os.CountDownTimer;

import com.boggleproject.boggle.EditCountdownDialog;
import com.boggleproject.boggle.EditCountdownDialogInterface;
import com.boggleproject.boggle.MainActivityInterface;

import java.util.Random;

import model.BoggleBox;

/**
 * Created by lukas on 7/25/17.
 */

public class MainActivityPresenter {
    private BoggleBox boggleBox;
    private MainActivityInterface mainActivity;
    private CountDownTimer countDownTimer;
    private long countDownTime;
    private boolean timerButtonClicked;

    public MainActivityPresenter(MainActivityInterface mainActivity) {
        this.mainActivity = mainActivity;
        createBoggleBox();
        setCountDownTimer(180000);
    }

    private void createBoggleBox() {
        boggleBox = new BoggleBox(mainActivity.getInputDice());
    }

    public char[] getMixedDices() {
        return boggleBox.shakeBoggleBox();
    }

    public int getRotation() {
        Random rand = new Random();
        int randNum = rand.nextInt(4);
        switch (randNum) {
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

    public void updateView() {
        mainActivity.updateView();
    }

    public void setCountDownTimer(long l) {
        countDownTime = l;
        countDownTimer = new CountDownTimerActivity(l, 1000);
    }

    public long getCountDownTime() {
        return countDownTime;
    }

    public void showCountdownDialog() {
        CountdownDialogPresenter countdownDialogPresenter = new CountdownDialogPresenter(this);
        EditCountdownDialog editCountdownDialog = new EditCountdownDialog();
        countdownDialogPresenter.setCountdownDialog(editCountdownDialog);
        editCountdownDialog.setCountdownDialogPresenter(countdownDialogPresenter);
        mainActivity.showCountdownDialog(editCountdownDialog);
    }

    public void timerButtonClick() {
        if (!timerButtonClicked) {
            countDownTimer.start();
            mainActivity.updateView();
        } else {
            countDownTimer.cancel();
            mainActivity.updateView();
        }
        timerButtonClicked = !timerButtonClicked;
    }

    public class CountDownTimerActivity extends CountDownTimer {

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
