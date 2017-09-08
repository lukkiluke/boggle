package presentation;

import com.boggleproject.boggle.EditCountdownDialogInterface;

/**
 * Created by lukas on 9/6/17.
 */

public class CountdownDialogPresenter {

    private MainActivityPresenter mainActivityPresenter;
    private EditCountdownDialogInterface cdDialog;
    private int[] maxMinMinutes;
    private int[] maxMinSeconds;

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
        maxMinMinutes = new int[]{0, 60};
    }

    public int[] getMinMaxMinutes() {
        return maxMinMinutes;
    }

    private void createMaxMinSeconds() {
        maxMinSeconds = new int[]{0,60};
    }

    public int[] getMinMaxSeconds() {
        return maxMinSeconds;
    }
}
