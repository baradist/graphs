package cf.baradist;

import java.util.Stack;

import static java.lang.Double.NaN;

public class Main {
    private static final double INF = Double.POSITIVE_INFINITY;

    public static void main(String[] args) {
        int size = 6;
        Double[][] matrix = new Double[size][];
        matrix[0] = new Double[]{NaN, 9., INF, 6., 11., INF};
        matrix[1] = new Double[]{INF, NaN, 8., INF, INF, INF};
        matrix[2] = new Double[]{INF, INF, NaN, INF, 6., 9.};
        matrix[3] = new Double[]{INF, 5., 7., NaN, 6., INF};
        matrix[4] = new Double[]{INF, 6., INF, INF, NaN, 4.};
        matrix[5] = new Double[]{INF, INF, INF, INF, INF, NaN};

        Dijkstra dijkstra = new Dijkstra();
        dijkstra.findWeights(matrix, 0, 5);
        Stack<Edge> path = dijkstra.findPath(5);
        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}
