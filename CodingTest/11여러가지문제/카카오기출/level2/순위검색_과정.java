// 참여 개발 언어 - cpp, java, python : 1, 2, 3
// 지원 직군 - backend, frontend : 1, 2
// 경력 - junior, senior : 1, 2
// 음식 - chicken, pizza : 1, 2
// ex) 2112 - java로 코테를 보고 backend에 지원. 경력은 junior, 음식은 chicken

// 코딩테스트 점수 - List로 저장

// 나의 생각
// 5만개나 되는 모든 정보를 찾게 하지 않고 24개의 key만 반복적으로 찾게 하자!!!

// 하지만, 시간이 많이 걸린다.

// 원하는 점수 이상의 점수를 가진 사람들이 몇 명인지에 대한 코드를 고쳐야 하나.

import java.util.List;
import java.util.ArrayList; 

import java.util.Collections;

import java.util.Map;
import java.util.HashMap;

import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        // Map<Integer, List<Integer>> inform = new HashMap<>();
        Map<String, List<Integer>> inform = new HashMap<>();
        
        StringTokenizer st; 
        
        // info에 있는 내용 처리
        String keyNum; int score = 0;
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
            else { inform.get(keyNum).add(score); }
            
        }
        
        // key에 해당하는 리스트 정렬
        for(String key : inform.keySet()) { Collections.sort(inform.get(key)); }
        
                
        // query 
        String lang, group, career, food; int std;
        String queryNum; 
        
        char first, second, third, fourth;
        for(int i = 0; i < query.length; i++) { 
            
            st = new StringTokenizer(query[i]);
            
            lang = st.nextToken(); st.nextToken();
            group = st.nextToken(); st.nextToken();
            career = st.nextToken(); st.nextToken();
            food = st.nextToken(); 
            
            queryNum = makeNumber(lang, group, career, food);
            
            std = Integer.parseInt(st.nextToken());
            
            /*System.out.println("queryNum = " + queryNum);
            System.out.println("기준 점수 = " + std); */
            
            // 해당하는 조건에 정확히 맞는 사람이 있을 때
            if(inform.containsKey(queryNum)) {
                
                // 숫자 찾는데서 시간을 많이 잡아먹는다.
                for(int e : inform.get(queryNum)) { 
                    if(e >= std) {
                        answer[i] = inform.get(queryNum).size() - inform.get(queryNum).indexOf(e);
                        break;
                    }
                }
                
                
            }
            
            else { 
                
                // queryNum의 첫 번째 자리
                first = queryNum.charAt(0);
                // queryNum의 두 번째 자리
                second = queryNum.charAt(1);
                // queryNum의 세 번째 자리
                third = queryNum.charAt(2);
                // queryNum의 네 번째 자리
                fourth = queryNum.charAt(3);
                
                // 0를 포함하지 않은 경우
                if(first != '0' && second != '0' 
                && third != '0' && fourth != '0') answer[i] = 0;
                
                // 0를 포함한 경우 
                else {
                    
                    for(String k : inform.keySet()) {
                    
                        // k의 1,2,3,4번째 숫자
                        // k.charAt(0); k.charAt(1); k.charAt(2); k.charAt(3); 

                        if((k.charAt(0) == first || first == '0') &&
                           (k.charAt(1) == second || second == '0') && 
                           (k.charAt(2) == third || third == '0') && 
                           (k.charAt(3) == fourth || fourth == '0')) { 
                            
                            // 숫자 찾는데서 시간을 많이 잡아먹는다.                      
                            for(int e : inform.get(k)) { 
                                if(e >= std) {
                                    answer[i] += inform.get(k).size() - inform.get(k).indexOf(e);
                                    break;
                                }
                            }

                        }
                    }
                }                
            }
            
        }
        
        
            
        
        
        return answer;
    }
    
    static String makeNumber(String lang, String group, String career, String food) { 
        
        char[] number = new char[4];
        // String number = 0; 
        
        // 언어
        if(lang.equals("java")) number[0] = '1';
        else if(lang.equals("cpp")) number[0] = '2';
        else if(lang.equals("python")) number[0] = '3';
        else number[0] = '0'; 
        
        // 직군
        if(group.equals("backend")) number[1] = '1'; 
        else if(group.equals("frontend")) number[1] = '2';   
        else number[1] = '0';
        
        // 경력 
        if(career.equals("junior")) number[2] = '1'; 
        else if(career.equals("senior")) number[2] = '2';
        else number[2] = '0';
        
        // 음식 
        if(food.equals("chicken")) number[3] = '1';
        else if(food.equals("pizza")) number[3] = '2';
        else number[3] = '0';
                
            
        return String.valueOf(number);
        
    }
}
