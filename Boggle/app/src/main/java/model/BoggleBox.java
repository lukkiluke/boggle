package model;

import java.io.InputStream;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by lukas on 7/8/17.
 */

public class BoggleBox {
    private Dice[] dices;

    public BoggleBox(InputStream diceFile) {
        this.dices = createDices(diceFile);
    }

    private Dice[] createDices(InputStream input) {
        Dice dices[] = new Dice[16];

        Scanner scanner = new Scanner(input);
        scanner.useDelimiter(",");
        char[] charArr = new char[6];
        int i = 0;
        int j = 0;
        while (scanner.hasNextLine()) {
            charArr[i] = scanner.next().charAt(0);
            if (i == 5) {
                dices[j] = new Dice(getChars(charArr));
                scanner.nextLine();
                i = -1;
                j++;
            }
            i++;
        }
        return dices;
    }

    private char[] getChars(char[] charArr) {
        char[] tempArr = new char[6];
        for (int k = 0; k < tempArr.length; k++) {
            tempArr[k] = charArr[k];
        }
        return tempArr;
    }

    public String[] shakeBoggleBox() {
        mixDices();
        char[] charArray = throwDicesInBoggleBox();
        String [] stringArray = new String[charArray.length];
        for (int i = 0; i < charArray.length; i++){
            stringArray[i] = Character.toString(charArray[i]);
        }
        return stringArray;
    }

    private void swapArrayElements(int a, int b) {
        Dice tempDice = dices[a];
        dices[a] = dices[b];
        dices[b] = tempDice;
    }

    private char[] throwDicesInBoggleBox() {
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
