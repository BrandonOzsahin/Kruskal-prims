import java.util.HashMap;

public class Vertex 
{
	// the number of this vertex
	private int v;
	
	// the list of the neighbors
	private HashMap<Integer, Integer> neighbors;
	
	// the constructor
	public Vertex(int v)
	{
		// set the number 
		this.v = v;
		
		// create the set of neighbors
		neighbors = new HashMap<>();
	}
	
	// get the number
	public int getV()
	{
		// return v
		return v;
	}

	// the overload equals
	public boolean equals(Object o)
	{
		// if o is null
		if( o == null )
		{
			// return false
			return false;
		}
		// if o is an object of Vertex
		if( o instanceof Vertex )
		{
			// get the other vertex
			Vertex other = (Vertex)o;
			
			// compare the number
			return other.v == this.v;
		}
		// else
		else
		{
			// return false
			return false;
		}
	}
	
	// the method to add a neighbor
	public void add(int n, int w)
	{
		// add to this neighbor
		neighbors.put(n, w);
	}
	
	// the method to get the weight of this neighbor
	public int getWeight(int n)
	{
		// if this is a neighbor
		if( neighbors.keySet().contains(n))
			return neighbors.get(n);
		// else return -1
		return -1;
	}
}
