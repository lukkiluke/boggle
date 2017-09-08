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

    public CountdownDialogPresenter(MainActivityPresenter mainActivityPresenter) {
        this.mainActivityPresenter = mainActivityPresenter;
    }

    public void setCountdownDialog(EditCountdownDialogInterface countdownDialog) {
        cdDialog = countdownDialog;
    }

    public void setCountdownTimeInSeconds() {
        countdownTimeInSeconds = cdDialog.getCountdownTimeInSeconds();
        mainActivityPresenter.setCountDownTimer(countdownTimeInSeconds * 1000);
        mainActivityPresenter.updateView();
    }

    public final ArrayList<Long> getTimeElements() {
        ArrayList<Long> timeElements = new ArrayList<>();
        for (int i = 0; i <= 60; i++)
            timeElements.add((long) i);
        return timeElements;
    }
}
