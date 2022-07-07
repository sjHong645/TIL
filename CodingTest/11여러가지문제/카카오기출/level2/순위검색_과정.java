 import java.util.StringTokenizer;

class Node { 
    
    String head; 
    String left; // 왼쪽 자식 노드
    String right; // 오른쪽 자식 노드 
    
    public Node(String head) { 
        
        this.head = head; 
        this.left = null; this.right = null;
        
    }
    
    public void setLeft(String left) { this.left = new Node(left); }
    
    public void setRight(String right) { this.right = new Node(right); }
    
    public Node getLeft() { return this.left; }
    public Node getRight() { return this.right; }
    
    /*public Node(String head, String left, String right) { 
        
        this.head = head; 
        this.left = left; 
        this.right = right;
        
    }*/
    
}
// 트리는 어떤가...
// 맨 위는 언어, 그 다음 층은 직군, 그 다음 층은 경력, 그 다음 층은 음식, 그 다음 층은 점수 

// 문제의 조건을 보니까, 직군, 경력, 음식 모두 2개밖에 없다. = 이진트리로 만들기 딱 좋다. 
// 루트는 cpp, java, python 3개로 만들고.

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        Node[] graph = new Node[3]; // 0번은 cpp, 1번은 java, 2번은 python 
        
        graph[0] = new Node("cpp");
        graph[1] = new Node("java");
        graph[2] = new Node("python");
        
        // 직군 - left : backend, right : frontend
        // 경력 - left : junior, right : senior
        // 소울푸드 - left : chicken, right : pizza
        // 점수 - 입력받는 자료형은 String이다.
        
        StringTokenizer st; 
        String lang, group, exp, food, score; 
        
        for(int i = 0; i < info.length; i++) { 
            
            st = new StringTokenizer(info[i]);
            
            lang = st.nextToken();
            if(lang.equals("cpp")) {
                
                group = st.nextToken(); exp = st.nextToken(); 
                food = st.nextToken(); score = st.nextToken(); 
                
                setTree(graph[0], group, exp, food, score);
            }
            
            else if(lang.equals("java")) {}
            
            else if(lang.equals("python")) {}
            
        }
        
        
        
        return answer;
    }
    
    static void setTree(Node lang, String group, String exp, String food, String score) { 
        
        if(group.equals("backend")) lang.setLeft(group);
        
        else lang.setRight(group); 
        
        
        
        
    }
}
