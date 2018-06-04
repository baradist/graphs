package cf.baradist;

import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        BellmanMoore bellmanMoore = new BellmanMoore();
        String filename = args[0];
        int from = Integer.valueOf(args[1]);
        int to = Integer.valueOf(args[2]);

        bellmanMoore.readGraphMatrix(filename);

        bellmanMoore.findWeights(from, to);
        Stack<Edge> path = bellmanMoore.findPath(to);
        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}
