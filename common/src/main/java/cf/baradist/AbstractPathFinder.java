package cf.baradist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.lang.Double.NaN;

public abstract class AbstractPathFinder {
    protected Double[][] matrix;
    protected Set<Integer> visitedIndexes;
    protected Double[] d;
    protected int size;
    protected int from;

    abstract public void findWeights(int from, int to);

    public void readGraphMatrix(String filename) {
        try {
            matrix = Files.lines(Paths.get(filename))
                    .map(line ->
                            Arrays.stream(line.split("\\s+"))
                                    .map(AbstractPathFinder::getDoubleValueOf).toArray(Double[]::new))
                    .toArray(Double[][]::new);
            size = matrix.length;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stack<Edge> findPath(int to) {
        int current = to;
        int currentFrom;
        Stack<Edge> path = new Stack<>();
        while (current != from) {
            currentFrom = findPrev(current);
            path.push(new Edge(currentFrom, current, matrix[currentFrom][current]));
            current = currentFrom;
        }
        return path;
    }

    private int findPrev(int current) {
        for (int i = 0; i < size; i++) {
            if (isRealValue(matrix[i][current]) && visitedIndexes.contains(i)) {
                if (d[current] == d[i] + matrix[i][current]) {
                    return i;
                }
            }
        }
        throw new IllegalArgumentException("Can't find a path");
    }

    public int getSize() {
        return size;
    }

    public void setMatrix(Double[][] matrix) {
        size = matrix.length;
        this.matrix = matrix;
    }

    private static Double getDoubleValueOf(String string) {
        if ("-".equals(string)) {
            return NaN;
        } else if ("∞".equals(string)) {
            return Double.POSITIVE_INFINITY;
        }
        return Double.valueOf(string);
    }

    protected static boolean isRealValue(Double value) {
        return !(value.isNaN() || value.isInfinite());
    }

    protected static String toString(Double[] doubles) {
        return Arrays.stream(doubles)
                .map(AbstractPathFinder::toString)
                .collect(Collectors.joining("\t"));
    }

    private static String toString(Double value) {
        return value == null ? "null" : value.isInfinite() ? "∞" : String.valueOf(value);
    }
}
