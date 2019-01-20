package pl.rpieja.fem.localSystem;

public class LocalElement {
    private static LocalElement LOCAL_ELEMENT = null;

    private LocalArea[] gaussIntegrationAreaPoints;

    private final double dNdKsi[][] = new double[4][4];
    private final double dNdEta[][] = new double[4][4];
    



    public static LocalElement getInstance() {
        return LOCAL_ELEMENT;
    }

    private LocalElement() {

    }
}
