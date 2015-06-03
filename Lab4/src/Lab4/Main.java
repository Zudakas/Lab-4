package Lab4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static int state = 0;
    public enum Die {
        D4(4), D6(6), D8(8),D10(10),D12(12), D20(20), D100(100);
        private int value;

        private Die(int value){
            this.value = value;
        }
    }
    public static void main(String[] args) {
        int inputDieType = 0;
        int inputDieCount = 0;
        int countOnes = 0;
        int roll = 0;


        Scanner input = new Scanner(System.in);
        ArrayList<Integer> Rolls = new ArrayList<>();

        while ((inputDieType != Die.D4.value) && (inputDieType != Die.D6.value) && (inputDieType != Die.D8.value) && (inputDieType != Die.D10.value) && (inputDieType != Die.D12.value) && (inputDieType != Die.D20.value) && (inputDieType != Die.D100.value)) {
            System.out.print("Please enter a die type(4, 6, 8, 10, 12, 20, or 100): ");
            inputDieType = input.nextInt();
        }
        while ((inputDieCount < 1) || (inputDieCount > 10)) {
            System.out.print("Please enter a die count between 1 & 10: ");
            inputDieCount = input.nextInt();
        }

            for (int x = 0; x < inputDieCount; x++) {
                if(state != 0) {

                    System.exit(0);
                }
                roll = rollDie(inputDieType);
                roll = checkMatch(inputDieType, roll);
                roll = checkWin(roll);

                System.out.println("Roll " +( x + 1) + ": " + roll);
                if (roll == 1) {
                    countOnes = countOnes + 1;
                    System.out.println("Ones Counted: " + countOnes);
                }
                if (countOnes == inputDieCount / 2) {
                    System.out.println("Bust!");
                    state = 1;
                }

            }
            System.out.println("You Lose!");
        }

    public static int checkWin(int r) {
        if ((r == 5) || (r == 30)) {
            System.out.println("You Win!");
            state = 2;
        }
        return r;
    }
    public static int rollDie(int d){
        Random roller = new Random();
        int roll;
        roll =1 + roller.nextInt(d);
        return roll;
    }
    public static int checkMatch(int d, int r) {
        int originalRoll = r;
        int newRoll = 0;
        int totalRoll = r;
        if(d == r) {
            newRoll = rollDie(d);
            System.out.println("Added Roll "+ newRoll + " to original roll of "+ originalRoll + ".");
            totalRoll = checkMatch(d,newRoll);
            totalRoll = newRoll + originalRoll;
            totalRoll = checkWin(totalRoll);
        }
    return totalRoll;
    }
    public static int checkMatch(int d, int r, int t) {
        int originalRoll = r;
        int newRoll = 0;
        int totalRoll = t;
        if(d == r) {
            newRoll = rollDie(d);
            System.out.println("Added Roll "+ newRoll + " to original roll of "+ originalRoll + ".");
            newRoll = checkMatch(d,newRoll);
            totalRoll = newRoll + originalRoll;
            totalRoll = checkWin(totalRoll);
        }
        return totalRoll;
    }
}
