package model;

import java.util.Random;

/**
 * Created by lukas on 7/8/17.
 */

public class BoggleBox {
    private Dice[] dices;

    public BoggleBox(Dice[] dices) {
        this.dices = dices;
    }

    public char[] shakeBoggleBox() {
        mixDices();
        return throwDicesInToggleBox();
    }

    private void swapArrayElements(int a, int b) {
        Dice tempDice = dices[a];
        dices[a] = dices[b];
        dices[b] = tempDice;
    }

    private char[] throwDicesInToggleBox() {
        char[] thrownDices = new char[dices.length];
        for (int i = 0; i < thrownDices.length; i++) {
            thrownDices[i] = dices[i].throwDice();
        }
        return thrownDices;
    }

    private void mixDices() {
        Random rand = new Random();
        for (int i = 0; i < dices.length; i++) {
            int randNum = rand.nextInt(dices.length);
            swapArrayElements(i, randNum);
        }
    }
}
