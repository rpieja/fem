package pl.rpieja.fem.localSystem;

import pl.rpieja.fem.datamodel.ShapeFunction;

import static java.lang.Math.sqrt;

public class LocalElement {

    private final double P1 = -1.0/sqrt(3.0);
    private final double P2 = 1.0/sqrt(3.0);

    /**
     * Singleton zawierający wszystkie punkty całkowania dla elementu lokalnego i wartości pochodnych
     *
     * LOCAL_ELEMENT - singleton instance
     * gaussIntegrationAreaPoints - punkty całkowania do nakładania warunków brzegowych (te zozłożone na brzegach)
     * gaussIntegrationPoints - punkty całkowania w elemencie
     *
     * dNdKsi - pochodna f. kształtu po ksi
     * dNdEta - pochodna f. kształtu po eta
     *
     * matrixN - wartości f. kształtu (dla każdego z 4 pkt po 4 f. kształtu)
     */

    private static LocalElement LOCAL_ELEMENT = null;

    private final double dNdKsi[][] = new double[4][4];
    private final double dNdEta[][] = new double[4][4];

    private final double matrixN[][] = new double[4][4];

    private LocalElement() {

        LocalNode[] gaussIntegrationPoints = {
                new LocalNode(P1, P1),
                new LocalNode(P2, P1),
                new LocalNode(P2, P2),
                new LocalNode(P1, P2)
        };

        LocalArea[] gaussIntegrationAreaPoints = {
                new LocalArea(new LocalNode(-1., P2), new LocalNode(-1., P1)),
                new LocalArea(new LocalNode(P1, -1.), new LocalNode(P2, -1.)),
                new LocalArea(new LocalNode(1., P1), new LocalNode(1., P2)),
                new LocalArea(new LocalNode(P2, 1.), new LocalNode(P1, 1.))
        };

        /**
         * wypełnianie macierzy pochodnymi funkcji kształtu i wartościami
         */
        for (int i = 0 ; i<4 ; i++) {

            matrixN[i][0] = ShapeFunction.N1(gaussIntegrationPoints[i].getKsi(), gaussIntegrationPoints[i].getEta());
            matrixN[i][1] = ShapeFunction.N2(gaussIntegrationPoints[i].getKsi(), gaussIntegrationPoints[i].getEta());
            matrixN[i][2] = ShapeFunction.N3(gaussIntegrationPoints[i].getKsi(), gaussIntegrationPoints[i].getEta());
            matrixN[i][3] = ShapeFunction.N4(gaussIntegrationPoints[i].getKsi(), gaussIntegrationPoints[i].getEta());

            dNdKsi[i][0] = ShapeFunction.dN1dKsi(gaussIntegrationPoints[i].getEta());
            dNdKsi[i][1] = ShapeFunction.dN2dKsi(gaussIntegrationPoints[i].getEta());
            dNdKsi[i][2] = ShapeFunction.dN3dKsi(gaussIntegrationPoints[i].getEta());
            dNdKsi[i][3] = ShapeFunction.dN4dKsi(gaussIntegrationPoints[i].getEta());

            dNdEta[i][0] = ShapeFunction.dN1dEta(gaussIntegrationPoints[i].getKsi());
            dNdEta[i][1] = ShapeFunction.dN2dEta(gaussIntegrationPoints[i].getKsi());
            dNdEta[i][2] = ShapeFunction.dN3dEta(gaussIntegrationPoints[i].getKsi());
            dNdEta[i][3] = ShapeFunction.dN4dEta(gaussIntegrationPoints[i].getKsi());

        }

        for (int i = 0 ; i<4 ; i++) {
            for (int j = 0 ; j<2 ; j++) {
                gaussIntegrationAreaPoints[i].node[j][0] = ShapeFunction.N1(gaussIntegrationAreaPoints[i].localNode.get(j).getKsi(), gaussIntegrationAreaPoints[i].localNode.get(j).getEta());
                gaussIntegrationAreaPoints[i].node[j][1] = ShapeFunction.N2(gaussIntegrationAreaPoints[i].localNode.get(j).getKsi(), gaussIntegrationAreaPoints[i].localNode.get(j).getEta());
                gaussIntegrationAreaPoints[i].node[j][2] = ShapeFunction.N3(gaussIntegrationAreaPoints[i].localNode.get(j).getKsi(), gaussIntegrationAreaPoints[i].localNode.get(j).getEta());
                gaussIntegrationAreaPoints[i].node[j][3] = ShapeFunction.N4(gaussIntegrationAreaPoints[i].localNode.get(j).getKsi(), gaussIntegrationAreaPoints[i].localNode.get(j).getEta());
            }
        }
    }

    public static LocalElement getInstance() {
        if (LOCAL_ELEMENT == null)
            LOCAL_ELEMENT = new LocalElement();
        return LOCAL_ELEMENT;
    }

    public double[][] getdNdKsi() { return dNdKsi; }

    public double[][] getdNdEta() { return dNdEta; }

    public double[][] getMatrixN() { return matrixN; }
}
