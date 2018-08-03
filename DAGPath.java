import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class DAGPath {

	public static void main(String args[]) throws NumberFormatException, IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("D://datafile.txt"));
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of nodes");
		int noOfNodes = Integer.parseInt(reader.readLine());
		
		Graph g = new Graph(noOfNodes);
		for(int i=0;i<noOfNodes; i++)
		{
			System.out.println("Enter connecting nodes u,v");
			String[] edges = new String[2];
			final String line = reader.readLine() ;
			if(line == null || line.equals(""))
			{
				break;
			}
			edges = line.trim().split(",");
			int u = Integer.parseInt(edges[0].trim());
			int v = Integer.parseInt(edges[1].trim());
			g.addEdge(u, v);
			g.distinctNodes.add(u);
			g.distinctNodes.add(v);
		}
		
		System.out.println(g);
		System.out.println(g.findDegree(1));
		g.printAllPathFromANode(g.findDegree(0), "0");
	}
}

class Graph{
	int vertex;
	List<Integer> adj[];
	public static Set<Integer> distinctNodes = new HashSet<>();
	public Graph(int vertex)
	{
		this.vertex = vertex;
		adj = new ArrayList[vertex];

		for(int i = 0; i < vertex; i++)
		{
			adj[i] = new ArrayList<>();
		}
	}
	public void addEdge(int u, int v)
	{
		adj[u].add(v);
	}
	

	public Integer findDegree(int degree)
	{
		int foundNode = 0;
		for(Integer node : distinctNodes)
		{
			for(List<Integer> adj: this.adj)
			{
				if(adj.contains(node))
				{
					foundNode++;
				}
			}
			if(foundNode == degree)
			{
				return node;
			}
		}
		return null;
		
	}
	
	public void printAllPathFromANode(Integer node, String s)
	{

		if(this.adj[node].size()==0)
		{
			System.out.println(s);
		}
		else
		{
			
			for(Integer nodes:this.adj[node])
			{
				printAllPathFromANode(nodes, s+" ->"+nodes);
			}
			
		}
		
		
	}
	public String toString()
	{
		StringBuilder s = new StringBuilder("");
		for(List<Integer> list:adj)
		{
			s.append(list.toString());
		}
		return s.toString();
	}
}