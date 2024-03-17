import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * This ADT represents a directed graph data structure with positive edge weights.
 *
 * @param NodeType the data type stored at each graph vertex
 * @param EdgeType the data type stored at each graph edge as a Number whose doubleValue() method returns a value >=0.0
 */
public class FlightTicketGraphBD <NodeType,EdgeType extends Number> implements FlightTicketADT<NodeType,EdgeType>{
	
	 /**
     * Vertex objects group a data field with an adjacency list of weighted
     * directed edges that lead away from them.
     */
    protected class Vertex {
        public NodeType data; // vertex label or application specific data
        public LinkedList<Edge> edgesLeaving;
        public Vertex(NodeType data) {
            this.data = data;
            this.edgesLeaving = new LinkedList<>();
        }
    }
    /**
     * Edge objects are stored within their source vertex, and group together
     * their target destination vertex, along with an integer weight.
     */
    protected class Edge implements ITicket {
        public Vertex target;
        public EdgeType weight;
        
        public NodeType destination;
        public NodeType departure;
        
        public Edge(Vertex target, Vertex source, EdgeType weight) {
            this.target = target;
            this.weight = weight;
            this.destination = target.data;
            this.departure = source.data;
        }

		@Override
		public Double getPrice() {
			return (double)weight;
		}

		@Override
		public String getDestination() {
			
			return destination.toString();
		}

		@Override
		public String getDeparture() {
			
			return departure.toString();
		}
    }

    protected Hashtable<NodeType, Vertex> vertices; // holds graph verticies, key=data
    public FlightTicketGraphBD() { vertices = new Hashtable<>(); }

    
    /**
     * Insert a new vertex into the graph.
     * 
     * @param data the data item stored in the new vertex
     * @return true if the data can be inserted as a new vertex, false if it is already in the graph
     * @throws NullPointerException if data is null
     */
    public boolean insertVertex(NodeType data){
        return true;
    }
    
    /**
     * Remove a vertex from the graph.
     * Also removes all edges adjacent to the vertex from the graph (all edges that have the vertex as a source or a destination vertex).
     * 
     * @param data the data item stored in the vertex to remove
     * @return true if a vertex with *data* has been removed, false if it was not in the graph
     * @throws NullPointerException if data is null
     */
    public boolean removeVertex(NodeType data){
        return true;
    }
    
    /**
     * Insert a new directed edge with a positive edges weight into the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @param weight the weight for the edge (has to be a positive Number)
     * @return true if the edge could be inserted or its weight updated, false if the edge with the same weight was already in the graph with the graph
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph, or weight is < 0
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean insertEdge(NodeType source, NodeType target, EdgeType weight){
        return true;
    }
    
    /**
     * Remove an edge from the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge could be removed, false if it was not in the graph
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean removeEdge(NodeType source, NodeType target){
        return true;
    }
    
    /**
     * Check if the graph contains a vertex with data item *data*.
     * 
     * @param v the data item to check check for
     * @return true if data item is stored in a vertex of the graph, false otherwise
     * @throws NullPointerException if *data* is null
     */
    public boolean containsVertex(NodeType data){
        return true;
    }
    
    /**
     * Check if edge is in the graph.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return true if the edge is in the graph, false if it is not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     */
    public boolean containsEdge(NodeType source, NodeType target){
        return true;
    }
    
    /**
     * Return the weight of an edge.
     * 
     * @param source the data item contained in the source vertex for the edge
     * @param target the data item contained in the target vertex for the edge
     * @return the weight of the edge (0 or positive integer)
     * @throws IllegalArgumentException if either sourceVertex or targetVertex or both are not in the graph
     * @throws NullPointerException if either sourceVertex or targetVertex or both are null
     * @throws NoSuchElementException if edge is not in the graph
     */
    public EdgeType getWeight(NodeType source, NodeType target){
        return null;
    }
    
    /**
     * Returns a list of the shortest paths between startingVertex and destinationVertex.
     * 
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return list of the shortest path's nodes
     */
    public List<ITicket> CheapestPath(NodeType start, NodeType end){
        return null;
    }
    
    /**
     * Returns a list of the least edge paths between startingVertex and destinationVertex.
     * 
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return list of the least edge path's nodes
     */
    public List<ITicket> LeastTransferPath(NodeType start, NodeType end){
        return null;
    }
    
    /**
     * Returns the cost of the path (sum over edge weights) between startingVertex and destinationVertex.
     * Uses Dijkstra's shortest path algorithm to find the shortest path.
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return the cost of the shortest path between vertex with data item startingVertex and vertex with data item destinationVertex, including both startingVertex and destinationVertex
     */
    public double getCheapestPathCost(NodeType start, NodeType end){
        return 0;
    }
    
    /**
     * Returns the cost of the path (sum over edge weights) between startingVertex and destinationVertex.
     * Uses Dijkstra's shortest path algorithm to find the path with Least.
     * 
     * @param start the data item in the starting vertex for the path
     * @param end the data item in the destination vertex for the path
     * @return the cost of the least edges path between vertex with data item startingVertex and vertex with data item destinationVertex, including both startingVertex and destinationVertex
     */
    public double getLeastTransferPathCost(NodeType start, NodeType end){
        return 0;
    }
    
    /**
     * Check if the graph is empty (does not contain any vertices or edges).
     * 
     * @return true if the graph does not contain any vertices or edges, false otherwise
     */
    public boolean isEmpty(){
        return true;
    }
    
    /**
     * Return the number of edges in the graph.
     * 
     * @return the number of edges in the graph
     */
    public int getEdgeCount(){
        return 0;
    }
    
    /**
     * Return the number of vertices in the graph
     * 
     * @return the number of vertices in the graph
     */
    public int getVertexCount(){
        return 0;
    }

}
