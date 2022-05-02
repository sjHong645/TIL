## 변수 선언 

``` java
static int V, E, parents[];
static Edge[] edgeList; // edge 정보를 저장할 edgeList
    
    static class Edge implements Comparable<Edge> {
        int from, to, weight;
 
        public Edge(int from, int to, int weight) {
            super();
            this.from = from;     // 시작점
            this.to = to;         // 도착점
            this.weight = weight; // 가중치
        }
 
        // 간선을 가중치 기준으로 오름차순하기 위한 compareTo 메서드 
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
            
            edgeList[i] = new Edge(a, b, c); // 시작점, 도착점, 가중치 정보를 edgeList에 저장한다.
        }
        
        System.out.println(kruskal());
    }
```

Edge 정보를 입력하고 나서 Kruskal 알고리즘을 동작한다. 

Kruskal 알고리즘 코드는 아래와 같다. 

``` java
  static int kruskal() {
        
        int res = 0, cnt = 0;
        
        // 간선들을 가중치를 기준으로 오름차순 정렬
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
```

여기서 정점 초기화 메서드 `make()`는 아래와 같다. 

``` java
    private static void make() {
        for (int i = 1; i <= V; i++) {
            parents[i] = i;
        }
    }
```

아직 각각의 노드가 다른 노드를 가리키지 않기 때문에 자기 자신을 가리키는 것으로 정점을 초기화한다.

`union`과 `find`는 아래와 같다. 

``` java
// a 노드와 b 노드가 서로 이어져 있다면 false
// 서로 이어져 있지 않다면 true 

    private static boolean union(int a, int b) {
        int aRoot = find(a); // a 노드의 루트 노드 aRoot
        int bRoot = find(b); // b 노드의 루트 노드 bRoot 
        
        // a 노드와 b 노드의 루트 노드가 같다는 건
        // 서로 이어져 있다는 것을 의미한다. 따라서, 반환값은 false
        if(aRoot == bRoot) return false; 
        
        // 서로 이어져 있지 않다면 
        // 서로 이어지도록 한 다음에 true를 반환한다. 
        parents[aRoot] = bRoot;
        return true;
    }
 
    private static int find(int a) {
    
        if(a == parents[a]) return a;
        
        // parent[a]의 값을 return한다. 
        // 그 값은 find(parents[a])를 통해 재귀식을 이용해서 찾는다.
        return parents[a] = find(parents[a]);
    }
```





























