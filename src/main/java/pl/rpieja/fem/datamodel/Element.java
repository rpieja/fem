package pl.rpieja.fem.datamodel;

import java.util.List;

public class Element {

    List<Node> nodes;
    List<Integer> IDs;

    public Element(List<Node> nodes, List<Integer> IDs) {
        this.nodes = nodes;
        this.IDs = IDs;
    }

//    public Element(Node[] nodes, Integer[] IDs) {
//
//    }

    public List<Integer> getIDs() {
        return IDs;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
