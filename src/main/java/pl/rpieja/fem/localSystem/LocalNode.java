package pl.rpieja.fem.localSystem;

public class LocalNode {
    private final double ksi;
    private final double eta;

    LocalNode(double ksi, double eta) {
        this.ksi = ksi;
        this.eta = eta;
    }

    double getKsi() {
        return ksi;
    }

    double getEta() {
        return eta;
    }
}
