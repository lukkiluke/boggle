package presentation;

import com.boggleproject.boggle.EditCountdownDialogInterface;

/**
 * Created by lukas on 9/6/17.
 */

public class CountdownDialogPresenter {

    private MainActivityPresenter mainActivityPresenter;
    private EditCountdownDialogInterface cdDialog;
    private long[] maxMinMinutes;
    private long[] maxMinSeconds;

    public CountdownDialogPresenter(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
        createMaxMinMinutes();
        createMaxMinSeconds();
    }

    public void setCountdownDialog(EditCountdownDialogInterface countdownDialog) {
        cdDialog = countdownDialog;
    }

    public void setCountdownTimeInSeconds() {
        mainActivityPresenter.setCountDownTimer(cdDialog.getCountdownTimeInSeconds() * 1000);
        mainActivityPresenter.updateView();
    }

    public long getCountdownTimeInSeconds() {
        return mainActivityPresenter.getCountDownTimeInMilliseconds() / 1000;
    }

    private void createMaxMinMinutes() {
        maxMinMinutes = new long[]{0, 60};
    }

    public long[] getMinMaxMinutes() {
        return maxMinMinutes;
    }

    private void createMaxMinSeconds() {
        maxMinSeconds = new long[]{0,60};
    }

    public long[] getMinMaxSeconds() {
        return maxMinSeconds;
    }
}
