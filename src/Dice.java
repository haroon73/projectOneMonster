/*
Name: Mohammad Haroon
Date: 10/14/2022
Program Description: In this program we have one main abstract class which is called Monster and it has four child class
.The four subclasses implementing the abstracts methods of Monster class. It is a game program which make attacks against each
other.
 */

import java.util.Random;

public class Dice {

    public static int roll(int min, int max) {
        Random random = new Random();
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }

        return (random.nextInt(max - Math.abs(min)) + 1) + min;
    }

    public static int roll(int max) {
        return Dice.roll(max, 1);
    }

}