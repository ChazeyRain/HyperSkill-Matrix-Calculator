package processor;

public class Main {
    public static void main(String[] args) {
        boolean isRunning = true;

        while (isRunning) {
            switch (ConsoleInput.getCommand()) {
                case 0:
                    isRunning = false;
                    break;
                case 1:
                    System.out.println("Your choice: > 1");
                    add();
                    break;
                case 2:
                    System.out.println("Your choice: > 2");
                    multiplyByConstant();
                    break;
                case 3:
                    System.out.println("Your choice: > 3");
                    multiplyMatrices();
                    break;
                case 4:
                    System.out.println("Your choice: > 4");
                    transposeMatrix();
                    break;
                case 5:
                    System.out.println("Your choice: > 5");
                    findDeterminant();
                    break;
                case 6:
                    System.out.println("Your choice: > 6");
                    findInverse();
                    break;
                default:
                    break;
            }
            System.out.println();
        }
    }

    public static void add() {
        System.out.println("Enter size of first matrix:");
        MatrixProcessor matrix1 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());

        System.out.println("Enter first matrix:");
        matrix1.setElements(ConsoleInput.getElements(matrix1.getRows(), matrix1.getColumns()));

        System.out.println("Enter size of second matrix:");
        MatrixProcessor matrix2 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());

        System.out.println("Enter second matrix:");
        matrix2.setElements(ConsoleInput.getElements(matrix2.getRows(), matrix2.getColumns()));

        try {
            System.out.println("The result is:");
            MatrixProcessor matrix3 = matrix1.add(matrix2);
            System.out.println(matrix3.getStringValue());

        } catch (UnsupportedOperationException e) {
            System.out.println("This matrix doesn't have an inverse.");
        }
        matrix1 = null;
        matrix2 = null;
    }

    public static void multiplyByConstant() {
        System.out.println("Enter size of matrix:");
        MatrixProcessor matrix1 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());

        System.out.println("Enter matrix:");
        matrix1.setElements(ConsoleInput.getElements(matrix1.getRows(), matrix1.getColumns()));

        System.out.println("The result is:");
        System.out.println(matrix1.scalarMultiply(ConsoleInput.getFactor()).getStringValue());
        matrix1 = null;
    }

    public static void multiplyMatrices() {
        System.out.println("Enter size of first matrix:");
        MatrixProcessor matrix1 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());

        System.out.println("Enter first matrix:");
        matrix1.setElements(ConsoleInput.getElements(matrix1.getRows(), matrix1.getColumns()));

        System.out.println("Enter size of second matrix:");
        MatrixProcessor matrix2 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());
        System.out.println("Enter second matrix:");
        matrix2.setElements(ConsoleInput.getElements(matrix2.getRows(), matrix2.getColumns()));

        try {
            System.out.println("The result is:");
            MatrixProcessor matrix3 = matrix1.matrixMultiply(matrix2);
            System.out.println(matrix3.getStringValue());

        } catch (UnsupportedOperationException e) {
            System.out.println("Error: Unsupported operation.");
        }
        matrix1 = null;
        matrix2 = null;
    }

    public static void transposeMatrix() {

        byte mode = (byte) ConsoleInput.getTransposeMode();

        System.out.println("Enter size of matrix:");
        MatrixProcessor matrix1 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());

        System.out.println("Enter matrix:");
        matrix1.setElements(ConsoleInput.getElements(matrix1.getRows(), matrix1.getColumns()));

        MatrixProcessor matrix2;

        switch (mode) {
            case 2:
                matrix2 = matrix1.transposeSide();
                break;
            case 4:
                matrix2 = matrix1.transposeVertical();
                break;
            case 3:
                matrix2 = matrix1.transposeHorizontal();
                break;
            default:
                matrix2 = matrix1.transposeMain();
                break;
        }
        System.out.println(matrix2.getStringValue());
        matrix1 = null;
        matrix2 = null;
    }

    public static void findDeterminant() {
        System.out.println("Enter size of matrix:");
        MatrixProcessor matrix1 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());

        System.out.println("Enter matrix:");
        matrix1.setElements(ConsoleInput.getElements(matrix1.getRows(), matrix1.getColumns()));
        try {
            System.out.println(matrix1.determinant());
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: unsupported operation, You can find determinant only in square matrix");
        }
    }

    public static void findInverse() {
        System.out.println("Enter size of matrix:");
        MatrixProcessor matrix1 = new MatrixProcessor(ConsoleInput.getRows(), ConsoleInput.getColumns());

        System.out.println("Enter matrix:");
        matrix1.setElements(ConsoleInput.getElements(matrix1.getRows(), matrix1.getColumns()));
        try {
            System.out.println(matrix1.inverse().getStringValue());
        } catch (UnsupportedOperationException e) {
            System.out.println("Error: unsupported operation");
        }
    }
}
