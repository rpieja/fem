package pl.rpieja.fem.logic;

import pl.rpieja.fem.datamodel.CalculationData;
import pl.rpieja.fem.datamodel.Grid;
import pl.rpieja.fem.utils.DataReader;
import pl.rpieja.fem.utils.GridCreator;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.io.IOException;

public class App {

    public static void main(String[] args) {
        DataReader dr = new DataReader();
        GridCreator gridCreator;
        CalculationData data;
        Grid grid;
        String fileName = "data.properties";
        try {
            System.out.println("Loading data from file: " + fileName);
            data = dr.readProps(fileName);
            System.out.print("Calculation parameters loaded succesfully: ");
            data.printData();

            System.out.println("Generating grid:");
            gridCreator = new GridCreator(data);
            grid=gridCreator.createGrid();
            System.out.println("Grid generated successfully: ");
            grid.printGrid();




        } catch (IOException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
            System.exit(-1);
        }


    }
}
