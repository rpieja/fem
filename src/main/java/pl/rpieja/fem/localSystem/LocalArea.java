package pl.rpieja.fem.localSystem;

import java.util.Vector;

public class LocalArea {
    public final double node[][]; // tablica funkcji kształtu
    final Vector<LocalNode> localNode;

    LocalArea(LocalNode node1, LocalNode node2) {
        localNode = new Vector<>();
        localNode.add(node1);
        localNode.add(node2);

        node = new double[2][4]; // 2 punkty calkowania, 4 funkcje ksztaltu
    }
}
