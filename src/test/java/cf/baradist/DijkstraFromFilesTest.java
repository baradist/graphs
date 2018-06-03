package cf.baradist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

@RunWith(Parameterized.class)
public class DijkstraFromFilesTest {
    private Dijkstra dijkstra;

    @Parameterized.Parameter
    public String filename;

    @Parameterized.Parameter(1)
    public int from;

    @Parameterized.Parameter(2)
    public int to;

    @Parameterized.Parameters(name = "{index}: from {1} to {2}, {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"dijkxtra-example.txt", 0, 5},
                {"dijkstra_01.txt", 0, 5},
                {"dijkstra_02.txt", 0, 5},
                {"dijkstra_12.txt", 0, 5},
                {"dijkstra_18.txt", 0, 5},
        });
    }

    @Before
    public void setUp() {
        dijkstra = new Dijkstra();
    }

    @Test
    public void test() {
        dijkstra.readGraphMatrix("src/test/resources/" + filename);

        dijkstra.findWeights(from, to);
        Stack<Edge> path = dijkstra.findPath(to);

        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}
