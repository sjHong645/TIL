## 변수 선언 

``` java
    static int V, E;
    static ArrayList<Node>[] adj; // 그래프를 표현할 인접 리스트 
    
    static class Node implements Comparable<Node> {
    
        int to, weight;
 
        public Node(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }
  
        // 가중치에 따라 오름차순 정렬이 되도록 했다.
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
        
        // 인접 리스트 설정 
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
```

## 프림 알고리즘 

``` java
private static long prim() {
        
        boolean[] visited = new boolean[V + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        // 1번 노드부터 출발
        pq.add(new Node(1, 0));
        
        long res = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            
            Node edge = pq.poll();
            
            // 이미 방문한 정점이면 pass
            if(visited[edge.to]) continue;
            
            // 확인하지 않은 정점이니까 방문 & 방문했으니까 가중치가 더해진다.
            visited[edge.to] = true; res += edge.weight;
            
            // 모든 노드를 방문했다면 return
            if(++cnt == V) return res;
            
            for (Node next : adj[edge.to]) {
            
                // 연결된 노드들 중 방문하지 않은 노드 찾기
                if(visited[next.to]) continue;
                
                pq.add(next);
            }
        }
        
        return res;
    }
```





























