import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        char exitParam;

        do {
            //Start game!
            mastermind();

            //Exit
            System.out.print("Play again? (y/n): ");
            exitParam = scn.next().charAt(0);
            System.out.println("\r\n\r\n\r\n");


        } while (exitParam != 'n');
    }

    private static void mastermind() {

        // Allowed Colors:
        /*
            Blue    = 1
            Red     = 2
            Green   = 3
            Yellow  = 4
            white   = 5
            black   = 6
         */

        ArrayList<int[]> colorCodeAttempts = new ArrayList<>();
        int[] masterCode = new int[4];
        int[] checkResult = new int[2];

        int errorCode = 0;
        int attempt = 0;


        int menuInput = startMenu();

        switch (menuInput) {
            case 1 -> {
                errorCode = promptUserColorCode(masterCode);
                if (errorCode != 0) {
                    System.out.println("=> Error with input!");
                    return;
                }
            }
            case 2 -> generateColorCode(masterCode);
            default -> {
                System.out.println("=> Error with menu option!");
                return;
            }
        }

        System.out.println("\r\n".repeat(12));
        System.out.println("The master color code has been set!");
        System.out.println("Now it is your Turn! Enter your Code!\r\n");

        for (attempt = 0; attempt < 12; attempt++) {
            if ((12 - attempt) != 1) {
                System.out.println("You have " + (12 - attempt) + " Attempts left!");
            } else {
                System.out.println("You have 1 attempt left!");
            }
            //Print old attempts
            if (attempt > 0) {
                if (attempt != 1) {
                    System.out.println("Your previous attempts!");
                } else {
                    System.out.println("Your previous attempt!");
                }
                for (int[] colorCode : colorCodeAttempts) {
                    System.out.println(Arrays.toString(colorCode));
                }
            }

            //Get new color code form User
            colorCodeAttempts.add(new int[4]);
            errorCode = promptUserColorCode(colorCodeAttempts.get(colorCodeAttempts.size() - 1));

            if (errorCode != 0) {
                System.out.println("=> Error with input!");
                return;
            }

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

    }

    private static int startMenu() {

        Scanner scn = new Scanner(System.in);
        String usrInput;
        int intUsrInput = -1;

        System.out.println("#########################################");
        System.out.println("#         Mastermind by DB & AW         #");
        System.out.println("#########################################\r\n");

        System.out.println("Main Menu:");
        System.out.println("Player vs. Player:   (1)");
        System.out.println("Player vs. Computer: (2)");
        System.out.print("Choose Mode: ");

        usrInput = scn.next();

        try {
            intUsrInput = Integer.parseInt(usrInput);
        } catch (NumberFormatException e) {
            intUsrInput = -1;
        }

        return intUsrInput;
    }

    private static int checkColorCode(int[] masterCode, int[] userAttemptCode, int[] checkResult) {

        int[] tmpMasterCode = masterCode.clone();
        int[] tmpUserAttemptCode = userAttemptCode.clone();
        checkResult[0] = 0;
        checkResult[1] = 0;

        // Match Position
        for (int i = 0; i < 4; i++) {
            if (tmpMasterCode[i] == tmpUserAttemptCode[i]) {
                checkResult[0]++;
                tmpMasterCode[i] = 0;
                tmpUserAttemptCode[i] = 0;
            }
        }

        // Match Color
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (tmpMasterCode[i] == tmpUserAttemptCode[j] && tmpMasterCode[i] != 0) {
                    checkResult[1]++;
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

            if (intUsrInput < 1 || intUsrInput > 6) {
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

        return switch (colorName) {
            case "blue" -> 1;
            case "red" -> 2;
            case "green" -> 3;
            case "yellow" -> 4;
            case "white" -> 5;
            case "black" -> 6;
            default -> -1;
        };
    }
}
