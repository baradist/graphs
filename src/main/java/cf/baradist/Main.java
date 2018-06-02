package cf.baradist;

import java.util.Stack;

public class Main {


    public static void main(String[] args) {
        Dijkstra dijkstra = new Dijkstra();
        Double[][] matrix = dijkstra.getGraphMatrix();
        int last = matrix.length - 1;

        dijkstra.findWeights(matrix, 0, last);
        Stack<Edge> path = dijkstra.findPath(last);
        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}
