package cf.baradist;

import java.util.HashSet;
import java.util.Set;

public class Dijkstra {
    private Set<Integer> visitedIndexes;
    private int size;
    private Double[] d;

    public void findWeights(Double[][] matrix, int from, int to) {
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
            System.out.println(current + "\t" + d[0] + "\t" + d[1] + "\t" + d[2] + "\t" + d[3] + "\t" + d[4] + "\t" + d[5]);
            current = nextTheClosestIndex();
            visitedIndexes.add(current);
        }
        System.out.println("The distance from " + from + " to " + to + " is: " + d[to]);
    }

    private void updateWeightsToNeighbors(int current, Double[] currentLine) {
        for (int i = 0; i < size; i++) {
            if (currentLine[i].isNaN() || currentLine[i].isInfinite()
                    || i == current
                    || visitedIndexes.contains(i)) {
                continue;
            }
            d[i] = Math.min(d[i], d[current] + currentLine[i]);
        }
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
            throw new IllegalArgumentException("Can't find min value in " + d);
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
}
