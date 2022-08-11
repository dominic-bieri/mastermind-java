import javax.sound.midi.Soundbank;
import java.lang.reflect.Array;
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
        Scanner scn = new Scanner(System.in);

        ArrayList<int[]> colorCodeAttempts = new ArrayList<int[]>();
        int[] masterCode = new int[4];

        char exitParam = 'y';
        int[] checkResult = new int[2];
        int attempt = 0;

        do {
            int menuInput = startMenu();

            switch (menuInput) {
                case 1 -> promptUserColorCode(masterCode);
                case 2 -> generateColorCode(masterCode);
                default -> System.out.println("=> Error with menu option!");
            }

            System.out.println("\r\n".repeat(12));
            System.out.println("The master color code has been set!");
            System.out.println("Now it is your Turn! Enter your Code!\r\n");

            for (attempts = 0; attempts < 12; attempts++) {
                if ((12 - attempts) != 1){
                    System.out.println("You have " + (12 - attempts) + " Attempts left!");
                }
                else {
                    System.out.println("You have 1 Attempt left!");
                }
                //Print old attempts
                if (attempts > 0) {
                    if (attempts != 1){
                        System.out.println("Your old attempts!");
                    }
                    else {
                        System.out.println("Your old attempt!");
                    }
                    for (int[] colorCode : colorCodes) {
                        System.out.println(Arrays.toString(colorCode));
                    }
                }

                //Get new attempt
                colorCodeAttempts.add(new int[4]);
                int errorCode = promptUserColorCode(colorCodeAttempts.get(colorCodeAttempts.size() - 1));

                if (errorCode == -1)
                    return;

                //Check against Master Code
                checkColorCode(masterCode, colorCodeAttempts.get(colorCodeAttempts.size() - 1), checkResult);

                if (checkResult[0] == 4) {
                    System.out.println("#########################################");
                    System.out.println("#           You are Megamind!           #");
                    System.out.println("#########################################\r\n");
                    return;
                } else {
                    System.out.println("Correct Positions: " + checkResult[0] + "\t Correct Colors: " + checkResult[1]);
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

    private static int checkColorCode(int[] masterCode, int[] userAttemptCode, int[] checkResult) {

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

        return 0;
    }

    private static int promptUserColorCode(int[] userColorCode) {

        Scanner scanner = new Scanner(System.in);
        int errorCode = 0;

        System.out.println("1 = Blue | 2 = Red | 3 = Green | 4 = Yellow | 5 = White | 6 = Black");
        for (int i = 0; i < 4; i++) {
            System.out.print("Insert your " + (i + 1) + ". color: ");
            String usrInput = scanner.next();
            int intUsrInput = 0;

            try {
                intUsrInput = Integer.parseInt(usrInput);
            } catch (NumberFormatException e) {
                intUsrInput = colorToCode(usrInput);
            }

            if (intUsrInput < 1 && intUsrInput > 6) {
                errorCode = -1;
                break;
            }
            userColorCode[i] = intUsrInput;
        }
        return errorCode;
    }

    private static int generateColorCode(int[] genColorCode) {
        for (int i = 0; i < 4; i++) {
            genColorCode[i] = (int) (Math.random() * 6 + 1);
        }
        return 0;
    }

    private static int colorToCode(String colorName) {

        colorName = colorName.toLowerCase().trim();

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
