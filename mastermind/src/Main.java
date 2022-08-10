import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Allowed Colors:
        /*
            Blue    = 1
            Red     = 2
            Green   = 3
            Yellow  = 4
            white   = 5
            black   = 6
         */

        ArrayList<int[]> tries = new ArrayList<int[]>();

        int[] colors = {1, 2, 3, 4};

        tries.add(colors);

        while (true) {
            startMenu();
        }
    }

    private static void startMenu() {
        //TODO: Start Menu

        //TODO: Ask User to select game mode ( User vs. User || User vs. Comp.)
    }

    private static int[] promptUserColorCode() {
        //TODO: Prompt user to state custom color code
        int[] userColorCode = {0, 0, 0, 0};
        Scanner scanner = new Scanner(System.in);
        System.out.println("1 = Blue | 2 = Red | 3 = Green | 4 = Yellow | 5 = White | 6 = Black");
        for (int i = 0; i < 4; i++) {
            System.out.print("Insert your " + (i + 1) + ". color: ");
            userColorCode[i] = scanner.nextInt();
            System.out.println();
        }
        return userColorCode;
    }

    private static int[] generateColorCode() {
        //TODO: Generate random Color Code
        int[] userColorCode = {0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            userColorCode[i] = 1 + (int)(Math.random() * ((6 - 1) + 1));
        }
        return userColorCode;
    }

    private static void promptUserColorCodeInput() {
        //TODO: Prompt Color Code from User
    }

    private static void checkUserInputAgainstColorCode() {
        //TODO: Check User Input against Color Code
    }

    private static int checkIfColorMatches() {
        //TODO: Check if a color matches
        return 0;
    }

    private static int checkIfPositionMatches() {
        //TODO: Check if a position matches
        return 0;
    }

}
