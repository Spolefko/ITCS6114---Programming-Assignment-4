/*
 * 
 */


public class Edge {

	private String target;
	private int weight;
	
	/*
	 * 
	 */
	public Edge(String inTarget, int inWeight) {
		target = inTarget;
		weight = inWeight;
	}
	
	/*
	 * 
	 */
	public String getTarget() {
		return target;
	}
	
	/*
	 * 
	 */
	public int getWeight() {
		return weight;
	}
}
