package cf.baradist;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

import static java.lang.Double.NaN;

public class AbstractPathFinder {
    protected Double[][] matrix;
    protected int size;
    protected int from;

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

    protected static String toString(Double value) {
        return value == null ? "null" : value.isInfinite() ? "∞" : String.valueOf(value);
    }
}
