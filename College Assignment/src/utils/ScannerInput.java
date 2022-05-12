package utils;

import java.util.Scanner;

public class ScannerInput {

    public static int readNextInt(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Integer.parseInt(input.next());
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a number please.");
            }
        } while (true);
    }

    public static double readNextDouble(String prompt) {
        Scanner input = new Scanner(System.in);
        do {
            try {
                System.out.print(prompt);
                return Double.parseDouble(input.next());
            } catch (NumberFormatException e) {
                System.err.println("\tEnter a decimal number please.");
            }
        } while (true);
    }

    public static String readNextLine(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.nextLine();
    }

    public static char readNextChar(String prompt) {
        Scanner input = new Scanner(System.in);
        System.out.print(prompt);
        return input.next().charAt(0);
    }

}
