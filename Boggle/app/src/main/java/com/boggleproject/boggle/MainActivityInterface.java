package com.boggleproject.boggle;

import java.io.InputStream;

/**
 * Created by lukas on 7/25/17.
 */

public interface MainActivityInterface {

    InputStream getInputDice();

    void setMixedDicesOnView();

    void actionOnTimerTick(long l);

    void actionOnTimerFinish();

    void showCountdownDialog(EditCountdownDialog editCountdownDialog);

    void updateView();
}
