package com.boggleproject.boggle;

/**
 * Created by lukas on 7/25/17.
 */

public interface MainActivityInterface {

    void setMixedDicesOnView();
    int getRotation();
    void actionOnTimerTick(long l);
    void actionOnTimerFinish();
}
