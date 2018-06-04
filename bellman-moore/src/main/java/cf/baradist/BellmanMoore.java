package cf.baradist;

import java.util.HashSet;
import java.util.LinkedList;

public class BellmanMoore extends AbstractPathFinder {
    private LinkedList<Integer> queue;

    @Override
    public void findWeights(int from, int to) {
        this.from = from;
        d = new Double[size];
        queue = new LinkedList<>();

        // step 1
        for (int i = 0; i < d.length; i++) {
            d[i] = Double.POSITIVE_INFINITY;
        }
        visitedIndexes = new HashSet<>(size);
        d[from] = 0.;
        queue.push(from);

        int current = from;
        visitedIndexes.add(current);
        while (!queue.isEmpty()) {
            current = queue.poll(); // first
            Double[] currentLine = matrix[current];
            updateWeightsToNeighbors(current, currentLine);
            System.out.println(current + "\t" + toString(d));
        }

        System.out.println("The distance from " + from + " to " + to + " is: " + d[to]);
    }

    private void updateWeightsToNeighbors(int current, Double[] currentLine) {
        for (int i = 0; i < size; i++) {
            if (!isRealValue(currentLine[i]) || current == i) {
                continue;
            }
            double newValue = d[current] + currentLine[i];
            if (newValue < d[i]) {
                correctQueue(i);
                d[i] = Math.min(d[i], newValue);
            }
        }
    }

    private void correctQueue(int i) {
        if (!visitedIndexes.contains(i)) {
            // if vertex wasn't visited, put it to the end of the queue
            queue.addLast(i);
        } else {
            // else move
            if (queue.contains(i)) {
                queue.remove(i);

            }
            // or just add to the beginning
            queue.addFirst(i);
        }
        visitedIndexes.add(i);
    }
}
