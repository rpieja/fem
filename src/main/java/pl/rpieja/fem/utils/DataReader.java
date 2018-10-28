package pl.rpieja.fem.utils;

import pl.rpieja.fem.datamodel.CalculationData;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DataReader {

    public CalculationData readProps(String fileName) throws IOException {
        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
        Properties props = new Properties();

        props.load(in);

        return new CalculationData(
                Integer.parseInt(props.getProperty("nH")),
                Integer.parseInt(props.getProperty("nL")),
                Double.parseDouble(props.getProperty("H")),
                Double.parseDouble(props.getProperty("L"))
        );

    }
}
