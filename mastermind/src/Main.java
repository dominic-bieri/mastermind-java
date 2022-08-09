import java.util.ArrayList;

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

    private static void promptUserColorCode() {
        //TODO: Prompt user to state custom color code
    }

    private static void generateColorCode() {
        //TODO: Generate random Color Code
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

    private static int checkIfPositionMatches () {
        //TODO: Check if a position matches
        return 0;
    }

}
