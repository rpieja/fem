package pl.rpieja.fem.datamodel;

public class Node {

    private final int nodeId;
    private double x, y, temperature;

    public Node(int nodeId, double x, double y, double temperature) {
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
        this.temperature = temperature;
    }

    public int getNodeId() {
        return nodeId;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }
}
