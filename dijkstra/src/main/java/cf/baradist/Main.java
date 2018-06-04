package cf.baradist;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        String filename = args[0];
        int from = Integer.valueOf(args[1]);
        int to = Integer.valueOf(args[2]);

        dijkstra.readGraphMatrix(filename);

        dijkstra.findWeights(from, to);
        Stack<Edge> path = dijkstra.findPath(to);
        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}
