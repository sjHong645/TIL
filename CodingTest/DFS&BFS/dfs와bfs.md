[출처](https://www.acmicpc.net/problem/1260)

## 문제 

### 문제 설명 

그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.  

단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다.  

정점 번호는 1번부터 N번까지이다.

### 입력 

첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.  
다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다.  

어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다. 입력으로 주어지는 간선은 양방향이다.

### 출력 

첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.

### 예제 입력 1
```
4 5 1
1 2
1 3
1 4
2 4
3 4
```
### 예제 출력 1
```
1 2 4 3
1 2 3 4
```

### 예제 입력 2
```
5 5 3
5 4
5 2
1 2
3 4
3 1
```

### 예제 출력 2
```
3 1 2 5 4
3 1 4 2 5
```


### 예제 입력 3
```
1000 1 1000
999 1000
```

### 예제 출력 3
```
1000 999
1000 999
```


## 정답 코드 

그래프를 이용한 DFS와 BFS의 기본 문제

어떤 형식으로 그래프를 구현할 지는 문제에 따라서 다르게 구현해주면 된다.

``` java
import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

import java.lang.StringBuilder;

import java.util.List; 
import java.util.ArrayList;

import java.util.LinkedList;
import java.util.Queue;

// 그래프를 인접행렬로 구현할 것!
class Graph22 {
	
	int vertexCnt; // 정점 개수 
	int edgeCnt; // 간선 개수
	boolean[][] matrix;
	
	Graph22 (int vertex, int edge){
		
		this.vertexCnt = vertex;
		this.edgeCnt = edge;
		
		matrix = new boolean[vertexCnt + 1][vertexCnt + 1];
		
		for(int i = 1; i <= vertexCnt; i++) {
			for(int j = 1; j <= vertexCnt; j++) {
				
				if(i == j) this.getMatrix()[i][j] = true;
				
				else this.getMatrix()[i][j] = false;
				
			}
			
		}
		
	}
	
	public boolean[][] getMatrix(){ return this.matrix; }
	
	// 정점 개수 * 정점 개수 크기의 행렬
	 
	
	// 간선 추가 
	public void addEdge(int source, int destination) {
		
		
		// 양방향이니까 (source, destination) , (destination, source)에 둘 다 1 넣으면 됨.
		if(!isEdge(source, destination)) {
			
			matrix[source][destination] = true;
			
			matrix[destination][source] = true;	
		}
		
		else return;
		
	}
	
	// 간선이 이미 있는지 확인 
	// 이미 간선이 있으면 true, 간선이 없으면 false
	public boolean isEdge(int source, int destination) {
		
		return matrix[source][destination] == true;
		
	}
	
		
}

public class DFS와BFS {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static StringTokenizer st; 
	
	static boolean[] visited_dfs; static StringBuilder sb_dfs; 
	
	static boolean[] visited_bfs; static StringBuilder sb_bfs;
	
	// static Graph22<Integer> graph = new Graph22<Integer>();
	static Graph22 graph;

	public static void main(String[] args) throws IOException {

		st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken()); // 정점의 개수
		int M = Integer.parseInt(st.nextToken()); // 간선의 개수 - 간선은 양방향
		
		int V = Integer.parseInt(st.nextToken()); // 탐색을 시작할 정점 번호
				
		int source; int destination;
		
		// 정점 개수(=N) 만큼 그래프 정점 만듬 
		// 간선 개수(=M) 도 추가로 넣어줌.
		graph = new Graph22(N, M);
		
		visited_dfs = new boolean[N + 1]; visited_dfs[0] = true;
		visited_bfs = new boolean[N + 1]; visited_bfs[0] = true;
		
		// 간선의 개수만큼 그래프를 입력한다.
		for(int i = 0; i < M; i++) {
			
			st = new StringTokenizer(br.readLine());
			
			source = Integer.parseInt(st.nextToken());
			destination = Integer.parseInt(st.nextToken());
			
			graph.addEdge(source, destination);
			
		}
		
		// dfs 시작
		dfs(V);
		
		System.out.println();
		
		
		// bfs 시작 
		bfs(V);

	}
	
	static void dfs(int start) {
		
		visited_dfs[start] = true; // 일단 시작 지점 방문.
		// sb_dfs.append(start + " ");
		System.out.print(start + " ");
		
		// graph.getMatrix()[start] 에서의 true 값을 가지는 index 값
		List<Integer> idxList = new ArrayList<>(); 
		
		for(int i = 1; i < graph.getMatrix()[start].length; i++) {
			
			if(graph.getMatrix()[start][i] == true && start != i) idxList.add(i);
			
		}
		
		for(int val : idxList) {
		    // System.out.println("start = " + start + " val = " + val);
			if(visited_dfs[val] == false) dfs(val);
			
		}
		
	}
	
	
	static void bfs(int start) {
		
		visited_bfs[start] = true;
		System.out.print(start + " ");
		
		Queue<Integer> que = new LinkedList<>();
		
		que.offer(start);
		
		List<Integer> idxList = new ArrayList<>();
		int deque = 0;
		while(!que.isEmpty()) {
			
			deque = que.poll();
			for(int i = 1; i < graph.getMatrix()[deque].length; i++) {
				
				if(graph.getMatrix()[deque][i] == true && deque != i) idxList.add(i);
				
			}
			
			for(int val : idxList) {
			    // System.out.println("start = " + start + " val = " + val);
				if(visited_bfs[val] == false) {
					
					visited_bfs[val] = true; 
					// 출력
					System.out.print(val + " ");
					que.offer(val);
					
				}
				
			}
			
			
		}
	}
	
	

}
```




























