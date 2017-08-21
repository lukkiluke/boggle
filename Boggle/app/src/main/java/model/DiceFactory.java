package model;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by lukas on 7/9/17.
 */

public class DiceFactory {

    public static Dice[] createDices(InputStream input){
        Dice dices[] = new Dice[16];

        Scanner scanner = new Scanner(input);
        scanner.useDelimiter(",");
        char[] charArr = new char[6];
        int i = 0;
        int j = 0;
        while(scanner.hasNextLine()){
            charArr[i] = scanner.next().charAt(0);
            if(i == 5){
                dices[j] = new Dice(getChars(charArr));
                scanner.nextLine();
                i = -1;
                j++;
            }
            i++;
        }
        return dices;
    }

    private static char[] getChars(char[] charArr) {
        char[] tempArr = new char[6];
        for(int k = 0; k < tempArr.length; k++){
            tempArr[k] = charArr[k];
        }
        return tempArr;
    }
}
