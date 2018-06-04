package cf.baradist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Stack;

@RunWith(Parameterized.class)
public class BellmanMooreFromFilesTest {
    private BellmanMoore bellmanMoore;

    @Parameterized.Parameter
    public String filename;

    @Parameterized.Parameter(1)
    public int from;

    @Parameterized.Parameter(2)
    public int to;

    @Parameterized.Parameters(name = "{index}: from {1} to {2}, {0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"bellman-moore-example.txt", 0, 5},
        });
    }

    @Before
    public void setUp() {
        bellmanMoore = new BellmanMoore();
    }

    @Test
    public void test() {
        bellmanMoore.readGraphMatrix("src/test/resources/" + filename);

        bellmanMoore.findWeights(from, to);
        Stack<Edge> path = bellmanMoore.findPath(to);

        while (!path.empty()) {
            System.out.println(path.pop());
        }
    }
}