package pl.rpieja.fem.datamodel;

import java.util.List;

public class Grid {

    private List<Element> elements;
    private List<Node> nodes;

    public Grid(List<Element> elements, List<Node> nodes) {
        this.elements = elements;
        this.nodes = nodes;
    }

    public void addElement (Element element){
        elements.add(element);
    }

    public void addNode (Node node) {
        nodes.add(node);
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Node> getNodes() {
        return nodes;
    }
}
