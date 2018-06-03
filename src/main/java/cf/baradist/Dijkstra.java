package cf.baradist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import static java.lang.Double.NaN;

public class Dijkstra {
    private Set<Integer> visitedIndexes;
    private Double[][] matrix;
    private int size;
    private Double[] d;
    private int from;

    public Double[][] readGraphMatrix(String filename) {
        try {
            matrix = Files.lines(Paths.get(filename))
                    .map(line -> {
                        String[] strings = line.split("\\s+");
                        return Arrays.stream(strings)
                                .map(Dijkstra::getDoubleValueOf).toArray(Double[]::new);
                    }).toArray(Double[][]::new);
            return matrix;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void findWeights(int from, int to) {
        this.from = from;
        size = matrix.length;
        d = new Double[size];
        for (int i = 0; i < d.length; i++) {
            d[i] = Double.POSITIVE_INFINITY;
        }
        visitedIndexes = new HashSet<>(size);
        d[from] = 0.;

        int current = 0;
        visitedIndexes.add(current);
        while (current != to) {
            Double[] currentLine = matrix[current];
            updateWeightsToNeighbors(current, currentLine);
            System.out.println(current + "\t" + toString(d));
            current = nextTheClosestIndex();
            visitedIndexes.add(current);
        }
        System.out.println("The distance from " + from + " to " + to + " is: " + d[to]);
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

    public int getSize() {
        return size;
    }

    private static Double getDoubleValueOf(String string) {
        if ("-".equals(string)) {
            return NaN;
        } else if ("∞".equals(string)) {
            return Double.POSITIVE_INFINITY;
        }
        return Double.valueOf(string);
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

    private void updateWeightsToNeighbors(int current, Double[] currentLine) {
        for (int i = 0; i < size; i++) {
            if (!isRealValue(currentLine[i]) || i == current
                    || visitedIndexes.contains(i)) {
                continue;
            }
            d[i] = Math.min(d[i], d[current] + currentLine[i]);
        }
    }

    private boolean isRealValue(Double value) {
        return !(value.isNaN() || value.isInfinite());
    }

    private int nextTheClosestIndex() {
        int minIndex;
        Double min = null;
        for (minIndex = 0; minIndex < d.length; minIndex++) {
            if (!visitedIndexes.contains(minIndex)) {
                min = d[minIndex];
                break;
            }
        }
        if (min == null) {
            throw new IllegalArgumentException("Can't find min value in " + toString(d));
        }
        for (int i = minIndex + 1; i < d.length; i++) {
            if (visitedIndexes.contains(i)) {
                continue;
            }
            if (d[i] < min) {
                minIndex = i;
                min = d[i];
            }
        }
        return minIndex;
    }

    private String toString(Double[] doubles) {
        return Arrays.stream(doubles)
                .map(Dijkstra::toString)
                .collect(Collectors.joining("\t"));
    }

    private static String toString(Double value) {
        return value == null ? "null" : value.isInfinite() ? "∞" : String.valueOf(value);
    }
}
