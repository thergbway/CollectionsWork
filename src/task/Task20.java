package task;

import java.util.Random;
import java.util.Stack;

public class Task20 extends Task {
    private static final String TASK_INFO = "Дана матрица из целых чисел. Найти в ней прямоугольную подмат¬рицу, " +
            "состоящую из максимального количества одинаковых элементов. Использовать класс Stack.\n\n\n";
    private static final int MAX_MATRIX_VALUE = 3;

    public Task20() {
        super("#20", TASK_INFO);
    }

    @Override
    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                appendText("Введите ширину матрицы(>0)\n");
                int matrixWidth = readInt();
                appendText("Введите высоту матрицы(>0)\n");
                int matrixHeight = readInt();

                int[][] matrix = new int[matrixHeight][matrixWidth];
                //randomize matrix
                Random r = new Random();
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        matrix[i][j] = r.nextInt(MAX_MATRIX_VALUE);
                    }
                }

                //show matrix
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {
                        appendText(matrix[i][j] + ", ");
                    }
                    appendText("\n");
                }

                Stack<MatrixDefinition> homogeneousMatrixStack = new Stack<MatrixDefinition>();
                for (int i = 0; i < matrix.length; i++) {
                    for (int j = 0; j < matrix[0].length; j++) {

                        for (int k = i; k < matrix.length; k++) {
                            for (int l = j; l < matrix[0].length; l++) {
                                //(i,j) - left up point
                                // (k,l) - right down point
                                if (isMatrixHomogeneous(matrix, i, j, k, l)) {
                                    homogeneousMatrixStack.push(new MatrixDefinition(i, j, k, l));
                                }


                            }

                        }
                    }
                }

                //pop and find the biggest matrix
                MatrixDefinition mD = null;
                int maxSize = -1;
                while (!homogeneousMatrixStack.isEmpty()) {
                    MatrixDefinition currMD = homogeneousMatrixStack.pop();
                    if (maxSize <= currMD.size()) {
                        mD = currMD;
                        maxSize = currMD.size();
                    }
                }

                //show the biggest homogeneous matrix
                if (mD != null) {
                    appendText("Самая большая матрица(сверху вниз - ось X, слева направо - ось Y):\n");
                    appendText("(" + mD.leftUpXIndex + ", " + mD.leftUpYIndex + ") ---> ("
                            + mD.rightDownXIndex + ", " + mD.rightDownYIndex + ")\n");
                    appendText("Размер: " + maxSize);
                } else
                    appendText("Матрица не была найдена\n");


            }
        }).start();
    }

    private boolean isMatrixHomogeneous(int[][] matrix,
                                        int leftUpXIndex, int leftUpYIndex,
                                        int rightDownXIndex, int rightDownYIndex) {
        int value = matrix[leftUpXIndex][leftUpYIndex];
        for (int i = leftUpXIndex; i <= rightDownXIndex; i++) {
            for (int j = leftUpYIndex; j <= rightDownYIndex; j++) {
                if (value != matrix[i][j])
                    return false;
            }
        }
        return true;
    }

    private class MatrixDefinition {
        public int leftUpXIndex;
        public int leftUpYIndex;
        public int rightDownXIndex;
        public int rightDownYIndex;

        public MatrixDefinition(int leftUpXIndex, int leftUpYIndex, int rightDownXIndex, int rightDownYIndex) {
            this.leftUpXIndex = leftUpXIndex;
            this.leftUpYIndex = leftUpYIndex;
            this.rightDownXIndex = rightDownXIndex;
            this.rightDownYIndex = rightDownYIndex;
        }

        public int size() {
            return (rightDownXIndex - leftUpXIndex + 1) * (rightDownYIndex - leftUpYIndex + 1);
        }
    }
}