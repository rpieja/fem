package pl.rpieja.fem.utils;

import java.util.Vector;

public class GaussElimination {

    public static Vector<Double> calculate(int nN, Vector<Double> globalP, double [][] globalH){
        Vector<Double> resultVector = new Vector<>();
        resultVector.setSize(nN);

        double[][] arrayAB = new double[nN][nN + 1];
        for (int i = 0; i < nN; i++) {
            for (int j = 0; j < nN; j++) {
                arrayAB[j][i] = arrayAB[j][i];
            }
        }

        for (int i = 0; i < nN; i++) {
            arrayAB[i][nN] = globalP.get(i);
        }

        double temp;
        for (int i = 0; i < nN - 1; i++) {
            for (int j = i + 1; j < nN; j++) {
                if (Math.abs(arrayAB[i][i]) < Math.pow(10, -12)) {
                    System.err.println("Błąd! Dzielnik równy 0");
                    break;
                }

                temp = -arrayAB[j][i] / arrayAB[i][i];
                for (int k = 0; k < nN + 1; k++) {
                    arrayAB[j][k] += temp * arrayAB[i][k];
                }
            }
        }

        for (int i = 0; i < nN; i++) {
            resultVector.set(i, 0.0);
        }

        for (int i = nN - 1; i >= 0; i--) {
            temp = arrayAB[i][nN];
            for (int j = nN - 1; j >= 0; j--) {
                temp -= arrayAB[i][j] * resultVector.get(j);
            }
            if (Math.abs(arrayAB[i][i]) < Math.pow(10, -12)) {
                System.err.println("Błąd! Dzielnik równy 0");
                break;
            }
            resultVector.set(i, temp / arrayAB[i][i]);
        }


        return resultVector;
    }


}
