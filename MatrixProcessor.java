package processor;

public class MatrixProcessor {

    /**
     * THIS IS THE SIMPLE MATRIX PROCESSOR
     *
     * ALL OPERATIONS WILL CREATE NEW OBJECTS, SO THIS IS AN IMMUTABLE TYPE
     *
     * YOU CAN CREATE NEW OBJECT WITH TYPE MatrixProcessor
     *
     * YOU CAN FILL CELLS OF THE MATRIX WITH 2 METHODS:
     *      -setElement(int row, int column, double value) - FILLS ONLY ONE CELL
     *          NOTE: MIN COORDINATES ARE 0 - FOR ROW AND 0 - FOR COLUMN, LIKE IN AN ARRAY
     *
     *      -setElements(double 2D array with same size as MatrixProcessor obj)
     *
     * THIS WILL ALLOW YOU TO DO SOME BASIC OPERATION WITH MATRICES:
     *
     * -ADD / SUBTRACT MATRICES
     *              MatrixProcessor_obj.add/subtract(MatrixProcessor_obj)
     *              return - MatrixProcessor
     *
     * -MULTIPLY MATRIX BY A CONSTANT
     *              MatrixProcessor_obj.scalarMultiply(MatrixProcessor_obj)
     *              return - MatrixProcessor
     *
     * -MULTIPLY MATRIX BY A MATRIX
     *              MatrixProcessor_obj.matrixMultiply(MatrixProcessor_obj)
     *              return - MatrixProcessor
     *
     * -TRANSPOSE MATRIX:
     *        -MAIN DIAGONAL
     *              MatrixProcessor_obj.transposeMain(MatrixProcessor_obj)
     *              return - MatrixProcessor
     *        -SIDE DIAGONAL
     *              MatrixProcessor_obj.transposeMain(MatrixProcessor_obj)
     *              return - MatrixProcessor
     *        -HORIZONTAL
     *              MatrixProcessor_obj.transposeMain(MatrixProcessor_obj)
     *              return - MatrixProcessor
     *        -VERTICAL
     *              MatrixProcessor_obj.transposeMain(MatrixProcessor_obj)
     *              return - MatrixProcessor
     *  - FIND DETERMINANT
     *              MatrixProcessor_obj.determinant()
     *              return - double
     */

    private final int rows;
    private final int columns;
    private double[][] matrix;
    //int[][] matrix;


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public MatrixProcessor(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.matrix = new double[rows][columns];
    }

    /**
     * SET ONE ELEMENT IN THE MATRIX USING:
     *
     * @param row - ROW
     * @param column - COLUMN
     * @param element - NEW VALUE
     */
    public void setElement(int row, int column, double element) {
        matrix[row][column] = element;
    }

    /**
     * SET ELEMENTS FROM AN ARRAY
     * AN ARRAY SIZE MUST BE EQUAL TO THE MATRIX SIZE
     *
     * @param elements - ARRAY OF DOUBLE
     */

    public void setElements(double[][] elements) {
        if (elements.length == this.rows && elements[0].length == this.columns) {
            System.arraycopy(elements, 0, matrix, 0, elements.length);
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public double[][] getElements() {
        return this.matrix;
    }

    public double getElement(int row, int column) {
        return matrix[row][column];
    }

    public String getStringValue() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                output.append(String.format("%.2f ", matrix[i][j]));
            }
            output.append("\n");
        }
        return output.toString();
    }

