/*
* 
* 
*/

import java.util.ArrayList;

public class Vertex {
	private String id;
	private ArrayList<Edge> adjList = new ArrayList<Edge>();
	private int shortestPathEstimate = Integer.MAX_VALUE;
	private String predecessor = null;
	
	/*
	 * 
	 */
	public Vertex(String inId) {
		id = inId;
	}
	
	/*
	 * 
	 */
	public void addEdge(String target, int weight) {
		adjList.add(new Edge(target, weight));
	}
	
	/*
	 * 
	 */
	public void setShortestPathEstimate(int estimate) {
		shortestPathEstimate = estimate;
	}
	
	/*
	 * 
	 */
	public void setPredecessor(String inPredecessor) {
		predecessor = inPredecessor;
	}
	
	/*
	 * 
	 */
	public boolean edgeExists (String target) {
		boolean exists = false;
		
		
		
		return exists;
	}
	
	/*
	 * 
	 */
	public int getEdgeWeight(String target) {
		int edgeWeight = Integer.MAX_VALUE;
		for (int i = 0; i < adjList.size(); i++) {
			if (adjList.get(i).getTarget().equals(target)) {
				edgeWeight = (adjList.get(i).getWeight());
				break;
			}
		}
		return edgeWeight;
	}
	
	/*
	 * 
	 */
	public int getShortestPathEstimate() {
		return shortestPathEstimate;
	}
	
	/*
	 * 
	 */
	public String getPredecessor() {
		return predecessor;
	}
	
	/*
	 * 
	 */
	public ArrayList<Edge> getEdgeList(){
		return adjList;
	}
	
	/*
	 * 
	 */
	public String toStringEdgeList() {
		String result = "";
		
		for (int i = 0; i < (adjList.size()); i++) {
			result += ("(" + id + "->" + adjList.get(i).getTarget() + ")[weight:" + adjList.get(i).getWeight() + "], ");
		}
		
		return result;
	}
}
