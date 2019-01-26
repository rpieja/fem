package pl.rpieja.fem.datamodel;


import pl.rpieja.fem.localSystem.LocalElement;

import java.util.Vector;

public class CalculationData {

    private final int nH;
    private final int nL;
    private final int nE;
    private final int nN;
    private final int dTau;
    private final int totalTau;

    private final double H;
    private final double L;
    private final double dh;
    private final double dl;
    private final double initialT;
    private final double ambientT;
    private final double alfa;
    private final double specHeat;
    private final double conductCoeff;
    private final double dens;

    public Vector<Double> pGlobal;
    public Vector<Double> pLocal;
    public double [][] hGlobal;
    public double [][] hLocal;

    private LocalElement localElement;

    public CalculationData(
            int nH,
            int nL,
            double h,
            double l,
            int dTau,
            int totalTau,
            double initialT,
            double ambientT,
            double alfa,
            double specHeat,
            double conductCoeff,
            double dens
    ) {
        this.nH = nH;
        this.nL = nL;
        H = h;
        L = l;
        nE = (nH - 1) * (nL - 1);
        nN = nH * nL;
        dh = H / nH;
        dl = L / nL;
        this.dTau = dTau;
        this.totalTau = totalTau;
        this.initialT = initialT;
        this.ambientT = ambientT;
        this.alfa = alfa;
        this.specHeat = specHeat;
        this.conductCoeff = conductCoeff;
        this.dens = dens;

        hGlobal = new double[nN][nN];
        hLocal= new double[4][4];

        pGlobal = new Vector<>();
        pLocal = new Vector<>();

        pGlobal.setSize(nN);
        pLocal.setSize(4);

        localElement = LocalElement.getInstance();

    }

    public int getdTau() {return dTau;}

    public int getTotalTau() { return totalTau; }

    public double getInitialT() { return initialT; }

    public double getAmbientT() { return ambientT; }

    public double getAlfa() { return alfa; }

    public double getSpecHeat() { return specHeat; }

    public double getConductCoeff() { return conductCoeff; }

    public double getDens() { return dens; }

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

    public LocalElement getLocalElement() { return localElement; }


    public void printData() {
        System.out.println("\nH = " + this.H + "\nL = " + this.L + "\nnH = " + this.nH + "\nnL = " + this.nL);
    }
}
