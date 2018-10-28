package pl.rpieja.fem.datamodel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class CalculationData {

    private final int nH, nL, nE, nN;
    private final double H, L, dh, dl;

    public CalculationData(int nH, int nL, double h, double l) {
        this.nH = nH;
        this.nL = nL;
        H = h;
        L = l;
        nE = (nH - 1) * (nL - 1);
        nN = nH * nL;
        dh=H/nH;
        dl=L/nL;
    }

    public double getDh() {
        return dh;
    }

    public double getDl() {
        return dl;
    }

    public int getnE() {
        return nE;
    }

    public int getnN() {
        return nN;
    }

    public int getnH() {
        return nH;
    }

    public int getnL() {
        return nL;
    }

    public double getH() {
        return H;
    }

    public double getL() {
        return L;
    }

    public void printData() {
        System.out.println("\nH = " + this.H + "\nL = " + this.L + "\nnH = " + this.nH + "\nnL = " + this.nL);
    }
}