//    public String getBeautifulString() {
//        String[] stringArray = getStringValue().split(" ");
//        StringBuilder output = new StringBuilder();
//        int length = 0;
//
//        for (String temp : stringArray) {
//            if (temp.length() > length) {
//                length = temp.length();
//            }
//        }
//        System.out.println(stringArray.length);
//        for (int i = 0; i <= this.rows; i++) {
//            for (int j = 0; j < this.columns; j++) {
//                output.append(stringArray[i + j]).append(" ".repeat(length - stringArray[i + j].length()));
//            }
//        }
//        return output.toString();
//    }


    /**
     * ADD OR SUBTRACT MATRICES
     *
     * @param tempMatrix - OBJECT OF MatrixProcessor TYPE
     * @return - OBJECT OF MatrixProcessor TYPE
     */

    public MatrixProcessor add(MatrixProcessor tempMatrix) {
        return addSubtract(tempMatrix, (byte)1);
    }

    public MatrixProcessor subtract(MatrixProcessor tempMatrix) {
        return addSubtract(tempMatrix, (byte) -1);
    }

    private MatrixProcessor addSubtract(MatrixProcessor tempMatrix, byte weight) {

        MatrixProcessor newMatrix = new MatrixProcessor(this.rows, this.columns);

        if(this.rows == tempMatrix.getRows() && this.columns == tempMatrix.getColumns()) {
            for (int i = 0; i < this.rows; i++) {
                for (int j = 0; j < this.columns; j++) {
                    newMatrix.setElement(i, j,
                            getElement(i, j) + weight * tempMatrix.getElement(i, j));
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
        return newMatrix;
    }

    /**
     * MULTIPLY BY A CONSTANT VALUE
     *
     * @param factor - CONSTANT OF TYPE D
     * @return - OBJECT OF MatrixProcessor CLASS
     */

    public MatrixProcessor scalarMultiply(double factor) {

        MatrixProcessor newMatrix = new MatrixProcessor(this.rows, this.columns);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                newMatrix.setElement(i, j, matrix[i][j] * factor);
            }
        }
        return newMatrix;
    }

    /**
     * MULTIPLY MATRIX 'A' BY MATRIX 'B', WHERE:
     * A - THIS OBJECT
     * B - TEMP MATRIX
     * @param tempMatrix - OBJECT OF MatrixProcessor CLASS
     * @return - OBJECT OF MatrixProcessor CLASS
     */

    public MatrixProcessor matrixMultiply(MatrixProcessor tempMatrix) {
        if (this.columns != tempMatrix.getRows()) {
            throw new UnsupportedOperationException();
        }

        MatrixProcessor newMatrix = new MatrixProcessor (this.rows, tempMatrix.getColumns());
        float tempElement;
        for (int i = 0; i < newMatrix.rows; i++) {
            for (int j = 0; j < newMatrix.columns; j++) {
                tempElement = 0;
                for (int k = 0; k < this.columns; k++) {
                    tempElement += matrix[i][k] * tempMatrix.getElement(k, j);
                    newMatrix.setElement(i, j, tempElement);
                }
            }
        }
        return newMatrix;
    }

    /**
     * TRANSPOSE MATRIX
     *        MAIN DIAGONAL
     *        SIDE DIAGONAL
     *        VERTICAL LINE
     *        HORIZONTAL LINE
     *
     * @return - OBJECT OF MatrixProcessor CLASS
     */

    public MatrixProcessor transposeMain() {
        MatrixProcessor newMatrix = new MatrixProcessor(this.columns, this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                newMatrix.setElement(j, i, matrix[i][j]);
            }
        }
        return newMatrix;
    }

    public MatrixProcessor transposeSide() {
        MatrixProcessor newMatrix = new MatrixProcessor(this.columns, this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                newMatrix.setElement(j, this.rows - 1 - i, matrix[i][this.columns - 1 - j]);
            }
        }
        return newMatrix;
    }

    public MatrixProcessor transposeVertical() {
        MatrixProcessor newMatrix = new MatrixProcessor(this.rows, this.columns);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                newMatrix.setElement(i, j, matrix[this.rows - 1 - i][j]);
            }
        }

        return newMatrix;
    }

    public MatrixProcessor transposeHorizontal() {
        MatrixProcessor newMatrix = new MatrixProcessor(this.rows, this.columns);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                newMatrix.setElement(i, j, matrix[i][this.columns - 1 - j]);
            }
        }

        return newMatrix;
    }

    /**
     * DETERMINANT
     *
     * @return - double value
     */

    public final double determinant() {
        double det = 0;
        if (this.rows == this.columns) {
            det = determinantFinder(matrix);
        } else {
            throw new UnsupportedOperationException();
        }
        return det;
    }

    private static double determinantFinder(double[][] matrix) {
        double det = 0;
        if (matrix.length == 1) {
            det = matrix[0][0];
        } else {
            for (int j = 0; j < matrix.length; j++) {
                det += (j % 2 == 0 ? 1 : -1) * determinantFinder(arrayShrink(matrix,0, j)) * matrix[0][j];
            }
        }
        return det;
    }

    private static double[][] arrayShrink(double[][] array, int row, int column) {
        double[][] newArray = new double[array.length - 1][array[0].length - 1];

        byte colWeight;
        byte rowWeight = 0;
        for (int i = 0; i < array.length - 1; i++) {
            colWeight = 0;

            if (i == row) {
                rowWeight = 1;
            }

            for (int j = 0; j < array.length - 1; j++) {
                if (j == column) {
                    colWeight = 1;
                }
                newArray[i][j] = array[i + rowWeight][j + colWeight];
            }
        }
        return newArray;
    }

    /**
     * INVERSE MATRIX
     */
    public final MatrixProcessor inverse() {
        MatrixProcessor newMatrix;

        double det = determinant();

        if (det == 0) {
            throw new UnsupportedOperationException();
        }

        newMatrix = cofactors(MatrixProcessor.this).transposeMain().scalarMultiply(1 / det);

        return newMatrix;
    }

    private MatrixProcessor cofactors(MatrixProcessor matrix) {
        MatrixProcessor newMatrix = new MatrixProcessor(matrix.rows, matrix.columns);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                newMatrix.setElement(i, j, Math.pow(-1, 2 + i + j) * determinantFinder(arrayShrink(matrix.getElements(), i, j)));
            }
        }

        return newMatrix;
    }
}
