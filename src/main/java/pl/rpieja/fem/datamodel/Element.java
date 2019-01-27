package pl.rpieja.fem.datamodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Element {

    List<Node> nodes;
    List<Integer> IDs;
    List<Integer> borderSurfacesIds;
    int borderNodes = 0;
    Vector<Edge> edges;

    public Element(List<Node> nodes, List<Integer> IDs) {
        this.nodes = nodes;
        this.IDs = IDs;

        borderSurfacesIds=new ArrayList<>();
        edges = new Vector<>();
        edges.setSize(4);

        edges.set(0, new Edge(nodes.get(3), nodes.get(0)));
        edges.set(1, new Edge(nodes.get(0), nodes.get(1)));
        edges.set(2, new Edge(nodes.get(1), nodes.get(2)));
        edges.set(3, new Edge(nodes.get(2), nodes.get(3)));

        for(Edge edge: edges)
            if(edge.getEdge()[0].isStatus() && edge.getEdge()[1].isStatus())
                borderNodes++;

        for (int i = 0 ; i<4 ; i++)
            if(edges.get(i).getEdge()[0].isStatus() && edges.get(i).getEdge()[1].isStatus())
                borderSurfacesIds.add(i);

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
