package model;

import java.util.Random;

/**
 * Created by lukas on 7/8/17.
 */

public class Dice {
    private char[] sides;
    private static Random rand = new Random();

    public Dice(char[] sides) {
        this.sides = sides;
    }
    public char throwDice(){
        int randNum = rand.nextInt(6);
        return sides[randNum];
    }
}
