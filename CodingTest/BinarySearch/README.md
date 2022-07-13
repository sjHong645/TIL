## 이진 탐색 

### 기본 알고리즘 

여기서 `탐색의 대상`이 되는 배열 A는 `오름차순으로 정렬`되어야 한다.

기본적인 내용은 target값을 A[mid]와 비교해서 

target < A[mid] 라면 target값은 first ~ mid -1 사이에 있다는 것을 알 수 있다.

target > A[mid] 라면 target값은 mid + 1 ~ last 사이에 있다는 것을 알 수 있다.

이러한 성질을 이용해서 이진 탐색이 이뤄진다. 

``` java
boolean BSearch(int A[], int len, int target) {
		
		int first = 0; 
		int last = len - 1;
		int mid;
		
		while(first <= last) {			
			mid = (first + last) / 2;
      
			if(target == A[mid]) return true;
			
			else {
				
				if(target < A[mid]) last = mid - 1;
				
				else first = mid + 1;
				
			}
		}
		
		return false;

	}

}
```

또는 해당 [출처](/CodingTest/11여러가지문제/카카오기출/level2/메소드/CollectionsBinarySearch.md)를 이용해서 코드를 짜면

A라는 배열에 `target`이 있다면 해당 위치를 반환.  
`target`이 없다면 target이 있어야 할 위치를 반환. 이때 `위치 값`을 `음수`로 바꾸고 -1을 더한다.
``` java
int BSearch(int A[], int len, int target) {
		
	int first = 0; 
	int last = len - 1;
	int mid;
		
	while(first <= last) {			
		mid = (first + last) / 2;
      		if(target == A[mid]) return mid;
			
		else if(target < A[mid]) last = mid - 1;
			
		else first = mid + 1;
		
		}
	}
		
	return -(first + 1);

}
```


### 재귀 알고리즘 활용 

원리는 똑같다. 표현을 다르게 한 것 뿐이다. 

target값을 A[mid]와 비교해서 

target < A[mid] 라면 target값은 first ~ mid -1 사이에 있다는 것을 알 수 있다.

target > A[mid] 라면 target값은 mid + 1 ~ last 사이에 있다는 것을 알 수 있다.

그러다가 first의 값이 last보다 커진다면 값을 찾지 못한 상태에서 탐색을 종료한다.

``` java
boolean BSearchRecur(int A[], int first, int last, int target) { 
 
  if(first > last) return false;
  
  int mid = (first + last) / 2; 
  
  if(target == A[mid]) return true; 
  
  else if(target < A[mid]) return BSearchRecur(A, first, mid - 1, target); 
  
  else return BSearchRecur(A, mid + 1, last, target);  
  
}
```
