## Radix sort(기수 정렬) 

### 과정

#### 1번째 이해 
기수 정렬은 정렬순서상 앞에 있는지 뒤에 있는지를 판단하기 위한 `비교연산을 하지 않는다`.

그리고 시간 복잡도 `O(nlogn)`을 넘을 수 있는 유일한 알고리즘이기도 하다.

하지만, 적용할 수 있는 범위가 `제한적`이라는 단점이 있다.  
예를 들어 다음 문장은 기수 정렬로 해결할 수 있다. 

- 정수배열 1, 7, 9, 5, 2, 6을 오름차순으로 정렬.
- 영단어 red, why, zoo, box를 사전편찬 순서대로 정렬.

반면, 다음 문장은 기수 정렬로 해결할 수 없다.
- 배열에 저장된 21, -9, 125, 8, -136, 45를 오름차순으로 정렬
- 영단어 professionalism, few, hydroxyproline, simple을 사전편찬 순서대로 정렬

⇒ 데이터의 `길이가 서로 같지 않다면` 기수 정렬을 사용하는데 있어서 제한이 있다.  

물론, 길이가 서로 다르면 다른 알고리즘을 사용해서 기수 정렬을 사용할 수 있지만 
굳이 별도의 알고리즘을 고민하고 효율의 문제를 감수하면서 `기수 정렬`을 사용할 필요는 없다.  

이제 `한 자리의 10진수 정수`들을 `기수 정렬`을 이용해서 정렬해보자.

