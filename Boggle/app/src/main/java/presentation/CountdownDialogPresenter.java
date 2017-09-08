package presentation;

import com.boggleproject.boggle.EditCountdownDialogInterface;

import java.util.ArrayList;

/**
 * Created by lukas on 9/6/17.
 */

public class CountdownDialogPresenter {

    private MainActivityPresenter mainActivityPresenter;
    private EditCountdownDialogInterface cdDialog;
    private long countdownTimeInSeconds;
    private long[] minutes;
    private long[] seconds;

    public CountdownDialogPresenter(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
        createMinutesArray();
        createSecondsArray();
    }

    public void setCountdownDialog(EditCountdownDialogInterface countdownDialog) {
        cdDialog = countdownDialog;
    }

    public void setCountdownTimeInSeconds() {
        countdownTimeInSeconds = cdDialog.getCountdownTimeInSeconds();
        mainActivityPresenter.setCountDownTimer(countdownTimeInSeconds * 1000);
        mainActivityPresenter.updateView();
    }

    private void createMinutesArray(){
        minutes = new long [61];
        for(int i = 0; i<minutes.length; i++){
            minutes[i] = (long) i;
        }
    }

    public long[] getMinutesArray(){
        return minutes;
    }

    public long getMinuteAt(int index){
        return minutes[index];
    }

    private void createSecondsArray(){
        seconds = new long[61];
        for(int i = 0; i<seconds.length; i++){
            seconds[i] = (long) i;
        }
    }

    public long[] getSecondsArray(){
        return seconds;
    }

    public long getSecondAt(int index){
        return seconds[index];
    }

    public final ArrayList<Long> getTimeElements() {
        ArrayList<Long> timeElements = new ArrayList<>();
        for (int i = 0; i <= 60; i++)
            timeElements.add((long) i);
        return timeElements;
    }
}
