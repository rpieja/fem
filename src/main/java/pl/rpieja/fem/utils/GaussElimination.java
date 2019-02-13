package pl.rpieja.fem.utils;

import java.util.Vector;

public class GaussElimination {

    public static Vector<Double> calculate(int nN, Vector<Double> globalP, double [][] globalH){
        Vector<Double> result = new Vector<Double>();
        result.setSize(nN);
        for (int i = 0 ; i < nN ; i++) {
            result.set(i, 0.0);
        }



        return result;
    }


}
