package pl.rpieja.fem.logic;

import pl.rpieja.fem.localSystem.LocalElement;

import java.util.Vector;

public class Jacobian {

    private static LocalElement LOCAL_ELEMENT = LocalElement.getInstance();
    /**
     * detJ - wyznacznik jakobianu
     * matrixJ - jakobian
     * integrationPoint - ktory punkt calkowania 0 || 1 || 2 || 3
     */
    private int integrationPoint;
    double [][] matrixJ;
    double [][] invertedMatrixJ;
    private double detJ;


    Jacobian(int integrationPoint, Vector<Double> x, Vector<Double> y) {
        this.integrationPoint=integrationPoint;

        matrixJ = new double[2][2]; //macierz jakobiego
        matrixJ[0][0] = LOCAL_ELEMENT.getdNdKsi()[integrationPoint][0] * x.get(0) + LOCAL_ELEMENT.getdNdKsi()[integrationPoint][1] * x.get(1) + LOCAL_ELEMENT.getdNdKsi()[integrationPoint][2] * x.get(2) + LOCAL_ELEMENT.getdNdKsi()[integrationPoint][3] * x.get(3);
        matrixJ[0][1] = LOCAL_ELEMENT.getdNdKsi()[integrationPoint][0] * y.get(0) + LOCAL_ELEMENT.getdNdKsi()[integrationPoint][1] * y.get(1) + LOCAL_ELEMENT.getdNdKsi()[integrationPoint][2] * y.get(2) + LOCAL_ELEMENT.getdNdKsi()[integrationPoint][3] * y.get(3);
        matrixJ[1][0] = LOCAL_ELEMENT.getdNdEta()[integrationPoint][0] * x.get(0) + LOCAL_ELEMENT.getdNdEta()[integrationPoint][1] * x.get(1) + LOCAL_ELEMENT.getdNdEta()[integrationPoint][2] * x.get(2) + LOCAL_ELEMENT.getdNdEta()[integrationPoint][3] * x.get(3);
        matrixJ[1][1] = LOCAL_ELEMENT.getdNdEta()[integrationPoint][0] * y.get(0) + LOCAL_ELEMENT.getdNdEta()[integrationPoint][1] * y.get(1) + LOCAL_ELEMENT.getdNdEta()[integrationPoint][2] * y.get(2) + LOCAL_ELEMENT.getdNdEta()[integrationPoint][3] * y.get(3);

        detJ = matrixJ[0][0] * matrixJ[1][1] - matrixJ[0][1] * matrixJ[1][0]; //liczenie wyznacznika jakobianu

        // odwracanie macierzy (potem potrzebne do równania)
        invertedMatrixJ = new double[2][2];

        invertedMatrixJ[0][0] = matrixJ[1][1];
        invertedMatrixJ[0][1] = -matrixJ[0][1];
        invertedMatrixJ[1][0] = -matrixJ[1][0];
        invertedMatrixJ[1][1] = matrixJ[0][0];
    }

    public double[][] getMatrixJ() {
        return matrixJ;
    }

    public double getDetJ() {
        return detJ;
    }

    public double[][] getInvertedMatrixJ() {
        return invertedMatrixJ;
    }
}
