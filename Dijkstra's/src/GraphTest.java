import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests the implementation of CS400Graph for the individual component of
 * Project Three: the implementation of Dijsktra's Shortest Path algorithm.
 */
public class GraphTest {

    private CS400Graph<String,Integer> graph;

    /**
     * Instantiate graph.
     */
    @BeforeEach
    public void createGraph() {
        graph = new CS400Graph<>();
        // insert vertices A-F
        graph.insertVertex("A");
        graph.insertVertex("B");
        graph.insertVertex("C");
        graph.insertVertex("D");
        graph.insertVertex("E");
        graph.insertVertex("F");
        // insert edges
        graph.insertEdge("A","B",6);
        graph.insertEdge("A","C",2);
        graph.insertEdge("A","D",5);
        graph.insertEdge("B","E",1);
        graph.insertEdge("B","C",2);
        graph.insertEdge("C","B",3);
        graph.insertEdge("C","F",1);
        graph.insertEdge("D","E",3);
        graph.insertEdge("E","A",4);
        graph.insertEdge("F","A",1);
        graph.insertEdge("F","D",1);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to F.
     */
    @Test
    public void testPathCostAtoF() {
        assertTrue(graph.getPathCost("A", "F") == 3);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to D.
     */
    @Test
    public void testPathCostAtoD() {
        assertTrue(graph.getPathCost("A", "D") == 4);
    }

    /**
     * Checks the distance/total weight cost from the vertex A to E.
     */
    @Test
    public void testPathCostAtoE() {
        // a - b - e = 7
        // a - c - b - e = 6 use this path
        assertTrue(graph.getPathCost("A", "E") == 6);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * A to D.
     */
    @Test
    public void testPathAtoD() {
        System.out.println(graph.getPathCost("A", "D"));
        System.out.println(graph.shortestPath("A", "D").toString());
        assertTrue(graph.shortestPath("A", "D").toString().equals(
                "[A, C, F, D]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * A to F.
     */
    @Test
    public void testPathAtoF() {
        System.out.println(graph.getPathCost("A", "F"));
        System.out.println(graph.shortestPath("A", "F").toString());
        assertTrue(graph.shortestPath("A", "F").toString().equals(
                "[A, C, F]"
        ));
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * A to E.
     */
    @Test
    public void testPathAtoE() {
        assertTrue(graph.shortestPath("A", "E").toString().equals(
                "[A, C, B, E]"
        ));
    }

    /**
     * Checks the distance/total weight cost from the vertex B to F.
     */
    @Test
    public void testPathCostBtoF() {
        // b - c - f = 3
        assertTrue(graph.getPathCost("B", "F") == 3);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * B to F.
     */
    @Test
    public void testPathBtoF() {
        // b - c - f = 3
        assertTrue(graph.shortestPath("B", "F").toString().equals(
                "[B, C, F]"
        ));
    }

    /**
     * Checks the distance/total weight cost from the vertex F to A.
     */
    @Test
    public void testPathCostFtoA() {
        // f - a = 1
        assertTrue(graph.getPathCost("F", "A") == 1);
    }

    /**
     * Checks the ordered sequence of data within vertices from the vertex
     * F to A.
     */
    @Test
    public void testPathFtoA() {
        // f - a = 1
        assertTrue(graph.shortestPath("F", "A").toString().equals(
                "[F, A]"
        ));
    }
}
