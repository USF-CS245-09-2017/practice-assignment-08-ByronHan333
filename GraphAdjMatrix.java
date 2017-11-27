import java.util.ArrayList;

//although called matrix this is adjacency list
public class GraphAdjMatrix implements Graph{
	private int vertices;
	private Node[] list;
	
	public GraphAdjMatrix(int vertices){
		this.vertices = vertices;
		list = new Node[vertices];
		for (int i = 0; i < vertices; i++){
			list[i] = new Node(0);
		}
	}
	
	public void addEdge(int v1, int v2){
		list[v1].add(v2);
		list[v1].num++;
	}
	
	public int[] neighbors(int vertex){
		int[] arr = new int[list[vertex].num];
		int j = 0;
		Node increment = list[vertex].next;
		while(increment != null){
			arr[j++] = increment.num;
			increment = increment.next;
		}
		return arr;
	}
	
	public void topologicalSort(){
		//whether this vertex is visited
		boolean[] visited = new boolean[vertices];
		//indegree of all vertices
		int[] indegree = new int[vertices];
		//if all vertices are visited
		boolean success = true;
		//the result of topological sort
		String result="";
		//The arraylist to store all the vertices with indegree 0 waiting to be printed
		ArrayList<Integer> al;
		
		//find the indegree of all vertices
		int[] temp;
		for (int i=0 ; i<vertices; i++){
			temp = neighbors(i);
			for (int j=0; j<temp.length; j++){
				indegree[temp[j]]++;
			}
		}
		
		//choose those vertices with indegree 0 and add them to the arraylist, then mark them as visited
		al = new ArrayList<Integer>();
		for(int k=0; k<indegree.length; k++){
			if(indegree[k]==0){
				al.add(k);
				visited[k] = true;
			}
		}
		
		//remove the first vertex with indegree 0 and delete this from the graph, then update the indegree map 
		//and then take those new vertices with indegree 0 and add them to the arraylist and mark them as visited
		while(!al.isEmpty()){
			int first = al.remove(0);
			result = result+first+" ";
			int[] neighbors = neighbors(first);
			for(int l=0; l<neighbors.length; l++){
				if(!visited[neighbors[l]]){
					indegree[neighbors[l]]--;
				}
			}
			for(int m=0; m<indegree.length; m++){
				if(indegree[m]==0 && !visited[m]){
					al.add(m);
					visited[m] = true;
				}
			}	
		}
		
		//if there is some vertices left it means there is a cycle
		for(int n=0; n<visited.length; n++){
			if(!visited[n]){
				success = false;
			}
		}
		if(success){
			System.out.println(result);
		}else{
			System.out.println("there is a cycle");
		}
	}
	
	private class Node{
		private int num;
		private Node next;
		
		public Node(int num, Node next){
			this.num = num;
			this.next = next;
		}
		
		public Node(){
			this.num = 0;
			this.next = null;
		}
		
		public Node(int num){
			this.num = num;
			this.next = null;
		}
		
		public void add(int num){
			Node temp = new Node(num);
			temp.next = this.next;
			this.next = temp;
		}
		 
	}
	
	
	
	
}


