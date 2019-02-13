package pl.rpieja.fem.logic;

import pl.rpieja.fem.datamodel.CalculationData;
import pl.rpieja.fem.datamodel.Grid;
import pl.rpieja.fem.datamodel.Node;
import pl.rpieja.fem.utils.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class App {

    public static void main(String[] args) {
        GridCreator gridCreator;
        CalculationData data;
        Grid grid;
        Vector<Double> temperatureVector;
        int step=1;

        DataReader dr = new DataReader();
        String fileName = "data.properties";

        Date date = new Date();
        String strDateFormat = "dd-MM-yyyy-HH-mm-ss";
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        String formattedDate= dateFormat.format(date);
        String logName = "FEM-Results-" + formattedDate+".log";
        File file = new File(logName);
        System.out.println("Logs saved in file: " + logName);

//        Function function = new Function();
//        System.out.println(function.calc(1.0, 1.0));
//        System.out.println(TwoDimIntegral.calculate3P(function));
//        System.out.println(TwoDimIntegral.calculate2P(function));

        try {
            //logger setup
            FileWriter writer = new FileWriter(file, true);
            PrintWriter output = new PrintWriter(writer);

            System.out.println("Loading data from file: " + fileName);
            data = dr.readProps(fileName);
            System.out.print("Calculation parameters loaded succesfully: ");
            //data.printData();

            System.out.println("Generating grid");
            gridCreator = new GridCreator(data);
            grid = gridCreator.createGrid();
            System.out.println("Grid generation complete");

            for (int itau = 0; itau < data.getTotalTau(); itau += data.getdTau()) {

                //compute mes for grid an data
                FemSolver.compute(data, grid);

                //calculate temperatures
                temperatureVector = GaussElimination.calculate(data.getnN(), data.getpGlobal(), data.gethGlobal());

                //set temperatures in grid
                for(int i=0; i<data.getnN(); i++){
                    grid.getNodes().get(i).setTemperature(temperatureVector.get(i));
                }


                //print results
                output.println("Krok: " + step++);
                int counter=0;
                for (int i=0 ; i<data.getnL(); i++)
                    for (int j=0; j<data.getnH();j++){
                        output.printf("%.5f\t", grid.getNodes().get(counter).getTemperature());
                        counter++;
                    }
                output.print("\n");
            }
            output.print("\n");

            output.close();
        } catch (IOException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
            System.exit(-1);
        }


    }
}