![image](https://user-images.githubusercontent.com/64796257/150620484-c6cca79c-331f-466c-9600-ba275a8b6c67.png)

이렇게 10진수 정수의 정렬을 위해 총 10개의 `버킷`이 필요하다.  
`버킷`은 0부터 9까지 순서대로 이름이 메겨져 있고 정렬대상은 값에 해당하는 버킷으로 이동한다.
만약에 `정렬대상이 4`이면 `버킷 4`로 이동한다.

이렇게 모든 값들이 다 버킷으로 이동했다면 `버킷 0에 저장된 것`부터 시작해서 `버킷 9에 저장된 것`까지 순서대로 값을 꺼내서 나열하면 된다.  
이것이 바로 `기수 정렬의 기본원리`이다.

여기서 `기수(radix)`란 주어진 데이터를 구성하는 `기본 요소`를 의미한다.  

예를 들어, `2진수`는 `0과 1의 조합`으로 데이터를 표현하니 2진수의 `기수는 0과 1`이다.  
         `10진수`는 `0과 9의 조합`으로 데이터를 표현하니 10진수의 `기수는 0 ~ 9`이다.

이러한 `기수`들을 이용한 정렬을 `기수 정렬`이라 한다.

#### 2번째 이해 
이번에는 `3자리 정수`들을 대상으로 `기수 정렬`을 진행해보자. 오름차순으로 정렬할 것이다.  
단, 여기서 정수는 `5진수`이다. 따라서, `버킷의 개수는 5개`가 되겠다.

134, 224, 232, 122

지금부터 보이는 방법을 `LSD(Least Significant Digit) 기수 정렬`이라 한다. `덜 중요한 자릿수부터 정렬을 진행`하는 방식.  
쉽게 말하면 첫 번째 자릿수부터 시작해서 정렬을 진행한다는 의미이다.

##### 1단계 : 첫 번째 자리수 기준으로 정렬 진행

![image](https://user-images.githubusercontent.com/64796257/150620892-92425fac-1e59-4cd1-968c-8efb4cfd6506.png)
![image](https://user-images.githubusercontent.com/64796257/150620893-71e39b79-285f-4d02-b0bc-68cf6fc13f2f.png)

이렇게 하고 나서 버킷 0에서부터 시작해서 데이터를 꺼내자. 따라서, 232, 122, 134, 224 순으로 데이터가 정렬된다.

##### 2단계 : 두 번째 자리수 기준으로 정렬 진행 

![image](https://user-images.githubusercontent.com/64796257/150620920-595fb12b-b411-4b5b-bd6d-965f1cac15d3.png)
![image](https://user-images.githubusercontent.com/64796257/150620923-0d55417a-d817-4899-ae45-f33a9ae711eb.png)

똑같이 버킷 0에서부터 시작해서 데이터를 꺼내면 122, 224, 232, 134 순으로 데이터가 정렬된다.

##### 3단계 : 세 번째 자리수 기준으로 정렬 진행 

![image](https://user-images.githubusercontent.com/64796257/150620948-e3b9e174-d2c9-45d7-9324-354addd406ed.png)
![image](https://user-images.githubusercontent.com/64796257/150620949-02af8c85-440c-4e71-80c6-8b002c16d4d5.png)

여기서 잊지 말아야 할 내용이 있다.  
`1단계에서 정렬한 결과`를 `2단계에서 두 번째 자릿수를 기준으로 정렬`하고  
`2단계에서 정렬한 결과`를 `3단계에서 세 번째 자릿수를 기준으로 정렬`하는 과정이라는 걸 알아두자. 

이로써 오름차순이 정렬되었다.  
이 방법의 단점은 `작은 자릿수`부터 시작해서 `가장 큰 자릿수`까지 `모두 비교를 해야` 값의 대소를 판단한다.  

즉, 비교 중간에 대소를 판단할 수 없다는 단점이 있다. 하지만 이러한 단점이 프로그래밍을 하는데 있어서 장점이 된다.

### LSD vs MSD 
이제 `MSD`에 대해서 알아보자. `MSD : Most Significant Digit`의 약자로써 `가장 큰 자릿수`부터 정렬이 진행되는 방법이다.

`MSD`는 가장 큰 자릿수부터 정렬이 시작하다보니 LSD와 달리 첫 번째 과정만 거쳐도 대략적인 정렬의 결과가 눈에 보인다. 
따라서, MSD의 가장 큰 장점은 반드시 끝까지 가지 않아도 `중간에서 정렬을 완료`할 수 있다는 점이다.

하지만 이렇다보니 모든 데이터를 일괄적인 과정을 통해 정렬할 수 없다는 단점이 생긴다. 예시를 들어보자.

![image](https://user-images.githubusercontent.com/64796257/150621114-f11956f5-f88b-4fcf-84b0-0ecb77a167e6.png)

위의 그림은 잘못된 결과를 나타내는 그림이다.  
`첫 번째 정렬`을 통해서 224, 232가 정렬이 완료되었기에 여기서 정렬을 멈췄어야 했다. 

하지만, 멈추지 않고 `3번째 자릿수`를 기준으로 또 다시 정렬을 실행해서 잘못된 결과를 초래했다.
반면, 134, 122는 정렬을 계속 진행해야 한다.

이러한 오류를 범하지 않으려면 `MSD 방식`은 `중간에 데이터를 점검`해야 한다. 따라서 구현의 난이도가 LSD에 비해 높다.  
게다가 중간에 데이터를 점검해야 하므로 성능의 이점도 반감될 수 있다.

### 구현

일반적으로 기수 정렬은 `LSD 방식`을 기준으로 얘기한다. 구현의 편의성 때문이다. 그리고 MSD와도 성능 차이가 별로 없다.

`양의 정수`라면 길이에 상관없이 정렬의 대상에 포함시킬 수 있는 기수 정렬을 구현해보자. 

예를 들어 42, 715를 정렬한다고 하면 

`LSD 방식`이기 때문에 처음에는 `2`와 `5`를 기준으로 ⇒ 두 번째는 `4`와 `1`을 기준으로 ⇒ 마지막에는 `0`과 `7`을 기준으로 정렬을 진행한다.  
그렇다면 양의 정수에서 1,2,3번째 숫자를 어떻게 뽑아올까.

- NUM으로부터 1번째 자리 숫자 추출 (NUM / 1) % 10
- NUM으로부터 2번째 자리 숫자 추출 (NUM / 10) % 10
- NUM으로부터 3번째 자리 숫자 추출 (NUM / 100) % 10   
cf) 나눗셈의 결과는 정수 1, 10, 100으로 나눴기 때문에 정수값이 나온다. ex. 715 / 100 = 7

이제 구현하자. 참고로 버킷은 구조가 큐에 해당하기 때문에 `연결 리스트 기반의 큐`를 활용했다.

``` java
package sorts;

import free.LinkedQueue;

class RadixSort {
	
	
	public void sort(int arr[]) {
		int maxLen = getMaxLen(arr);
		radixsort(arr, arr.length, maxLen);
	}
	
	// 배열 안에 있는 원소 중 가장 긴 길이값 찾기
	private int getMaxLen(int arr[]) {
		int maxLen = 0;
		int temp; int cnt;
		
		for(int i = 0; i < arr.length; i++) {
			temp = arr[i];
			cnt = 0;
			while(true) {
				temp = temp / 10;
				cnt++;
				if(temp == 0) break;
				
			}
			if(maxLen < cnt) maxLen = cnt;
		}
		return maxLen;
	}

	private void radixsort(int arr[], int num, int maxLen) {
	// 연결리스트 큐가 배열의 각 원소의 자료형이다.
	  LinkedQueue[] buckets = new LinkedQueue[10];
	  
	  // bi = bucket index
	  // di = data index
	  // divfac = 나눌 인자(1, 10, 100, 1000...)
	  int bi; int pos; 
	  int di; int divfac = 1; 
	  
	  int radix;
	  
	  for(bi = 0; bi < 10; bi++) {
		  buckets[bi] = new LinkedQueue();
	  }
	  
	  for(pos = 0; pos < maxLen; pos++) {
		  for(di = 0; di < num; di++) {
			  
			  radix = (arr[di] / divfac) % 10;
			  
			  buckets[radix].enqueue(arr[di]);
		  }
	  
	  
	  for(bi = 0, di = 0; bi < 10; bi++) {
		  while(!buckets[bi].isEmpty()) {
			  arr[di++] = buckets[bi].dequeue();
		  }
	  }
	  
	  divfac *= 10;
	  }
	} 
  
  public static void main(String[] args) {
	RadixSort radixsort = new RadixSort();
	
    int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
    
    radixsort.sort(arr);
    
    for(int i = 0; i < arr.length; i++) {
    	System.out.print(arr[i] + " ");
    }
    
    // 결과 : 2 24 45 66 75 90 170 802 
  }
}
```
### 성능 평가

`기수 정렬`의 핵심연산은 버킷에다가 `데이터를 삽입`하고 `추출`하는 동작이다.  
따라서 시간 복잡도는 `삽입`과 `추출`의 빈도수를 대상으로 결정해야 한다.

`for(di=0; di<num; di++)` ⇒ 여기서 일어나는 반복문은 `삽입의 과정`을 반복하고
`for(bi=0, di=0; bi<BUCKET_NUM; bi++)` ⇒ 여기서 일어나는 반복문은 `추출의 과정`을 반복한다.

이 두 과정에서 일어나는 연산을 한 쌍으로 묶으면 `num`이라 할 수 있다.  
(`2 * num`이라고도 할 수 있지만 Big-O를 계산하는 과정에서는 2가 큰 의미가 없음)

그걸 `maxLen` 만큼 반복하기 때문에 `maxLen * num` 만큼의 연산횟수가 되겠다.

따라서, 정렬대상의 수를 `n`이라 하고 `정렬대상의 길이를 l`이라 하면 시간 복잡도에 대한 Big-O는 O(ln)이 돼서 `O(n)`이라 할 수 있다. 

## Counting sort(계수 정렬) 

일단 계수정렬을 하기 위해서는 조건이 2가지가 있다. 정렬하고자 하는 자료를 `배열 A`라 하자.
1) 배열 A의 원소들은 모두 `자연수`여야 한다. (만약에 배열의 index를 0부터 시작한다면 0도 포함할 수 있다)
2) `배열의 최댓값`은 `최대 k`여야 한다. 

### 과정

#### 1단계 : 배열 안에 있는 원소 값들의 개수를 저장하는 `Counting Array`를 만든다. 줄여서 `CA`라 하겠다.
![image](https://user-images.githubusercontent.com/64796257/150627389-f8eb7bb4-8ae8-4913-b93c-dba906a9da8b.png)

예시에서 제시한 배열의 모든 원소의 값들은 `5보다 작거나 같다`. 그래서 배열의 `길이가 6`인 `Counting array`를 만들었다.

가장 먼저 `1`이 검색된다. 그러면 `CA[1]의 값`을 `1 추가`해서 CA[1] == 1이 되었다.  
그 다음, `0`이 검색된다. ==> `CA[0]의 값`을 `1 추가`해서 CA[0] == 1이 되었다.  
그 다음, `1`이 검색된다. ==> `CA[1]의 값`을 `1 추가`해서 CA[1] == 2가 되었다.  
그 다음, `5`가 검색된다. ==> `CA[5]의 값`을 `1 추가`해서 CA[5] == 1이 되었다.  

이런 식으로 배열을 모두 참조해서 해당 숫자가 i이면 `CA[i]의 값`을 `1씩 추가하는 방식`으로 CA의 원소들을 채워나간다.

#### 2단계 : CA에 있는 원소에 직전 요소들의 값을 더해준다.
![image](https://user-images.githubusercontent.com/64796257/150627579-351b46f2-d20a-4f00-bfd0-5645b6b344ea.png)

#### 3단계 : 입력한 배열과 동일한 크기의 출력 배열 B를 만들고 입력 배열의 역순으로 출력배열에 원소들을 채워준다.
![image](https://user-images.githubusercontent.com/64796257/150627593-4b630722-0366-438f-96de-83063cb3f5ee.png)

맨 뒤에 있는 `1`을 보자. `CA[1] == 5`이다. 그래서 `1`은 `B[5]`에 대입하고 `CA[1]`의 값을 `1 빼준다`.
그 앞에 있는 `2`를 보자. `CA[2] == 6`이다. 그래서 `2`는 `B[6]`에 대입하고 `CA[2]`의 값을 `1 빼준다`.
그 앞에 있는 `5`를 보자. `CA[5] == 11`이다. 그래서 `5`는 `B[11]`에 대입하고 `CA[5]`의 값을 `1 빼준다`.

### 구현

``` java
package sorts;

import java.util.Arrays;

class CountingSort {
         
         // A의 길이가 M이라면 
         // results 배열의 길이는 M+1이여야 한다. 
         // 왜냐하면, results 배열은 정수 0 뿐만 아니라 최대값도 저장해야 하기 때문이다.
	void countingSort(int A[], int results[]) {
		
		// 입력한 배열 A의 최댓값
		int Amax = Arrays.stream(A).max().getAsInt();
		
		// 최댓값이 5라면 0~5까지의 index는 있어야 하니까
		// 배열의 길이가 5+1 = 6이어야 한다.
		int[] CA = new int[Amax + 1]; 
		
		// 과정 1
		for (int i = 0; i < A.length; i++) {
			CA[A[i]]++;
		}
		
		// 과정 2 
		for(int i = 0; i < CA.length - 1; i++) {
			CA[i+1] = CA[i+1] + CA[i];
		}
		
		// 과정 3 
		for(int i = A.length - 1; i >= 0; i--) {
			results[CA[A[i]]] = A[i];
			CA[A[i]]--;
		}
	}

	
	public static void main(String[] args) {
		
		int[] example = {2, 4, 6, 3, 1, 3, 3, 6, 7, 9, 0, 4, 6, 5, 5};
		int[] results = new int[example.length + 1];
		
		Arrays.fill(results, 0);
		
		CountingSort cs = new CountingSort();
		
		cs.countingSort(example, results);
		
		for(int i = 0; i < results.length; i++) {
			System.out.print(results[i] + " ");
		}
	}
}

```

### 성능 평가

1),3)번 과정은 k번 반복하므로 Θ(k)의 시간복잡도가 필요. 2)번 과정은 삽입 과정을 n번 반복하므로 Θ(n).

따라서, 최악의 경우에는 Θ(n)의 시간복잡도가 필요하다는 것을 알 수 있다.


