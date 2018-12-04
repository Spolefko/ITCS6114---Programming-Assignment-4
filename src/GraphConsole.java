/*
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class GraphConsole {
	/**
     * path of current file
     */
    public static final String ROOT_PATH = System.getProperty("user.dir");

    /**
     * the name of gml File
     */
    private static final String GML_FILE_NAME = "celegansneural.gml";
    /**
     * dijkstra algorithm
     */
    public static final String DIJKSTRA_ALGORITHM = "dijkstra";
    /**
     * bellman ford algorithm
     */
    public static final String BELLMANFORD_ALGORITHM = "bellmanford";
    /**
     * quit command
     */
    public static final String QUIT = "quit";
    /**
     * graph command
     */
    public static final String GRAPH = "graph";
    /**
     * print command
     */
    public static final String PRINT = "print";
    /**
     * performance command
     */
    public static final String PERFORMANCE = "performance";

	
	public static void main(String[] args) {
		String source;
        System.out.println("please input your command");
        Scanner inputCmd = new Scanner(System.in);
        String query = inputCmd.nextLine();
        long performanceDijkstra = -1;
        long performanceBellman = -1;
        Graph graph = null;
        
        while (!QUIT.equals(query)) {
            if (query != null) {
                switch (query) {
                    case GRAPH:
                        graph = new Graph(ROOT_PATH, GML_FILE_NAME);
                        break;
                    case PRINT:
                        if (graph == null) {
                            System.out.println("please generate the graph first");
                        } else {
                        	graph.print();
                        }
                        break;
                    case DIJKSTRA_ALGORITHM:
                        System.out.println("Please enter source vertex");
                        source = inputCmd.nextLine();
                    	performanceDijkstra = graph.dijkstra(source);
                        break;
                    case BELLMANFORD_ALGORITHM:
                    	System.out.println("Please enter source vertex");
                        source = inputCmd.nextLine();
                    	performanceBellman = graph.bellmanFord(source);
                        break;
                    case PERFORMANCE:
                        if (graph == null) {
                            System.out.println("Please generate the graph first");
                        } 
                        else if (performanceDijkstra < 0 && performanceBellman < 0) {
                        	System.out.println("Please run a graph algorithm first");
                        }
                        else {
                        	if(performanceDijkstra >= 0)
                        		System.out.println("Dijkstra performance time: " + performanceDijkstra + "ms");
                        	if(performanceBellman >= 0) 
                        		System.out.println("Bellman performance time: " + performanceBellman + "ms");
                        }
                        break;
                    default:
                        System.out.println("the input command should be one the commands: graph, print, performance, quit, dijkstra, bellmanford");
                        break;
                }
            }
            System.out.println("please input your command");

            query = inputCmd.nextLine();
        }
        inputCmd.close();
    
		
	}
	
	

}
