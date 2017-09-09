package com.boggleproject.boggle;

import presentation.CountdownDialogPresenter;

/**
 * Created by lukas on 9/6/17.
 */

public interface EditCountdownDialogInterface {

    long getCountdownTimeInSeconds();

    void setCountdownDialogPresenter(CountdownDialogPresenter countdownDialogPresenter);

    void setMinuteValuesInView();

    void setSecondValuesInView();
}
