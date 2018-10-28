package pl.rpieja.fem.utils;

public class TwoDimIntegral {

    public static final Double[] INTEGRAL_POINTS_2P = {-(1/Math.sqrt(3)), (1/Math.sqrt(3))};
    public static final Double[] WEIGHS_2P = {1.0, 1.0};

    public static final Double[] INTEGRAL_POINTS_3P = {-0.77, 0.0 , 0.77};
    public static final Double[] WEIGHS_3P = {(5.0/9.0), (8.0/9.0), (5.0/9.0)};

    public static Double calculate3P(Function function) {
        Double result = 0.0;
        for( int i = 0; i<3;i++)
            for (int j = 0 ; j<3; j++){
                result += function.calc(INTEGRAL_POINTS_3P[i], INTEGRAL_POINTS_3P[j])* WEIGHS_3P[i]* WEIGHS_3P[j];
            }

        return result;
    }


    public static Double calculate2P(Function function) {
        Double result = 0.0;

        for( int i = 0; i<INTEGRAL_POINTS_2P.length;i++)
            for (int j = 0 ; j<INTEGRAL_POINTS_2P.length; j++){
                result += function.calc(INTEGRAL_POINTS_2P[i], INTEGRAL_POINTS_2P[j])* WEIGHS_2P[i]* WEIGHS_2P[j];
            }

        return result;
    }

}
