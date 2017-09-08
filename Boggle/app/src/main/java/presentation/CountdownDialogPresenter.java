package presentation;

import com.boggleproject.boggle.EditCountdownDialogInterface;

/**
 * Created by lukas on 9/6/17.
 */

public class CountdownDialogPresenter {

    private MainActivityPresenter mainActivityPresenter;
    private EditCountdownDialogInterface cdDialog;
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
        mainActivityPresenter.setCountDownTimer(cdDialog.getCountdownTimeInSeconds() * 1000);
        mainActivityPresenter.updateView();
    }

    public long getCountdownTimeInSeconds(){
        return mainActivityPresenter.getCountDownTimeInMilliseconds()/1000;
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
}
