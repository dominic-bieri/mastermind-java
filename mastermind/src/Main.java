import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

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
        checkUserInputAgainstColorCode();
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
        int[] userColorCode = new int[4];
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

    private static void checkUserInputAgainstColorCode() {
        //TODO: Check User Input against Color Code

        if (checkIfPositionMatches() == 4){
            System.out.println("YOU WON");
        }
        else{
            System.out.println("Correct Colors: " + checkIfColorMatches() + "\t Correct Positions: " + checkIfPositionMatches());
        }

    }

    private static int checkIfColorMatches(/*int[] masterCode, int[] userAttemptCode*/) {
        int[] userAttemptCode = {1, 2, 3 ,4};
        int[] masterCode = {1, 1, 2, 6};
        //TODO: Check if a color matches
        ArrayList<Integer> matches = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (userAttemptCode[j] == masterCode[i]){
                    if (!matches.contains(userAttemptCode[j])){
                        matches.add(userAttemptCode[j]);
                    }
                }
            }
        }
        return matches.size();
    }

    private static int checkIfPositionMatches(/*int[] masterCode, int[] userAttemptCode*/) {
        //TODO: Check if a position matches
        int[] masterCode = {1, 2 , 3 , 4};
        int[] userAttemptCode = {1, 1, 4, 3};

        int matches = 0;
        for (int i = 0; i < 4; i++) {
            if (userAttemptCode[i] == masterCode[i]){
                matches++;
            }
        }
        return matches;
    }

}
