[관련 파일](/CS/알고리즘&자료구조/Graph/MST.md)

## 코드 - [출처](https://data-make.tistory.com/519)

코드에 대한 해설은 다른 파일에서 했다.

### 크루스칼 (Kruskal) 

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class MST_Kruskal {
 
    static int V, E, parents[];
    static Edge[] edgeList;
    
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
 
        public Edge(int from, int to, int weight) {
            super();
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
 
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        V = Integer.parseInt(st.nextToken());    // 정점 개수
        E = Integer.parseInt(st.nextToken());    // 간선 개수
        
        edgeList = new Edge[E];
        parents = new int[V + 1];
 
        // Edge 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            
            edgeList[i] = new Edge(a, b, c);
        }
        
        System.out.println(kruskal());
    }
 
    private static int kruskal() {
        
        int res = 0, cnt = 0;
        
        // 간선 가중치 기준 오름차순 정렬
        Arrays.sort(edgeList);
        // 정점 초기화
        make();
        
        // 주어진 간선을 이어보면서
        for (Edge edge : edgeList) {
            // 싸이클이 형성되지 않는다면
            if(union(edge.from, edge.to)) {
                // 해당 간선을 사용
                res += edge.weight;
                // 정점 - 1 개의 간선이 이어졌다면 MST 완성!
                if(++cnt == V - 1) return res;
            }
        }
        
        return res;
    }
 
    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        
        if(aRoot == bRoot) return false;
        parents[aRoot] = bRoot;
        return true;
    }
 
    private static int find(int a) {
        if(a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }
 
    private static void make() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }
 
}


// 출처: https://data-make.tistory.com/519 [Data Makes Our Future]

```


### 프림 (Prim) 

``` java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
 
public class MST_Prim {
 
    static int V, E;
    static ArrayList<Node>[] adj;
    static class Node implements Comparable<Node> {
        int to, weight;
 
        public Node(int to, int weight) {
            // super();
            this.to = to;
            this.weight = weight;
        }
 
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        V = Integer.parseInt(st.nextToken());    // 정점 개수
        E = Integer.parseInt(st.nextToken());    // 간선 개수
        
        adj = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }
        
        // Edge 정보 입력
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            // 인접 리스트
            adj[a].add(new Node(b, c));
            adj[b].add(new Node(a, c));
        }
        
        System.out.println(prim());
    }
 
    private static long prim() {
        
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 1번 노드부터 출발
        pq.add(new Node(1, 0));
        
        long res = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            
            Node edge = pq.poll();
            // 이미 확인한 정점이면 pass
            if(visited[edge.to]) continue;
            
            res += edge.weight;
            visited[edge.to] = true;
            // 모든 노드를 방문했다면 return
            if(++cnt == V) return res;
            
            for (int i = 0; i < adj[edge.to].size(); i++) {
                // 연결된 노드들 중 방문하지 않은 노드 찾기
                Node next = adj[edge.to].get(i); 
                if(visited[next.to]) continue;
                
                pq.add(next);
            }
        }
        
        return res;
    }
 
}


// 출처: https://data-make.tistory.com/519 [Data Makes Our Future]
```
