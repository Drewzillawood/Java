// LEAVE THIS FILE IN THE DEFAULT PACKAGE
//  (i.e., DO NOT add 'package cs311.pa1;' or similar)

// DO NOT MODIFY THE EXISTING METHOD SIGNATURES
//  (you may, however, add additional methods and fields)

// DO NOT INCLUDE LIBRARIES OUTSIDE OF THE JAVA STANDARD LIBRARY
//  (i.e., you may include java.util.ArrayList etc. here, but not junit, apache commons, google guava, etc.)

import java.util.ArrayList;

public class GraphProcessor
{
	// other member fields and methods

	// NOTE: graphData should be an absolute file path
	public GraphProcessor(String graphData)
	{
		// implementation
	}

	public int outDegree(String v)
	{
		// implementation
		return 0;
	}

	public ArrayList<String> bfsPath(String u, String v)
	{
		// implementation
		return new ArrayList<String>();
	}

	public int diameter()
	{
		// implementation
		return 0;
	}

	public int centrality(String v)
	{
		// implementation
		return 0;
	}

	/**
	 * Graph implementation
	 * 
	 * @author drewu
	 */
	public final class Graph
	{
		/**
		 * This Graphs set of Edges
		 */
		private ArrayList<Edge>		Edges;

		/**
		 * This Graphs set of Vertices
		 */
		private ArrayList<Vertex>	Vertices;

		/**
		 * Constructs a graph with no edges, and no vertices
		 */
		public Graph()
		{
			Edges = new ArrayList<Edge>();
			Vertices = new ArrayList<Vertex>();
		}

		/**
		 * Constructs a graph with no edges
		 * 
		 * @param Root
		 *            Vertex for this new Graph
		 */
		public Graph(Vertex root)
		{
			Edges = new ArrayList<Edge>();
			Vertices.add(root);
		}

		/**
		 * Constructs an already active Graph
		 * 
		 * @param root
		 *            Root Vertex for this new Graph
		 * @param v
		 *            List of vertices
		 * @param e
		 *            List of edges
		 */
		public Graph(Vertex root, ArrayList<Vertex> v, ArrayList<Edge> e)
		{
			Edges = e;
			Vertices = v;
			Vertices.add(root);
		}

		/**
		 * Adds Vertex v to our list and joins it
		 * 
		 * @param v
		 */
		public void addVertex(Vertex v)
		{
			if(!Vertices.contains(v))
			{
				Vertices.add(v);
			}
		}

		/**
		 * Adds Edge e to our list and joins it
		 * 
		 * @param e
		 */
		public void addEdge(Edge e)
		{
			if(!Edges.contains(e))
			{
				Edges.add(e);
			}
		}

		/**
		 * Retrieve our list of vertices
		 * 
		 * @return Vertices of this graph
		 */
		public ArrayList<Vertex> getVertices()
		{
			return Vertices;
		}

		/**
		 * Retrieve our list of edges
		 * 
		 * @return Edges of this graph
		 */
		public ArrayList<Edge> getEdges()
		{
			return Edges;
		}

		/**
		 * Private Edge class to implement Graph
		 * 
		 * @author drewu
		 */
		public final class Edge
		{
			/**
			 * Will only be able to traverse in one direction
			 */
			private Vertex direction;

			/**
			 * Edge will connect two vertices
			 * 
			 * @param u
			 * @param v
			 */
			private Edge(Vertex u, Vertex v)
			{
				// We will use the parameter 'v' to determine direction
				direction = v;
			}

			/**
			 * Will send us to the next vertex via this edge
			 * 
			 * @return Vertex at the end of this edge
			 */
			public Vertex jump()
			{
				return direction;
			}
		}

		/**
		 * Private Vertex class to implement graph
		 * 
		 * @author drewu
		 */
		public final class Vertex
		{
			/**
			 * This Vertex's current set of neighbors
			 */
			private ArrayList<Edge>	n;

			/**
			 * This Vertex will maintain a count of its neighbors
			 */
			private int				numNeighbors;

			/**
			 * Vertex without any neighbors
			 */
			private Vertex()
			{
				n = new ArrayList<Edge>();
				numNeighbors = 0;
			}

			/**
			 * Constructor will create new Vertex w/ given ArrayList of
			 * neighbors
			 * 
			 * @param neighbors
			 */
			private Vertex(ArrayList<Edge> neighbors)
			{
				n.addAll(neighbors);
				numNeighbors = n.size();
			}

			/**
			 * Get number of Neighbors
			 * 
			 * @return number of Neighbors
			 */
			public int getNumNeighbors()
			{
				return numNeighbors;
			}

			/**
			 * Retrieve all the edges of this vertex
			 * 
			 * @return List of attached Edges
			 */
			public ArrayList<Edge> getNeighbors()
			{
				return n;
			}
		}
	}

}