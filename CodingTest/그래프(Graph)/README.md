## 인접리스트로 구현

여기서 key값은 그래프의 `정점`을 의미하고  

value값은 key값의 정점과의 연결 상태를 의미한다.

예를 들어, key가 0이고 value가 [1, 4] 라면 

`0번 정점`은 `1번 정점`과 연결되어 있고 `4번 정점`과도 연결되어 있는 상황을 의미한다.

ex)  
![image](https://user-images.githubusercontent.com/64796257/153124245-3c851879-6610-463c-abcf-aa40b92e0ef7.png)


``` java
// 인접 리스트 구조를 이용해서 그래프를 구현한 코드
import java.util.*;

class Graph<T> {

	// 인접 리스트 구현을 Map을 이용해서 구현함.
  // 이때, 각 정점은 T, 그 정점과 이어진 다른 정점들은 List<T>로 표현하도록 했다.
	private Map<T, List<T>> map = new HashMap<>();

	// 새 정점을 그래프에 추가한다.
	public void addVertex(T s)
	{
    // s라는 이름의 key를 설정하고 그 키가 LinkedList<T>라는 value를 가지도록 했다.
		map.put(s, new LinkedList<T>());
	}
	
  // 정점 s를 삭제한다.
	public void removeVertex(T s) {
		
		// s 노드와 관련 있던 다른 노드들과 연결된 간선 내용을 지운다.
		for(T key: map.keySet()) {
			for(int i = 0; i < map.get(key).size(); i++) {
				if(map.get(key).get(i) == s) {
					map.get(key).remove(i);
				}
			}
		}
		// 최종적으로 s 노드를 삭제한다.
		map.remove(s);
	}

	// source와 destination 사이에 간선을 추가하는 함수
	public void addEdge(T source, T destination, boolean bidirectional)
	{
    // source 정점이 없다면 새로 추가한다.
		if (!map.containsKey(source))
			addVertex(source);
    
    // destination 정점이 없다면 새로 추가한다.
		if (!map.containsKey(destination))
			addVertex(destination);
    
    // source 정점이 destination 정점을 가리키도록 한다.
    
    // 만약에 무방향 그래프라면 destination 정점에서도 source 정점을 가리키도록 한다.
		map.get(source).add(destination);
		if (bidirectional == true) {
			map.get(destination).add(source);
		}
	}
	
  // soucre가 destination을 가리키고 있는 간선을 삭제하는 함수 
	public void removeEdge(T source, T destination, boolean bidirectional) 
	{
    
    // source 정점 또는 destination 정점이 없다면 함수를 종료시킨다.
		if(!map.containsKey(source) || !map.containsKey(destination)) {
			return;
		}
		
    // source 정점이 가지고 있는 value에서
    // destination 정점과 관련된 정보를 삭제한다.
		for(int i = 0; i < map.get(source).size(); i++) {
			if(map.get(source).get(i) == destination) {
				map.get(source).remove(i);
			}
		}
		
    // 무방향 그래프라면 반대쪽에서도 간선 정보를 삭제해줘야 한다.
    if(bidirectional == true){
      // destination 정점이 가지고 있는 value에서
      // source 정점과 관련된 정보를 삭제한다.
      for(int i = 0; i < map.get(destination).size(); i++) {
        if(map.get(destination).get(i) == source) {
          map.get(destination).remove(i);
        }
      }
    }	
	}

	// 정점의 개수를 출력
	public void getVertexCount()
	{
    // 중요한 부분 
    // map.keySet().size()
    
		System.out.println("The graph has "
						+ map.keySet().size()
						+ " vertex");
	}

	// 간선의 개수를 출력
	public void getEdgesCount(boolean bidirection)
	{
		int count = 0;
		for (T v : map.keySet()) {
			count += map.get(v).size();
		}
		if (bidirection == true) {
			count = count / 2;
		}
		System.out.println("The graph has "
						+ count
						+ " edges.");
	}

	// s라는 정점이 있는지 여부를 출력
	public void hasVertex(T s)
	{
		if (map.containsKey(s)) {
			System.out.println("The graph contains "
							+ s + " as a vertex.");
		}
		else {
			System.out.println("The graph does not contain "
							+ s + " as a vertex.");
		}
	}

	// s 정점이 d 정점을 가리키는 간선이 있는지 
	public void hasEdge(T s, T d)
	{
		
		if(!map.containsKey(s)) {
			System.out.println("The graph does not contains "
					+ s + " as a vertex.");
			return;
		}
		
		if(!map.containsKey(d)) {
			System.out.println("The graph does not contains "
					+ d + " as a vertex.");
			return;
		}
		
		if (map.get(s).contains(d)) {
			System.out.println("The graph has an edge between "
							+ s + " and " + d + ".");
		}
		else {
			System.out.println("The graph has no edge between "
							+ s + " and " + d + ".");
		}
	}

	// Prints the adjancency list of each vertex.
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for (T v : map.keySet()) {
			builder.append(v.toString() + ": ");
			for (T w : map.get(v)) {
				builder.append(w.toString() + " ");
			}
			builder.append("\n");
		}

		return (builder.toString());
	}
}


public class GraphAdjancentList {

	public static void main(String args[])
	{

		// Object of graph is created.
		Graph<Integer> g = new Graph<Integer>();

		// edges are added.
		// Since the graph is bidirectional, 그래프가 양방향이라서 true라 함.
		// so boolean bidirectional is passed as true.
		g.addEdge(0, 1, true);
		g.addEdge(0, 4, true);
		g.addEdge(1, 2, true);
		g.addEdge(1, 3, true);
		g.addEdge(1, 4, true);
		g.addEdge(2, 3, true);
		g.addEdge(3, 4, true);

		// Printing the graph
		System.out.println("Graph:\n"
						+ g.toString());

		// Gives the no of vertices in the graph.
		g.getVertexCount();

		// Gives the no of edges in the graph.
		g.getEdgesCount(true);

		// Tells whether the edge is present or not.
		g.hasEdge(1, 2);

		// Tells whether vertex is present or not
		g.hasVertex(5);
		
		g.removeEdge(1, 2, true);
		g.hasEdge(1, 2);
		
		g.removeVertex(3);
		g.hasVertex(3);
		
		g.hasEdge(3, 4);
		
	}

}
```

출력 내용 
``` 
Graph:
0: 1 4 
1: 0 2 3 4 
2: 1 3 
3: 1 2 4 
4: 0 1 3 

The graph has 5 vertex
The graph has 7 edges.
The graph has an edge between 1 and 2.
The graph does not contain 5 as a vertex.
The graph has no edge between 1 and 2.
The graph does not contain 3 as a vertex.
The graph does not contains 3 as a vertex.

```

## 인접행렬로 구현

일단 아래 그래프는 무방향 그래프를 기준으로만 구현했다. 

하지만, 방향 그래프로 사용하기 위해서는 바꿔야 할 부분이 그렇게 어렵지 않다.

``` java
class GraphMatrix {
	
	 private int _numberOfVertices; // 꼭짓점 개수
	    private int _numberOfEdges; // 간선의 개수

	    private int[][] _adjacency; // 인접행렬

	    // 인접행렬에서 간선이 존재하는지 여부
	    static final int EDGE_EXIST = 1; 
	    static final int EDGE_NONE = 0;

	    // 생성자. 
	    // 생성하고자 하는 꼭지점의 개수를 매개변수로 전달.
	    // 10개를 전달하고 싶다면 AdjacencyMatrixGraph(10); 이라 하면 됨.
	    public GraphMatrix(int givenNumberOfVertices) {
	        this.setNumberOfVertices(givenNumberOfVertices);
	        this.setNumberOfEdges(0);
	        this.setAdjacency(new int[givenNumberOfVertices][givenNumberOfVertices]);
	        for (int i = 0; i < givenNumberOfVertices; i++) {
	            for (int j = 0; j < givenNumberOfVertices; j++) {
	                this.adjacency()[i][j] = GraphMatrix.EDGE_NONE;
	            }
	        }
	    }
	    
      // 아예 행렬이 주어진다면 그 행렬을 그대로 그래프 인접행렬로 활용한다.
	    public GraphMatrix(int[][] givenMatrix) {
	    	
	    	this.setNumberOfVertices(givenMatrix.length);
	    	this.setNumberOfEdges(givenMatrix.length);
	    	this.setAdjacency(givenMatrix);
	    	for (int i = 0; i < givenMatrix.length; i++) {
	            for (int j = 0; j < givenMatrix.length; j++) {
	                this.adjacency()[i][j] = givenMatrix[i][j];
	            }
	        }
	    }


	    // 꼭지점의 개수 설정 
	    private void setNumberOfVertices(int newNumberOfVertices) {
	        this._numberOfVertices = newNumberOfVertices;
	    }

	    // 꼭지점 개수 출력하는 get 함수
	    public int numberOfVertices() {
	        return this._numberOfVertices;
	    }

		// 간선의 개수 설정 
	    private void setNumberOfEdges(int newNumberOfEdges) {
	        this._numberOfEdges = newNumberOfEdges;
	    }

	    // 간선의 개수 출력하는 get 함수
	    public int numberOfEdges() {
	        return this._numberOfEdges;
	    }

	    // 인접행렬 설정
	    private void setAdjacency(int[][] newAdjacency) {
	        this._adjacency = newAdjacency; 
	    }

	    // 인접 행렬을 출력하는 get 함수
	    private int[][] adjacency() {
	        return this._adjacency;
	    }

	    /**
	     * Checks if two vertices are connected by an edge
	     *
	     * @param from the parent vertex to check for adjacency
	     * @param to the child vertex to check for adjacency
	     * @return whether or not the vertices are adjancent
	     */
	    // from 꼭짓점에서 to 꼭짓점으로 연결되어 있는지 확인하는 함수
	    
	    private boolean adjacencyOfEdgeDoesExist(int from, int to) {
	        return (this.adjacency()[from][to] != GraphMatrix.EDGE_NONE);
	    }

	    // 특정 꼭짓점이 존재하는지 확인하는 함수
	    // 아직은 개수 이하의 값만 넣을 수 있어서 if문 조건이 아래와 같다.
	    public boolean vertexDoesExist(int aVertex) {
	        if (aVertex >= 0 && aVertex < this.numberOfVertices()) {
	            return true;
	        } else {
	            return false;
	        }
	    }

	    // from 꼭짓점과 to 꼭짓점이 서로 간선에 의해 연결되어 있는지 확인하는 함수
	    public boolean edgeDoesExist(int from, int to) {
	        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
	            return (this.adjacencyOfEdgeDoesExist(from, to));
	        }

	        return false;
	    }
	
	    // 간선을 추가하는 함수 
	    // 일단 인접행렬의 index값이 행렬의 꼭짓점과 동일하다고 여겨서 아래와 같이 코드를 작성한다. 
	    public boolean addEdge(int from, int to) {
	        if (this.vertexDoesExist(from) && this.vertexDoesExist(to)) {
	            if (!this.adjacencyOfEdgeDoesExist(from, to)) {
		    	// 인접행렬에 간선의 내용 저장
	                this.adjacency()[from][to] = GraphMatrix.EDGE_EXIST;
	                this.adjacency()[to][from] = GraphMatrix.EDGE_EXIST;
			
			// 간선의 개수 1 증가
	                this.setNumberOfEdges(this.numberOfEdges() + 1);
	                return true;
	            }
	        }

	        return false;
	    }
	    // 간선을 삭제하는 함수 
	    // 일단 인접행렬의 index값이 행렬의 꼭짓점과 동일하다고 여겨서 아래와 같이 코드를 작성한다.
	    public boolean removeEdge(int from, int to) {
	    
	    // from과 to 사이에 간선이 존재할 때 동작을 실행한다.
	        if (this.adjacencyOfEdgeDoesExist(from, to)) {
	            this.adjacency()[from][to] = GraphMatrix.EDGE_NONE;
	            this.adjacency()[to][from] = GraphMatrix.EDGE_NONE;
	            this.setNumberOfEdges(this.numberOfEdges() - 1);
	            return true;
	        }
	        return false;
	    }
	    
	    
	
}
```


