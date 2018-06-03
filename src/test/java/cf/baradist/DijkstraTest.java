package cf.baradist;

import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static cf.baradist.Dijkstra.INF;
import static java.lang.Double.NaN;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DijkstraTest {

    private Dijkstra dijkstra;
    private Double[][] matrix;

    @Before
    public void setUp() throws Exception {
        dijkstra = new Dijkstra();
        matrix = new Double[6][];
        matrix[0] = new Double[]{NaN, 9., INF, 6., 11., INF};
        matrix[1] = new Double[]{INF, NaN, 8., INF, INF, INF};
        matrix[2] = new Double[]{INF, INF, NaN, INF, 6., 9.};
        matrix[3] = new Double[]{INF, 5., 7., NaN, 6., INF};
        matrix[4] = new Double[]{INF, 6., INF, INF, NaN, 4.};
        matrix[5] = new Double[]{INF, INF, INF, INF, INF, NaN};
    }

    @Test
    public void test() {
        dijkstra.findWeights(matrix, 0, 5);
        Stack<Edge> path = dijkstra.findPath(5);
        assertThat(path.size(), is(2));

        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}
