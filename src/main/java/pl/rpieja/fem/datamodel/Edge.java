package pl.rpieja.fem.datamodel;

public class Edge {

    private final Node[] edge;
    private double [][] shapeFunctionVals;

    public Edge(Node node1, Node node2) {
        this.edge = new Node[2];
        this.edge[0] = node1;
        this.edge[1] = node2;
    }

    public void setShapeFunctionVals(double[][] shapeFunctionVals) {
        this.shapeFunctionVals = shapeFunctionVals;
    }

    public double[][] getShapeFunctionVals() {
        return shapeFunctionVals;
    }

    public Node[] getEdge() {
        return edge;
    }
    
}
