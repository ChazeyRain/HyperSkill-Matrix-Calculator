package processor;

import java.util.Scanner;

public class ConsoleInput {

    private static final Scanner scanner = new Scanner(System.in);

    public ConsoleInput() {
        //default constructor
    }

    public static int getTransposeMode() {
        System.out.println("1. Main diagonal\n" +
                "2. Side diagonal\n" +
                "3. Horizontal line\n" +
                "4. Vertical line");
        String input = scanner.next();

        if (!input.matches("[1-4]")) {
            System.out.println("ERROR: WRONG OPERATION");
            return getTransposeMode();
        }
        return Integer.parseInt(input);
    }

    public static int getCommand() {
        System.out.println("1. Add matrices\n" +
                "2. Multiply matrix by a constant\n" +
                "3. Multiply matrices\n" +
                "4. Transpose matrix\n" +
                "5. Find determinant\n" +
                "6. Find inverse\n" +
                "0. Exit\n");
        String input = scanner.next();

        if (!input.matches("[0-6]")) {
            System.out.println("ERROR: WRONG OPERATION");
            return getCommand();
        }
        return Integer.parseInt(input);
    }

    public static int getRows() {
        String input = scanner.next();
        if (!input.matches("[0-9]+")) {
            System.out.println("ERROR: ROWS CAN HAVE ONLY NUMERIC VALUE");
            return getRows();
        }
        return Integer.parseInt(input);
    }

    public static int getColumns() {
        String input = scanner.next();
        if (!input.matches("[0-9]+")) {
            System.out.println("ERROR: COLUMNS CAN HAVE ONLY NUMERIC VALUE");
            return getColumns();
        }
        return Integer.parseInt(input);
    }

    public static double[][] getElements(int rows, int columns) {
        double[][] inputArray = new double[rows][columns];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                inputArray[i][j] = scanner.nextDouble();
            }
        }
        return inputArray;
    }

    public static double getFactor() {
        System.out.println("Enter the constant:");
        String input = scanner.next();

        if (!input.matches("[0-9.,]+")) {
            System.out.println("ERROR: NON DOUBLE VALUE");
            return getFactor();
        }
        return Double.parseDouble(input);
    }
}
