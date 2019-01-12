package pl.rpieja.fem.logic;

import pl.rpieja.fem.datamodel.CalculationData;
import pl.rpieja.fem.datamodel.Grid;
import pl.rpieja.fem.utils.DataReader;
import pl.rpieja.fem.utils.Function;
import pl.rpieja.fem.utils.GridCreator;
import pl.rpieja.fem.utils.TwoDimIntegral;

import java.io.IOException;

public class App {

    public static void main(String[] args) {
        DataReader dr = new DataReader();
        GridCreator gridCreator;
        CalculationData data;
        Grid grid;
        Function function = new Function();
        System.out.println(function.calc(1.0, 1.0));
        System.out.println(TwoDimIntegral.calculate3P(function));
        System.out.println(TwoDimIntegral.calculate2P(function));
        String fileName = "data.properties";
        try {
            System.out.println("Loading data from file: " + fileName);
            data = dr.readProps(fileName);
            System.out.print("Calculation parameters loaded succesfully: ");
            data.printData();

            System.out.println("Generating grid:");
            gridCreator = new GridCreator(data);
            grid = gridCreator.createGrid();
            System.out.println("Grid generated successfully: ");

            for (int itau = 0; itau < data.getTotalTau(); itau += data.getdTau()) {

                //compute mes for grid an data



                //set temperatures in grid and print

            }

        } catch (IOException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
            System.exit(-1);
        }


    }
}
