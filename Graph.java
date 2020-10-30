import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Graph 
{
	// the list of vertices
	private ArrayList<Vertex> vertices;
	
	// the list of edges
	private ArrayList<Edge> edges;
	
	// the number of vertices
	private int V;
	
	// the constructor
	public Graph()
	{
		// create the lists
		vertices = new ArrayList<>();
		edges = new ArrayList<>();
	}
	
	// the method to get this vertex
	private Vertex getVertex(int v)
	{
		// for each of the vertex
		for( Vertex vertex : vertices )
		{
			// if this is the match
			if( vertex.getV() == v )
			{
				// return this 
				return vertex;
			}
		}
		
		// return null
		return null;
	}
	
	// the method to read the file
	public boolean readFile(String filename)
	{
		// try to open the file
		try
		{
			// open the scanner
			Scanner scanner = new Scanner(new File(filename));
			
			// read the number of vertices
			V = scanner.nextInt();
			// skip the change in line
			scanner.nextLine();
			
			// as long as there are lines
			while( scanner.hasNextLine() )
			{
				// read this line
				String line = scanner.nextLine();
				
				// get the tokens
				String tokens[] = line.split(" ");
				
				// get the vertices
				int u = Integer.parseInt(tokens[0]);
				int v = Integer.parseInt(tokens[1]);
				
				// get the weight
				int w = Integer.parseInt(tokens[2]);
				
				// add vertices
				if( !vertices.contains(new Vertex(u)))
					vertices.add(new Vertex(u));
				if( !vertices.contains(new Vertex(v)))
					vertices.add(new Vertex(v));
				
				// add the neighbors
				getVertex(u).add(v, w);
				getVertex(v).add(u, w);
				
				// add edge
				edges.add(new Edge(getVertex(u), getVertex(v), w));
			}
			
			// close the scanner
			scanner.close();
			
			// return true
			return true;
		}
		// catch exception
		catch(FileNotFoundException e)
		{
			// show message
			System.out.println("Cannot open file " + filename);
			
			// return false
			return false;
		}
	}
	
	// the method to check if all nodes visited
	private boolean allVisited(boolean[] visited)
	{
		// for each of the entry
		for( boolean b : visited )
		{
			// if this is not visited
			if( !b )
			{
				// return false
				return false;
			}
		}
		// finally return true
		return true;
	}
	
	// find the lowest unvisited node
	private void getUnvisitedNode(boolean[] visited, ArrayList<Edge> T)
	{
		// get the lowest edge such that one is visited and other is not
		
		// for each of the edges
		for( Edge e : edges ) 
		{
			// if one is visited and other is not
			if( visited[e.getU().getV()] && !visited[e.getV().getV()])
			{
				// add this to the MST
				T.add(e);
				
				// mark j as visited
				visited[e.getV().getV()] = true;
				
				// stop
				return;
			}
			// else try the other way round
			else if( !visited[e.getU().getV()] && visited[e.getV().getV()])
			{
				// add this to the MST
				T.add(e);
				
				// mark j as visited
				visited[e.getU().getV()] = true;
				
				// stop
				return;
			} 
		}
	}
	
	// the prims algorithm
	public void prim(boolean flag)
	{
		// get the starting time
		long start = System.currentTimeMillis();
				
		// add all the egdes to map
		boolean[] visited = new boolean[V];
		
		// set 0 to visited
		visited[0] = true;
		
		// sort the list of edges
		Collections.sort(edges);
		
		// the mst
		ArrayList<Edge> T = new ArrayList<>();		
		
		// as long as not all visited
		while( !allVisited(visited) )
		{
			// get the unvisited
			getUnvisitedNode(visited, T);
		}
		
		// get the end time
		long end = System.currentTimeMillis();
		
		// find the total cost
		int totalCost = 0;
		for( Edge e : T )
			totalCost += e.getWeight();
		
		// print the total cost
		System.out.println("Total cost of MST with prim algorithm is : " + totalCost);
		
		// print the time taken
		System.out.println("Time taken : " + (end - start) + " ms");
		
		// if flag
		if( flag )
		{
			// show message
			System.out.println("The edges of the MST with prim are : ");
			// print the edges
			for( Edge e : T )
				System.out.println(e);
			
			// change line
			System.out.println();
		}
	}
	
	// the method to get the component
	private ArrayList<Vertex> getComponent(ArrayList<ArrayList<Vertex>> components, Vertex v)
	{
		// for each of the component
		for( ArrayList<Vertex> component : components )
		{
			// if this vertex is in this component
			if( component.contains(v))
			{
				// return this
				return component;
			}
		}
		
		// return null
		return null;
	}
	
	// the kruskal
	public void kruskal(boolean flag)
	{
		// create a copy of edges
		ArrayList<Edge> edges = new ArrayList<>(this.edges);
		
		// get the start
		long start = System.currentTimeMillis();
		
		// sort edges
		Collections.sort(edges);
		
		// the list to store components
		ArrayList< ArrayList<Vertex> > components = new ArrayList<>();
		
		// the mst
		ArrayList<Edge> T = new ArrayList<>();
		
		// initially all the components are on its own
		for( Vertex v : vertices )
		{
			// create an array component to hold this vertex
			ArrayList<Vertex> component = new ArrayList<>();
			
			// add the vertex
			component.add(v);
			
			// add this to components
			components.add(component);
		}
		
		// while number of components are greater than 2
		while( components.size() >= 2 )
		{
			// remove the smallest edge
			Edge e = edges.remove(0);
			
			// get the component of either edges
			ArrayList<Vertex> componentU = getComponent(components, e.getU());
			ArrayList<Vertex> componentV = getComponent(components, e.getV());
			
			// if these are the not the same
			if( componentU != componentV )
			{
				// add this edge to MST
				T.add(e);
				
				// add all the vertices of v to u
				for( Vertex v : componentV )
					componentU.add(v);
				
				// remove v from the list
				components.remove(componentV);
			}
		}
		
		// get the end
		long end = System.currentTimeMillis();
		
		//  find the total cost
		int totalCost = 0;
		for( Edge e : T )
			totalCost += e.getWeight();
		
		// print the total cost
		System.out.println("Total cost of MST with kruskal algorithm is : " + totalCost);
		
		// print the time taken
		System.out.println("Time taken : " + (end - start) + " ms");
		
		// if flag
		if( flag )
		{
			// show message
			System.out.println("The edges of the MST with kruskal are : ");
			// print the edges
			for( Edge e : T )
				System.out.println(e);
			
			// change line
			System.out.println();
		}
	}
}
