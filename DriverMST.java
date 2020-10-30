
public class DriverMST
{
	public static void main(String[] args) 
	{
		// if no arguments
		if( args.length == 0 )
		{
			// show usage
			System.out.println("Usage : java DriverMST <input_file.txt>");
			return;
		}

		// creates graph
		Graph graph = new Graph();
		// reads graph
		graph.readFile(args[0]);
	
		// print Prims
		graph.prim(args.length > 1 && args[1].equals("-e"));
		System.out.println();
		// print Kruskals
		graph.kruskal(args.length > 1 && args[1].equals("-e"));
	}
}
