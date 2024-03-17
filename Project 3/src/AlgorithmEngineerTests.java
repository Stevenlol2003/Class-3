import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AlgorithmEngineerTests {

	 private FlightTicketGraph<String,Integer> graph;
	 
	 /**
	     * Instantiate graph.
	     */
	    @BeforeEach
	    public void createGraph() {
	        graph = new FlightTicketGraph<>();
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
	    public void testCheapestPathCostAtoF() {
	        assertTrue(graph.getCheapestPathCost("A", "F") == 3);
	    }
	    
	    /**
	     * Checks the distance/total weight cost from the vertex A to D.
	     */
	    @Test
	    public void testCheapestPathCostAtoB() {
	    	
	        assertTrue(graph.getCheapestPathCost("A", "B") == 5);
	    }
	    /**
	     * Checks the distance/total weight cost from the vertex A to D.
	     */
	    @Test
	    public void testCheapestPathCostAtoD() {
	    	
	        assertTrue(graph.getCheapestPathCost("A", "D") == 4);
	    }
	    
	    /**
	     * Checks the distance/total weight cost from the vertex A to F when choose the least edge path.
	     */
	    @Test
	    public void testLeastEdgePathCostAtoF() {
	    	
	        assertTrue(graph.getLeastTransferPathCost("A", "F") == 3);
	    }
	    /**
	     * Checks the distance/total weight cost from the vertex A to B when choose the least edge path.
	     */
	    @Test	
	    public void testLeastEdgePathCostAtoB() {
	    	 
	        assertTrue(graph.getLeastTransferPathCost("A", "B") == 6);
	    }
	    
	    /**
	     * Checks the distance/total weight cost from the vertex A to B when choose the least edge path.
	     */
	    @Test	
	    public void testLeastEdgePathCostAtoD() {
	    	 
	        assertTrue(graph.getLeastTransferPathCost("A", "D") == 5);
	    }
	    /**
	     * Checks the ordered sequence of the shortest path data within vertices from the vertex 
	     * A to F.
	     */
	    @Test
	    public void testCheapestPathAtoF() {
	        assertTrue(graph.CheapestPath("A", "F").toString().equals(
	            "[(A,C), (C,F)]"
	        ));
	    }
	    /**
	     * Checks the ordered sequence of the shortest path data within vertices from the vertex 
	     * A to B.
	     */
	    @Test
	    public void testCheapestPathAtoB() {
	        assertTrue(graph.CheapestPath("A", "B").toString().equals(
	            "[(A,C), (C,B)]"
	        ));
	    }
	    /**
	     * Checks the ordered sequence of the shortest path data within vertices from the vertex 
	     * A to D.
	     */
	    @Test
	    public void testCheapestPathAtoD() {
	        assertTrue(graph.CheapestPath("A", "D").toString().equals(
	        		"[(A,C), (C,F), (F,D)]"
	        ));
	    }
	    /**
	     * Checks the ordered sequence of the least edge path data within vertices from the vertex 
	     * A to F.
	     */
	    @Test
	    public void testLeastEdgePathAtoF() {
	        assertTrue(graph.LeastTransferPath("A", "F").toString().equals(
	            "[(A,C), (C,F)]"
	        ));
	    }
	    
	    /**
	     * Checks the ordered sequence of the least edge path data within vertices from the vertex 
	     * A to F.
	     */
	    @Test
	    public void testLeastEdgePathAtoB() {
	        assertTrue(graph.LeastTransferPath("A", "B").toString().equals(
	            "[(A,B)]"
	        ));
	    }
	    
	    /**
	     * Checks the ordered sequence of the least edge path data within vertices from the vertex 
	     * A to D.
	     */
	    @Test
	    public void testLeastEdgePathAtoD() {
	        assertTrue(graph.LeastTransferPath("A", "D").toString().equals(
	            "[(A,D)]"
	        ));
	    }
	    
	    
}
