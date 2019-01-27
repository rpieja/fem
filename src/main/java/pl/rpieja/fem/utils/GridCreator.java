package pl.rpieja.fem.utils;

import pl.rpieja.fem.datamodel.CalculationData;
import pl.rpieja.fem.datamodel.Element;
import pl.rpieja.fem.datamodel.Grid;
import pl.rpieja.fem.datamodel.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GridCreator {

    CalculationData data;
    List<Node> nodes;
    List<Element> elements;


    public GridCreator(CalculationData data) {
        this.data = data;
    }

    public Grid createGrid() {

        elements = new ArrayList<>(data.getnE());
        nodes=new ArrayList<>(data.getnN());

        generateNodes(nodes);
        generateElements(elements, nodes);

        printNodes();

        printElements();

        return new Grid(elements, nodes);
    }

    void generateNodes(List<Node> nodes) {
        int counter=0;
        for (int i = 0 ; i<data.getnL();i++)
            for(int j = 0; j<data.getnH();j++){
                counter++;
                double x = i*data.getDl();
                double y = j*data.getDh();
                nodes.add(new Node(counter, x, y, 0, data));
            }
    }


    void printNodes(){
        for (Node node:
             nodes) {
            System.out.println(node.getNodeId()+"\t");
        }

    }
    void generateElements(List<Element> elements, List<Node> nodes) {
        for (int i = 0 ; i<data.getnL()-1;i++)
            for(int j = 0; j<data.getnH()-1;j++){
                Integer [] tab = new Integer[4];

                tab[0] = j + i * data.getnH();
                tab[3] = tab[0] + 1;
                tab[1] = j + (i+1) * data.getnH();
                tab[2] = tab[1] + 1;

                Node [] nodesTab = new Node[4];
                int z = 0;
                for(int nodeId: tab){
                    nodesTab[z] = nodes.get(nodeId);
                    z++;
                }

                elements.add(new Element(Arrays.asList(nodesTab), Arrays.asList(tab)));
            }
    }

    void printElements(){
        for (Element e :
                elements) {

            for(Integer id:e.getIDs()){
                System.out.printf("%s, ", id.intValue());
            }
            System.out.println("");
        }
    }
}
