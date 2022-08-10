import javax.sound.midi.Soundbank;
import java.util.ArrayList;
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

        ArrayList<int[]> colorCodes = new ArrayList<int[]>();
        int[] masterCode = {1, 1, 1, 1};


        Scanner scn = new Scanner(System.in);
        char exitParam = 'y';
        int attempts = 0;

        do {
            int menuInput = startMenu();

            switch (menuInput) {
                case 1:
                    masterCode = promptUserColorCode();
                    break;
                case 2:
                    masterCode = generateColorCode();
                    break;
                default:
                    System.out.println("=> Error with menu option!");
                    break;
            }

            System.out.println("The Color has been set! Your Turn!");

            for (attempts = 0; attempts < 12; attempts++) {

                //Print old attempts
                if (attempts > 0) {
                    System.out.println("Your old attempts!");
                    for (int[] attempt : colorCodes) {
                        System.out.println(attempt.toString());
                    }
                }

                //Get new attempt
                colorCodes.add(promptUserColorCode());

                checkUserInputAgainstColorCode();

            }

            //Exit
            System.out.println("Play again? (y/n)");
            exitParam = scn.next().charAt(0);
            System.out.println("\r\n\r\n\r\n");


        } while (exitParam != 'n');
    }

    private static int startMenu() {
        //TODO: Start Menu
        //TODO: Ask User to select game mode ( User vs. User || User vs. Comp.)
        Scanner scn = new Scanner(System.in);
        int userInput = 0;

        System.out.println("#########################################");
        System.out.println("#         Mastermind by DB & AW         #");
        System.out.println("#########################################\r\n");

        System.out.println("Main Menu:");
        System.out.println("Player vs. Player:   (1)");
        System.out.println("Player vs. Computer: (2)");
        System.out.print("Choose Mode: ");
        try {
            userInput = scn.nextInt();
        } catch (Exception e) {
        }

        return userInput;
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
            userColorCode[i] = 1 + (int) (Math.random() * ((6 - 1) + 1));
        }
        return userColorCode;
    }

    private static void checkUserInputAgainstColorCode() {
        //TODO: Check User Input against Color Code

        if (checkIfPositionMatches() == 4) {
            System.out.println("YOU WON");
        } else {
            System.out.println("Correct Colors: " + checkIfColorMatches() + "\t Correct Positions: " + checkIfPositionMatches());
        }

    }


    private static int checkIfColorMatches(int[] masterCode, int[] userAttemptCode) {
        //TODO: Check if a color matches
        ArrayList<Integer> matches = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (userAttemptCode[j] == masterCode[i]) {
                    if (!matches.contains(userAttemptCode[j])) {
                        matches.add(userAttemptCode[j]);
                    }
                }
            }
        }
        return matches.size();
    }

    private static int checkIfPositionMatches(int[] masterCode, int[] userAttemptCode) {
        //TODO: Check if a position matches

        int matches = 0;
        for (int i = 0; i < 4; i++) {
            if (userAttemptCode[i] == masterCode[i]) {
                matches++;
            }
        }
        return matches;
    }

}
