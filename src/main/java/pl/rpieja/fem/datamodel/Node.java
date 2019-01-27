package pl.rpieja.fem.datamodel;

public class Node {

    private final int nodeId;
    private double x, y, temperature;
    private final boolean status;

    public Node(int nodeId, double x, double y, double temperature, CalculationData data) {
        this.nodeId = nodeId;
        this.x = x;
        this.y = y;
        this.temperature = temperature;

        this.status = this.x == 0.0 || this.y == 0.0 || this.x == data.getH() || this.y == data.getL();
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

    public boolean isStatus() { return status; }
}
