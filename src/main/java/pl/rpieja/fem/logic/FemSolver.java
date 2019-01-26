package pl.rpieja.fem.logic;

import pl.rpieja.fem.datamodel.CalculationData;
import pl.rpieja.fem.datamodel.Grid;

import java.util.Vector;

import static java.lang.Math.abs;

public class FemSolver {

    /**
     *
     * @param data
     * @param grid
     *
     *
     */
    public void compute(CalculationData data, Grid grid){

        for (int i = 0; i < data.getnN(); i++) {
            for (int j = 0; j < data.getnN(); j++) {
                data.hGlobal[i][j] = 0.0;
            }
            data.pGlobal.set(i, 0.0);
        }

        double interpolatedT0;
        double matrixCElement;
        int id;
        double detJ;

        Jacobian jacobian;
        Vector<Double> dNdX = new Vector<>();
        Vector<Double> dNdY = new Vector<>();
        Vector<Double> x = new Vector<>();
        Vector<Double> y = new Vector<>();
        Vector<Double> temp0 = new Vector<>();

        dNdX.setSize(4);
        dNdY.setSize(4);
        x.setSize(4);
        y.setSize(4);
        temp0.setSize(4);

        for(int elementIterator = 0; elementIterator<data.getnE(); elementIterator++) {
            for(int i =0; i<4;i++) {
                for (int j = 0; j < 4; j++)
                    data.hLocal[i][j] = 0;
                data.pLocal.set(i, 0.);
            }

            for (int i = 0 ; i<4 ; i++){
                id = grid.getElements().get(elementIterator).getIDs().get(i);
                x.set(i, grid.getNodes().get(id).getX());
                y.set(i, grid.getNodes().get(id).getY());
                temp0.set(i, grid.getNodes().get(id).getTemperature());
            }

            for (int integrationPoints = 0 ; integrationPoints<4;integrationPoints++){
                jacobian=new Jacobian(integrationPoints, x, y);
                interpolatedT0 = 0;

                for (int i=0; i<4 ; i++){
                    dNdX.set(i, (1.0 / jacobian.getDetJ() * (jacobian.getInvertedMatrixJ()[0][0] * data.getLocalElement().getdNdKsi()[integrationPoints][i] + jacobian.getInvertedMatrixJ()[0][1] * data.getLocalElement().getdNdEta()[integrationPoints][i])));
                    dNdY.set(i, (1.0 / jacobian.getDetJ() * (jacobian.getInvertedMatrixJ()[1][0] * data.getLocalElement().getdNdKsi()[integrationPoints][i] + jacobian.getInvertedMatrixJ()[1][1] * data.getLocalElement().getdNdEta()[integrationPoints][i])));

                    interpolatedT0 += temp0.get(i) * data.getLocalElement().getMatrixN()[integrationPoints][i];
                }
                detJ = abs(jacobian.getDetJ());
                for (int i = 0; i < 4 ;i++){
                    for( int j = 0 ; j<4;j++){
                        matrixCElement = data.getSpecHeat() * data.getDens() * data.getLocalElement().getMatrixN()[integrationPoints][i]* data.getLocalElement().getMatrixN()[integrationPoints][j] * detJ;
                        data.hLocal[i][j] =+ data.getConductCoeff() * (dNdX.get(i) * dNdX.get(j) * dNdY.get(i)* dNdY.get(j)) * detJ + matrixCElement / data.getdTau();
                        data.pLocal.set(i, data.pLocal.get(i) + matrixCElement/data.getdTau()*interpolatedT0);
                    }
                }
            }

        //  WARUNKI BRZEGOWE


        }


    }
}
