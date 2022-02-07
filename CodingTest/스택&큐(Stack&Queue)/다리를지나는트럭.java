package stackAndqueue;

import java.util.Queue;
import java.util.LinkedList;



class Truck{
	
	int weight; // 트럭의 무게
	int length; // 지나간 다리의 길이
	
	Truck(int weight){
		this.weight = weight; 
		this.length = 1;
	}
}

public class 다리를지나는트럭 {
    public static int solution(int bridge_length, int weight, int[] truck_weights) {
        
        // 대기 트럭의 무게 정보를 큐에 저장
        Queue<Truck> waitQ = new LinkedList<>();
        
        for(int i = 0; i < truck_weights.length; i++){
        	Truck tru = new Truck(truck_weights[i]);
        	
            waitQ.offer(tru);
        }
        
        int len = waitQ.size();
        
        // 다리
        Queue<Truck> bridge = new LinkedList<>();
        
        // 다리를 다 지난 트럭들 
        Queue<Truck> finished = new LinkedList<>();
        
        int time = 0; int bridgeWeight = 0;
        
        // 맨 처음에 대기줄에 있던 트럭
        Truck truck = waitQ.poll();
        
        while(finished.size() != len) {
        	
        	// 다리가 견딜수 있는 하중 까지 최대한 차들을 다리로 내보낸다.
        	// 다리에 차를 내보낸 횟수를 carCnt라고 하겠다.
        	int carCnt = 0;
        	while(bridgeWeight + truck.weight <= weight) {
        		
        		if(bridge.size() <= bridge_length) {
        			// 트럭이 다리에 들어왔다.
        			bridge.offer(truck);
            		time++;
            		carCnt++;
            		
            		bridgeWeight = bridgeWeight + truck.weight;
            		
            		// 대기 줄에 있는 트럭이 없으면 break한다.
            		if(!waitQ.isEmpty()) {
            			truck = waitQ.poll();
            		}
            		
            		else break;
        		}
        		
        		else break; // 다리에 더 이상 차가 지날 수 없을 때 break.
        	}
        	
        	// 다리에 차를 내보낸 만큼 이미 차들은 다리를 건넜다.
        	for(Truck p: bridge) {
        		p.length += carCnt;
        		carCnt--;
        	}
        	
        	carCnt = 0; 
        	/*for(int i = 0; i < bridge.size(); i++) {
        		bridge.get(i).length += carCnt;
        		carCnt--;
        	}*/
        	
        	// 다리에 오른 차들이 다리를 건너는 시간이 지나야 한다.
        	// time = time + bridge_length;
        	/* for(int i = 0; i < bridge.size(); i++) {
        		bridge.get(i).length++;
        	} */ 
        	
        	
        	if(bridge.peek() != null) {
        		while(bridge.peek().length != bridge_length ) {
            		
            		// 다리에 있는 차들이 한 칸식 전진
                	/*for(int i = 0; i < bridge.size(); i++) {
                		bridge.get(i).length++;
                	}*/
                	
                	for(Truck p: bridge) {
                		p.length++;
                	}
                	
                	time++; // 한 칸씩 전진하면서 time도 증가.
            		
            	}
        	}
        	
        	
        	// 맨 앞에 있는 차는 다리를 완주했으니까 finished에 하나를 추가한다.
        	Truck remove = bridge.poll();
        	
        	if(remove != null) {
        		finished.offer(remove);
            	time++;	
        	}
        	
        	
        	// System.out.println("time = " + time); 
        	
        	// 다리로 다 보냈다면 다리에 있는 차들을 하나씩 빼준다.
        	/* while(!bridge.isEmpty()) {
        		finished.offer(bridge.remove(0));
        		time++;
        	}*/
        	// bridgeWeight = 0; // 다리에 있는 차들을 빼냈으니까 0으로 초기화.
        	// time++;
        }
        
        
        
        return time;
    }
    

    /* public boolean isInBridge(Queue<Integer> bridge){
      
        
       
        
    }*/
	public static void main(String[] args) {
		
		// int[] wait = {10,10,10,10,10,10,10,10,10,10};
		// int[] wait = {10};
		int[] wait = {7, 4, 5, 6};
		
		int bridge_length = 2;
		int weight = 10;
		
		System.out.println(solution(bridge_length, weight, wait));
		

	}
}
