import java.util.StringTokenizer;

class Info { 
    
    String lang; // 개발 언어 - cpp, java, python
    String group; // 직군 - backend, frontend
    String exp; // 경력 - junior, senior
    String food; // 소울 푸드 - chicken, pizza
    
    int score; // 코딩테스트 점수 
    
    public Info(String lang, String group, String exp, String food, int score) { 
        
        this.lang = lang;
        this.group = group;
        this.exp = exp;
        this.food = food;
        
        this.score = score; 
        
    }
}

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        StringTokenizer st; 
        
        // info 정보를 클래스 형태로 정리
        Info[] inf = new Info[info.length];
        
        for(int i = 0; i < info.length; i++) { 
            
            st = new StringTokenizer(info[i]); 
            
            inf[i] = new Info(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), 
                             Integer.parseInt(st.nextToken()));
            
        }
        
        Info[] qur = new Info[query.length]; 
        
        String lang, group, exp, food; int score;
        
        for(int i = 0; i < query.length; i++) { 
            
            st = new StringTokenizer(query[i]); 
            
            lang = st.nextToken(); st.nextToken(); // 언어 and
            group = st.nextToken(); st.nextToken(); // 직군 and
            exp = st.nextToken(); st.nextToken(); // 경력 and
            food = st.nextToken(); // 음식
            
            score = Integer.parseInt(st.nextToken()); // 점수 
            
            qur[i] = new Info(lang, group, exp, food, score);
            
            
        }
        
        
        
        return answer;
    }
}
