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
// 루트는 cpp, java, python 3개로 만들고

// 직군 - left : backend, right : frontend
// 경력 - left : junior, right : senior
// 소울푸드 - left : chicken, right : pizza
// 점수 - 입력받는 자료형은 String이다.

// 범위가 정해져있으니까 각각의 case들을 배열로 표현할 수 있지 않을까.

import java.util.List; 
import java.util.ArrayList; 

import java.util.StringTokenizer;

class Solution {
    
    /*static List<Integer>[] cpplist;
    static List<Integer>[] javalist;
    static List<Integer>[] pylist; */
    static List<Integer[] list; 
    
    static StringTokenizer st; 
    
    public int[] solution(String[] info, String[] query) {
        int[] answer = {};
        
        // 정보 저장
        /*cpplist = new ArrayList[8];
        javalist = new ArrayList[8];
        pylist = new ArrayList[8]; */
        
        list = new ArrayList[24]; // 0 ~ 7 : cpp, 8 ~ 15 : java, 16 ~ 23 : python
        
        
        
        for(int i = 0; i < 24; i++) {
            
            /*cpplist[i] = new ArrayList<>();
            javalist[i] = new ArrayList<>();
            pylist[i] = new ArrayList<>(); */
            list = new ArrayList<>();
            
        }
        
        String lang;
        for(int i = 0; i < info.length; i++) { 
            
            st = new StringTokenizer(info[i]);
            
            lang = st.nextToken();
            /* if(lang.equals("cpp")) { setData(cpplist, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());}
            
            else if(lang.equals("java")) { setData(javalist, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());}
            
            else if(lang.equals("python")) { setData(pylist, st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());} */
            
            
            
        }
        
        // 쿼리 
        String group, exp, food, score;
        
        boolean[] visited = new boolean[8]; // true - 방문해야 할 인덱스
        
        for(int i = 0; i < query.length; i++) { 
            
            st = new StringTokenizer(query[i]); 
            lang = st.nextToken(); st.nextToken(); // 언어 - cpp, java, python 
            group = st.nextToken(); st.nextToken(); // 직군
            exp = st.nextToken(); st.nextToken(); // 경력
            food = st.nextToken(); // 음식
            
            score = st.nextToken(); // 점수
            
            
        }
        
        
        return answer;
    }
    
    static void qur(List<Integer>[] list, String group, String exp, String food, String score) { 
        
        
        
    }
    
    static void setData(List<Integer>[] list, String group, String exp, String food, String score) { 
        
        if(group.equals("backend") && exp.equals("junior") && food.equals("chicken")) {
            
            list[0].add(Integer.parseInt(score)); 
        }
        
        else if(group.equals("backend") && exp.equals("junior") && food.equals("pizza")) {
            
            list[1].add(Integer.parseInt(score)); 
        }
        
        else if(group.equals("backend") && exp.equals("senior") && food.equals("chicken")) {
            
            list[2].add(Integer.parseInt(score)); 
        }
        
        else if(group.equals("backend") && exp.equals("senior") && food.equals("pizza")) {
            
            list[3].add(Integer.parseInt(score)); 
        }
        
        else if(group.equals("frontend") && exp.equals("junior") && food.equals("chicken")) {
            
            list[4].add(Integer.parseInt(score)); 
        }
        
        else if(group.equals("frontend") && exp.equals("junior") && food.equals("pizza")) {
            
            list[5].add(Integer.parseInt(score)); 
        }
        
        else if(group.equals("frontend") && exp.equals("senior") && food.equals("chicken")) {
            
            list[6].add(Integer.parseInt(score)); 
        }
        
        else if(group.equals("frontend") && exp.equals("senior") && food.equals("pizza")) {
            
            list[7].add(Integer.parseInt(score)); 
        }
        
        
        
    }
}
