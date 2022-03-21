## DFS 

``` python
def dfs(u) : 
  visited[u] = true;
  // do something for node u; (ex. u 노드 출력)
  
  for each v in out-neighbors of u : 
    if(visited[v] == false) dfs(v);

```

1) 처음 방문한 노드를 `방문했다`고 표시한다. 
2) 원하는 동작을 실행한다(ex. 출력)
3) 그 노드의 이웃한 노드가 있는데 `아직 방문하지 않은` 노드라면 그 노드에 대해서 dfs를 실행한다.

## BFS 

``` python

def bfs(u) : 

  visited[u] = true;
  // do something for node u; (ex. u 노드 출력)
  
  queue.enqueue(u); 
  
  while queue is not empty : 
    
    deq = que.dequeue(); 
    
    for each v in out-neighbors of deq : 
      
      if(visited[v] == false) : 
        visited[v] = true;
        // do something for node v; (ex. v 노드 출력)
        queue.enqueue(v);
```

1) 처음 방문한 노드를 `방문했다`고 표시한다.
2) 그 노드를 queue에 `집어넣는다`.
3) queue가 비지 않는다면 `queue의 맨 앞`에 있는 값을 `dequeue` 한다.
4) `dequeue 한 값`의 이웃한 노드가 있는데 
4-1) `아직 방문하지 않은` 노드라면 `방문했다`고 표시하고 그 노드에 대해서 원하는 동작을 실행한다.
4-2) 그 노드를 queue에 `넣는다`.
