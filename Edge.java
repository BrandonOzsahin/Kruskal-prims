public class Edge implements Comparable<Edge>
{
	// the two vertices for the edge
	private Vertex u;
	private Vertex v;
	private int weight;
	
	// the constructor
	public Edge(Vertex u, Vertex v, int weight)
	{
		// set the vertices
		this.u = u;
		this.v = v;
		this.weight = weight;
	}

	// implements compareTop()
	@Override
	public int compareTo(Edge o) 
	{
		// return the comparison of the weights
		return weight - o.weight; 
	}

	// getter for U
	public Vertex getU() 
	{
		return u;
	}

	// getter for V
	public Vertex getV()
	{
		return v;
	}

	// getter for weight
	public int getWeight() 
	{
		return weight;
	}
	
	// getter for string
	public String toString()
	{
		return u.getV() + " " + v.getV() + " " + weight;
	}
	
}
