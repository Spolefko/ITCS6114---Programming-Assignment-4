/*
 *  
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Graph {
	private ArrayList<Vertex> vertexList = new ArrayList<Vertex>();
	
	/*
	 * Represents when bellman-Ford algorithm finds a negative weight cycle
	 */
	public static final int NEG_CYCLE_EXISTS = -2; 
	
	/*
	 * 
	 */
	public Graph(String rootPath, String inputFileName) {
		Path inputFile = Paths.get(rootPath + File.separator + inputFileName);
		//Try to open file (Try with resources will automatically close file)
		try (BufferedReader reader = Files.newBufferedReader(inputFile)) {
			String line = null;
			String lineTemp = null;
			int edgeSource = 0;
			String edgeTarget =  null;
			int edgeWeight = 0;
				
			//Read each line, remove whitespace, and take out all node and edge info
			while ((line = reader.readLine()) != null) {
		    	line = line.trim();
		    	if (line.length() < 4) 
		    		continue;
		    	
		    	lineTemp = line.substring(0, 4);		    	
		    	if(lineTemp.equals("node")) {
		    		reader.readLine();
		    		line = reader.readLine(); //get id
		    		line = line.trim();
		    		vertexList.add(new Vertex(line.substring(3, line.length())));
		    		reader.readLine();
		    	} 
		    	else if(lineTemp.equals("edge")) {
		    		reader.readLine(); //skip bracket
		    		
		    		line = reader.readLine(); //get source
		    		line = line.trim();
		    		edgeSource = Integer.parseInt(line.substring(7, line.length()));
		    		
		    		line = reader.readLine(); //get target
		    		line = line.trim();
		    		edgeTarget = line.substring(7, line.length());
		    		
		    		line = reader.readLine(); //get weight
		    		line = line.trim();
		    		edgeWeight = Integer.parseInt(line.substring(7, line.length()));
		    		
		    		vertexList.get(edgeSource).addEdge(edgeTarget, edgeWeight);
		    	}
		    }		    
		}catch (IOException caughtException) {
			System.err.println("IOException occurred: " + caughtException.toString());
		}
	}
	
	/*
	 * 
	 */
	public long dijkstra(String source) {
		long performance = 0;
		
		
		return performance;
	}
	
	/*
	 * 
	 */
	public long bellmanFord(String source) {
		long startPerformance = System.currentTimeMillis();
		String predecessor = null;
		String current = null;
		String path = "";
		ArrayList<Edge> adjList = null;
		int s = Integer.parseInt(source);
		initialize(s);
		
		for (int v = 0; v < (vertexList.size()-1); v++) { //repeat |G.V|-1 times
			//Go through every vertex's adj list to go through every edge
			for (int i = 0; i < (vertexList.size()-1); i++) { 
				adjList = vertexList.get(i).getEdgeList();
				for(int j = 0; j < adjList.size(); j++) {
					relax(Integer.toString(i), adjList.get(j).getTarget());
				}
			}
		}
		
		//Verify that no negative cycle exists
		int adjVertexIndex;
		int tWeight;
		int sWeight;
		int stWeight;
		String target;
		for (int i = 0; i < (vertexList.size()-1); i++) { 
			adjList = vertexList.get(i).getEdgeList();
			
			sWeight = vertexList.get(i).getShortestPathEstimate();
			if (sWeight == Integer.MAX_VALUE)
				continue; //No path exists to verify
			
			for(int j = 0; j < adjList.size(); j++) {
				//get target shortest path weight
				target = adjList.get(j).getTarget();
				adjVertexIndex = Integer.parseInt(((target)));
				tWeight = vertexList.get(adjVertexIndex).getShortestPathEstimate(); 
				
				stWeight = sWeight + vertexList.get(i).getEdgeWeight(target);

				if (tWeight > stWeight)
					return NEG_CYCLE_EXISTS;
			}
		}
		
		
		
		//print bellman-ford results
		for (int i = 0; i < vertexList.size(); i++) {
			predecessor = vertexList.get(i).getPredecessor();
			current = Integer.toString(i);
			
			if (predecessor == null) {
				System.out.println("No path exists from " + source + " to " + i);
				continue;
			}
			
			System.out.println("Weight of path from " + source + " to vertex " + i + 
					": " + vertexList.get(i).getShortestPathEstimate());
			
			path = "";
			while (predecessor != null) {
				path = "(" + predecessor + "->" + current + ")" + path;
				current = predecessor;
				predecessor = vertexList.get(Integer.parseInt(predecessor)).getPredecessor();
			}
			System.out.println("Path from " + source + " to vertex " + i + ": " + path);
		}
		
		return (System.currentTimeMillis() - startPerformance);
	}
	
	/*
	 * 
	 */
	public void relax(String source, String target) {
		int s = Integer.parseInt(source);
		int t = Integer.parseInt(target);
		int tWeight = vertexList.get(t).getShortestPathEstimate(); //Current path weight estimate of the target
		int stWeight = (vertexList.get(s).getShortestPathEstimate());
		
		if (stWeight != Integer.MAX_VALUE) { //If the shortest path is current inf, no point in relaxing
			stWeight += vertexList.get(s).getEdgeWeight(target); //weight of source plus the edge connecting source to target
		
			if(tWeight > stWeight) {
				vertexList.get(t).setShortestPathEstimate(stWeight);
				vertexList.get(t).setPredecessor(source);
			}
		}
	}
	
	/*
	 * 
	 */
	public void initialize(int source) {
		for (int i = 0; i < (vertexList.size()-1); i++) {
			vertexList.get(i).setPredecessor(null);
			vertexList.get(i).setShortestPathEstimate(Integer.MAX_VALUE);
		}
		
		vertexList.get(source).setShortestPathEstimate(0);
	}
	
	/*
	 * 
	 */
	public void print() {
		System.out.println("The vertex set of the graph is: ");
		for (int i = 0; i < vertexList.size(); i++) {
			System.out.println("The source vertex is: " +  i);
			System.out.println("Output edge set is: "); 
			System.out.println(vertexList.get(i).toStringEdgeList() + "\n");
		}
	}
	
}
