import javax.sound.midi.Soundbank;
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

        ArrayList<int[]> colorCodes = new ArrayList<int[]>();
        int[] masterCode = new int[4];


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

            System.out.println("\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n\r\n");
            System.out.println("The Color has been set!");
            System.out.println("Now it is your Turn! Enter your Code!\r\n");

            for (attempts = 0; attempts < 12; attempts++) {

                System.out.println("You have " + (12 - attempts) + " left!");
                //Print old attempts
                if (attempts > 0) {
                    System.out.println("Your old attempts!");
                    for (int[] colorCode : colorCodes) {
                        System.out.println(Arrays.toString(colorCode));
                    }
                }

                //Get new attempt
                colorCodes.add(promptUserColorCode());

                //Check against Master Code
                int[] checkRes = checkColorCode(masterCode, colorCodes.get(colorCodes.size() - 1));

                if (checkRes[0] == 4) {
                    System.out.println("#########################################");
                    System.out.println("#           You are Megamind!           #");
                    System.out.println("#########################################\r\n");
                    return;
                } else {
                    System.out.println("Correct Colors: " + checkRes[0] + "\t Correct Positions: " + checkRes[1]);
                }

            }

            System.out.println("#########################################");
            System.out.println("#               You lost!               #");
            System.out.println("#########################################\r\n");

            //Exit
            System.out.println("Play again? (y/n)");
            exitParam = scn.next().charAt(0);
            System.out.println("\r\n\r\n\r\n");


        } while (exitParam != 'n');
    }

    private static int startMenu() {

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

    private static int[] checkColorCode(int[] masterCode, int[] userAttemptCode) {

        int[] tmpMasterCode = masterCode.clone();
        int[] tmpUserAttemptCode = userAttemptCode.clone();
        int[] codeRes = new int[2];

        for (int i = 0; i < 4; i++) {
            if (tmpMasterCode[i] == tmpUserAttemptCode[i]) {
                codeRes[0]++;
                tmpMasterCode[i] = 0;
                tmpUserAttemptCode[i] = 0;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tmpMasterCode[i] == tmpUserAttemptCode[j] && tmpMasterCode[i] != 0) {
                    codeRes[1]++;
                    tmpMasterCode[i] = 0;
                    tmpUserAttemptCode[j] = 0;
                    break;
                }
            }
        }

        return codeRes;
    }

    private static int[] promptUserColorCode() {
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
        int[] userColorCode = new int[4];
        for (int i = 0; i < 4; i++) {
            userColorCode[i] = (int) (Math.random() * 6 + 1);
        }
        return userColorCode;
    }

    private static int colorToCode(String colorName) {
        switch (colorName) {
            case "blue":
                return 1;
            case "red":
                return 2;
            case "green":
                return 3;
            case "yellow":
                return 4;
            case "white":
                return 5;
            case "black":
                return 6;
            default:
                return 0;
        }
    }
}
