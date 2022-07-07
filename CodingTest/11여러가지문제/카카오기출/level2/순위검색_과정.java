// 참여 개발 언어 - cpp, java, python : 1, 2, 3
// 지원 직군 - backend, frontend : 1, 2
// 경력 - junior, senior : 1, 2
// 음식 - chicken, pizza : 1, 2
// ex) 2112 - java로 코테를 보고 backend에 지원. 경력은 junior, 음식은 chicken

// 코딩테스트 점수 - List로 저장

import java.util.List;
import java.util.ArrayList; 

import java.util.Map;
import java.util.HashMap;

import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        // Map<Integer, List<Integer>> inform = new HashMap<>();
        Map<Integer, List<Integer>> inform = new HashMap<>();
        
        StringTokenizer st; 
        
        // info에 있는 내용 처리
        int keyNum = 0; int score = 0;
        for(int i = 0; i < info.length; i++) { 
            st = new StringTokenizer(info[i]);
            
            // 언어, 직군, 경력, 음식
            keyNum = makeNumber(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken());
            
            // 코딩테스트 점수 
            score = Integer.parseInt(st.nextToken());
            
            // 아직 keyNum에 대해 정의된 게 없을 때
            if(!inform.containsKey(keyNum)) { 
                inform.put(keyNum, new ArrayList<>());
                inform.get(keyNum).add(score);
            }
            
            // 정의된 게 있을 때
            else { 
                inform.get(keyNum).add(score);
            }
            
        }
                
        // query 
        String lang, group, career, food; int std;
        int queryNum = 0; 
        
        int first, second, third, fourth;
        for(int i = 0; i < query.length; i++) { 
            
            st = new StringTokenizer(query[i]);
            
            lang = st.nextToken(); st.nextToken();
            group = st.nextToken(); st.nextToken();
            career = st.nextToken(); st.nextToken();
            food = st.nextToken(); 
            
            queryNum = makeNumber(lang, group, career, food);
            
            std = Integer.parseInt(st.nextToken());
            
            // 해당하는 조건에 정확히 맞는 사람이 있을 때
            if(inform.containsKey(queryNum)) {
                
                for(int e : inform.get(queryNum)) {
                    if(e >= std) answer[i]++;
                }
                
            }
            
            else { 
                String queryString = Integer.toString(queryNum);
                
                // queryNum의 첫 번째 자리
                first = queryString.charAt(0) - '0';
                // queryNum의 두 번째 자리
                second = queryString.charAt(1) - '0';
                // queryNum의 세 번째 자리
                third = queryString.charAt(2) - '0';
                // queryNum의 네 번째 자리
                fourth = queryString.charAt(3) - '0';
                
                // 4를 포함하지 않은 경우
                if(first != 4 && second != 4 && third != 4 && fourth != 4) answer[i] = 0;
                
                // 4를 포함한 경우 
                else {
                    for(int k : inform.keySet()) {
                    
                    // 
                    
                    }
                }
                
                
                
                
                
                
            }
            
        }
        
        return answer;
    }
    
    static int makeNumber(String lang, String group, String career, String food) { 
        
        int number = 0; 
        
        // 언어
        if(lang.equals("java")) number += 1000; 
        else if(lang.equals("cpp")) number += 2000; 
        else if(lang.equals("python")) number += 3000;
        else number += 4000; 
        
        // 직군
        if(group.equals("backend")) number += 100; 
        else if(group.equals("frontend")) number += 200;        
        else number += 400;
        
        // 경력 
        if(career.equals("junior")) number += 10; 
        else if(career.equals("senior")) number += 20;  
        else number += 40;
        
        // 음식 
        if(food.equals("chicken")) number += 1; 
        else if(food.equals("pizza")) number += 2;        
        else number += 4;
                
            
        return number;
        
    }
}
