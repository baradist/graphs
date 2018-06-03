package cf.baradist;

import java.util.Stack;

public class Main {


    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.readGraphMatrix("src/test/resources/dijkxtra-example.txt");

        int last = dijkstra.getSize();
        dijkstra.findWeights(0, last);
        Stack<Edge> path = dijkstra.findPath(last);
        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}
