## Heap Sort(힙 정렬) 

[해당 내용](https://github.com/sponbob-pat/TIL/blob/main/CS/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98%26%EC%9E%90%EB%A3%8C%EA%B5%AC%EC%A1%B0/Heap.md)

## Merge Sort(병합 정렬) 

### 과정 
기본 원리는 이렇다. 
“8개의 데이터를 동시에 정렬하는 것보다, 이를 둘로 나눠서 4개의 데이터를 정렬하는 것이 더 쉽고, 
4개의 데이터들을 다시 한번 둘로 나눠서 2개의 데이터를 정렬하는 것이 더 쉽다.”

이를 그림으로 표현하면 다음과 같다.  
![image](https://user-images.githubusercontent.com/64796257/150488115-c77a7dcd-86f4-4e60-bd21-f39b3e0bf275.png)

왼쪽 그림은 `오름차순 정렬`을 기준으로 `병합 정렬`의 원리를 설명하고 있다.

병합 정렬은 데이터가 `1개`만 남을 때까지 분할한다. 

데이터가 2개만 남아도 정렬할 필요가 있지만 데이터가 1개만 있다면 더 이상의 정렬은 필요없다.

무슨 말일까. 다음 그림을 보자.

ex)  
![image](https://user-images.githubusercontent.com/64796257/150488288-d98254a6-1af9-41e3-ac5e-0d54695f5596.png)

우선은 `분할`의 과정을 거친다. 전체 데이터를 둘로 나누면서 하나씩 구분될 때까지 분할한다.

총 8개의 데이터를 3번에 거쳐서 두 부분으로 나누면 데이터가 하나씩 구분된다. 분할을 할 때는 정렬을 고려하지 않고 그저 분할만 하면 된다.

분할이 완료되었다면 이제 `병합을 진행`할 차례이다.

우선 나뉘었던 둘을 하나로 묶는다. `8과 2`를 `2,8` 로 묶고 `3과 7`을 `3,7`로 묶는다. 이를 전체으로 보면 묶는 과정 1번이다.  
따라서 총 `3번의 묶는 과정`을 거친다. 이렇게 되면 정렬이 완료된다.

그렇다면 분할의 과정에서 하나씩 구분될 때까지 둘로 나누는 과정을 반복하는 이유는 뭘까? 처음부터 하나씩 구분하면 안될까?  
라는 질문을 할 수 있는데 그 이유는 다음과 같다.

⇒ 바로 `재귀적 구현`을 위한 것이다.  
그래서 이 페이지의 가장 위에 있는 그림이 `병합 정렬`을 실질적으로 구현하는데 더 가까운 그림이라 할 수 있다.

### 구현
``` java
  
  // 정렬하고 싶은 배열을 매개변수로 전달한다. 
  public <T extends Comparable<T>> T[] sort(T[] unsorted) {
  
    // 정렬을 하고 나서 배열을 return 한다.
    // 배열할 영역은 0부터 (배열의 길이)-1이다. 
    doSort(unsorted, 0, unsorted.length - 1);
    return unsorted;
  }

  /**
   * @param arr the array to be sorted.
   * @param left the first index of the array.
   * @param right the last index of the array.
   */
  private static <T extends Comparable<T>> void doSort(T[] arr, int left, int right) {
    if (left < right) {
      int mid = (left + right) >>> 1; // 중간 지점을 계산한다.
                                      // (left + right)라는 값을 2로 나눈다는 뜻.(1비트 만큼 오른쪽으로 옮겼으니까)
                                      
      // arr라는 배열을 둘로 나눠서 각각 정렬을 진행한다.
      doSort(arr, left, mid); // left ~ mid 부분 정렬
      doSort(arr, mid + 1, right); // (mid + 1) ~ right 부분 정렬
      
      // 정렬된 두 배열을 병합한다.
      merge(arr, left, mid, right);
    }
  }

  /**
   * Merges two parts of an array.
   *
   * @param arr : 병합할 배열
   * @param left : 배열의 첫 번째 인덱스
   * @param mid : 배열의 중간 인덱스
   * @param right : 배열의 마지막 인덱스(the last index of the array merges two parts of an array in increasing order).
   */
   
  private static <T extends Comparable<T>> void merge(T[] arr, int left, int mid, int right) {
    int length = right - left + 1; // 배열의 길이를 length에 저장
    @SuppressWarnings("unchecked")
    T[] temp = (T[]) new Comparable[length]; // 정렬된 배열을 저장할 임시 저장소 
    int k = 0; // temp 배열에서 데이터가 들어있는 인덱스 값
    int i = left; 
    int j = mid + 1;
    
    // i와 j가 각 부분에 있는 동안 동작을 수행한다.
    while (i <= mid && j <= right) {
    
      // i번째 값이 j번째 값을 서로 비교해서 
      // 작은 값을 temp에 넣어주고 k를 1증가시킨다.
      // i번째 값이 작았다면 i를 1 증가시키고
      // j번째 값이 작았다면 j를 1 증가시킨다.
      if (arr[i].compareTo(arr[j]) <= 0) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }
    
    // 아래의 두 while문은 각 부분의 나머지 값들을 전부 temp 배열에 넣어주는 동작을 한다. 
    while (i <= mid) {
      temp[k++] = arr[i++];
    }

    while (j <= right) {
      temp[k++] = arr[j++];
    }
    
    // 그렇게 temp의 0번째 값부터 length 길이만큼의 부분에 있는 값들을
    // arr의 left번쨰 값부터 length만큼 복사한다.
    System.arraycopy(temp, 0, arr, left, length);
  }

```
여기서 merge 메소드에 대해 좀 더 자세히 살펴보겠다. 변수 선언 부분을 그림으로 표현하면 아래와 같다.  
![image](https://user-images.githubusercontent.com/64796257/150494059-4460cc2e-0463-46d1-a8b0-51555dfb026a.png)

합치는 과정은 다음과 같다. 

![image](https://user-images.githubusercontent.com/64796257/150494263-3d2c505f-8fd2-4038-a0b9-c76a8d42e2ff.png)

`왼쪽 부분`과 `오른쪽 부분`의 `가장 왼쪽`을 가리키는 것부터 시작한다. 각 부분의 왼쪽은 i와 j로 가리킨다.  
3과 1을 비교해서 `작은값 1`을 temp 배열에 넣는다.

1 다음에는 2가 있으니 2를 가리킨다. 3과 2를 비교해서 `작은값 2`를 temp 배열에 넣는다.

이런식으로 정렬하다가 한 부분에 있는 데이터를 모두 temp에 넣었다면 다른 한쪽에 있는 데이터의 나머지 원소들을 전부 순서대로 temp에 넣어준다.  
이러한 과정으로 mergesort가 진행된다.

### 성능 평가 

- 시간 복잡도   
![image](https://user-images.githubusercontent.com/64796257/150495370-ac84e627-12a6-47b7-a7cc-27dec2a87fc4.png)

의사코드를 통해서 재귀적인 시간복잡도 식을 세울수 있다.

이때, 시간 복잡도식은 마스터 정리를 쉽게 사용할 수 있는 형태이므로 마스터 정리를 이용해서 쉽게 시간 복잡도를 구할 수 있다.

- 공간 복잡도  
![image](https://user-images.githubusercontent.com/64796257/150495451-8973b081-b75f-47d0-81c8-2b5065c42c23.png)

데이터를 저장하는 input 배열인 A 배열의 크기 n / 임시로 데이터를 저장할 배열 temp의 크기 n

그래서 공간 복잡도는 S(n) = Θ(n) 이 된다. 

## Quick Sort 

[출처](https://github.com/sponbob-pat/Java/blob/master/src/main/java/com/thealgorithms/sorts/QuickSort.java) 

헷갈리지 말아야 할 내용
- while문 : 조건문이 `참(true)`일 때 블록 안에 있는 문장을 수행하고 조건문이 `거짓(false)`이 되는 순간 while 문 동작을 멈춘다.
- less 함수 

| 상황 | 리턴 값 | less 함수의 return값 |
| --- | --- | ---|
| x < y | -1 | true | 
| x == y | 0 | false |
| x > y | 1 | false |
### 과정 

[3, 2, 4, 1, 7, 6, 5] 을 퀵 정렬한다고 하자. 

변수 설정 
- left : 배열 가장 왼쪽 index
- right : 배열 가장 오른쪽 index
- mid : left와 right의 중간값 ⇒ `(left+right) >>> 1` 로 계산 
- pivot : array[mid] 값

이 변수들을 표시해서 직접 써보면 아래와 같다. 

![image](https://user-images.githubusercontent.com/64796257/150547641-28431f26-36c1-4dab-8f0d-45de8cbd6c09.png)

어떻게 정렬되는지 하나씩 살펴보겠다. 

left = 0 <= right = 6 을 만족하기 때문에 left와 right을 각각 1씩 증가/감소 시킬 수 있다. 

단, `left`를 `1씩 증가`시키 위해서는 `array[left]`의 값이 `pivot보다 작아야`한다.  
`right`를 `1씩 감소`시키기 위해서는 `pivot`의 값이 `array[right]보다 작아야` 한다.

단 한 번이라도 조건을 만족하지 않으면 left와 right는 그곳에 머문다. 그러면 다음과 같이 left와 right가 각 배열에서 값을 가진다. 

![image](https://user-images.githubusercontent.com/64796257/150617862-b78d613d-86a4-4ad0-9f7e-ccd54b77e72b.png)

이때, left와 right가 left <= right 조건을 만족하므로 해당 인덱스에 있는 값을 서로 교환하고 left와 right를 각각 1씩 증가/감소시킨다. 

여전히 `pivot값`은 기존의 값이었던 `1`이다. 

![image](https://user-images.githubusercontent.com/64796257/150617907-4c5b7a93-2d93-409d-81d4-1f9a135d396d.png)

`array[left]`는 `2`이기 때문에 조건을 만족하지 않아서 left의 변화는 없다.
`array[right]`는 조건을 만족하기 때문에 계속해서 `right--` 연산을 진행한다.  
그러다가 `array[right] = pivot = 1` 일 때 즉, `right = 0` 일 때 멈춘다.

![image](https://user-images.githubusercontent.com/64796257/150617953-84102cb1-a6da-48b9-ab7b-df24616799f0.png)

이렇게 되면 `left <= right 조건`을 만족하지 않기 때문에 return 값으로 left값을 반환한다. 이 값이 바로 `pivot`이 된다.  
이렇게 정해진 pivot을 기준으로 `left ~ (pivot-1)` 부분과 `pivot ~ right` 부분에 재귀적으로 퀵 정렬을 수행한다.

![image](https://user-images.githubusercontent.com/64796257/150617991-73c2bfb4-652b-435e-ab41-566384d3fd04.png)

### 구현 
``` java
import static sorts.SortUtils.*;

class QuickSort implements SortAlgorithm {

    @Override
    // main 함수에서 사용할 sort 함수. 매개변수로 배열만 전달하면 된다. 
    public <T extends Comparable<T>> T[] sort(T[] array) { 
        doSort(array, 0, array.length - 1);
        return array;
    }
    
    // array 배열의 left 부터 right 까지의 정렬을 진행한다.
    private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
        if (left < right) {
            int pivot = partition(array, left, right); // array 배열의 left에서 right까지의 범위 중 기준점 pivot을 정한다.
            doSort(array, left, pivot - 1); // array 배열의 left ~ pivot -1 까지 정렬
            doSort(array, pivot, right);    // array 배열의 pivot ~ right 까지 정렬
        }
    }
 
    private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
        int mid = (left + right) >>> 1; // left와 right의 중간값을 mid에 저장
        T pivot = array[mid];           // pivot은 array[mid]로 설정

        // left <= right 조건을 만족하는 동안 계속 while문 실행
        while (left <= right) {
        
            // array[left]의 값이 pivot보다 작다면 계속 while문 실행
            // 한 번이라도 조건을 만족하지 않으면 멈춘다.
            while (less(array[left], pivot)) { 
                left++;
            }
            // pivot의 값이 array[right]보다 작다면 계속 while문 실행
            // 한 번이라도 조건을 만족하지 않으면 멈춘다.
            while (less(pivot, array[right])) {
                right--;
            }
            
            // left <= right를 만족할 때 array[left]의 값과 array[right]의 값을 서로 바꾼다.
            // 그러고 나서 left와 right를 각각 1씩 증가/감소시킨다.
            if (left <= right) {
                swap(array, left, right);
                left++;
                right--;
            }
        }
        // 최종적인 left값이 pivot값이 된다.
        return left;
    }

    // Driver Program
    public static void main(String[] args) {

    	QuickSort quickSort = new QuickSort();
      
      Integer[] intArray = {3, 2, 4, 1, 7, 6, 5};
      quickSort.sort(intArray);
        
      print(intArray);
    }
}
```

### 성능평가 

pivot이 결정되면 `left는 오른쪽`으로 `right는 왼쪽`으로 이동한다.  
이때 이동의 과정에서 `left`와 `right`는 `pivot값과 비교`하면서 이동 여부가 결정된다.  

그래서 `데이터의 수가 n`이라 할 때 하나의 pivot이 제 자리를 찾아가는 과정에서 발생하는 `비교연산의 횟수는 O(n)`이라 할 수 있다. 

pivot에 의해 반으로 나뉘어진 상황에서도 `left`와 `right`는 각각 `pivot과의 비교연산`이 진행된다.  
pivot을 제외한 나머지 데이터가 `n`이라 할 때 `비교연산의 횟수 역시 n`이라 할 수 있다.

그렇다면 이러한 비교횟수 n번이 총 몇 번 일어날까?? 

31개의 데이터가 있다고 가정해보자.  
이때 둘로 나뉘는 횟수를 k라 할 때 데이터수 n에 대해 `k = log_2(n)` 라는 관계를 가진다고 한다.

따라서, `퀵 정렬의 Big-O`는 `O(nlog_2(n))`이 된다고 할 수 있다.   
퀵 정렬의 경우에는 최선에 가까운 성능을 평균적으로 보이기 때문에 굳이 최악의 경우에 대한 Big-O를 따지지 않는다.

이렇듯 퀵 정렬은 `O(nlog_2(n))`의 복잡도를 갖는 다른 정렬 알고리즘과 비교했을 때에도 평균적으로 제일 빠른 것으로 알려져 있다.  

데이터 이동횟수가 상대적으로 적고 별도의 메모리 공간을 요구하지 않기 때문이다.

## 다양한 정렬방식 비교 & 비교정렬의 한계 

### 비교 
1) 대체적으로는 `quick sort`를 많이 사용한다. 왜냐하면, 이 방법이 평균적으로 time cost를 가장 덜 사용하기 때문이다.

2) 만약에 정렬하고자 하는 대상이 `연결 리스트`로 이루어져 있다면 `merge sort`가 더 좋다.

3) `O(nlog_2(n))`의 시간복잡도와 O(1)의 extra space를 보장해야 한다면 `heap sort`를 사용해야 한다.  
왜냐하면 다른 sort 방식들은 `최악의 경우` 시간복잡도와 공간복잡도가 더 커질 수 있기 때문에다.

그리고 모든 자료가 아닌 `가장 큰 자료 k개만 필요`하다면 이때도 역시 `heap sort`를 사용하는 것이 더 좋다.

이와 같이 `비교`를 기반으로 정렬이 이뤄지는 `비교 정렬(Comparsion Sort)`은 최악의 경우 `O(nlog_2(n))`을 밑돌 수 없다.  
그런데 진짜로 그럴까? 비교정렬이 진짜로 그런 한계를 가지고 있는지 파악해보자.

![image](https://user-images.githubusercontent.com/64796257/150618624-4074c2f3-11c6-4a1b-8adc-1e9d363cc67e.png)

a,b,c는 서로 중복되지 않는다고 하자. sorting 알고리즘들을 일반화해서 표현한 트리가 위 그림이다.

위 트리를 통해 알 수 있는 사실은 `비교를 통한 정렬`을 하게 되면 `최악의 경우`에 `트리의 높이만큼` 비교연산이 이뤄진다는 것이다. 

만약에 중복되지 않은 자료 3개를 정렬한다고 하면 최대 3번의 비교연산이 필요하다. 
==> 3개의 자료를 가지고 결정 트리를 만들었을 때 결정 트리의 높이는 ![image](https://user-images.githubusercontent.com/64796257/150618834-d36b068c-3f90-4cb6-8d87-846213eeacb1.png)
으로 결정된다.

즉, `n개의 자료`를 가지고 결정트리를 만들면 단말 노드는 `n!`개 만큼 생성되고 이에 따른 결정 트리의 높이는 ![image](https://user-images.githubusercontent.com/64796257/150618863-2b7d0666-151e-4894-b224-1c74350c09cc.png)
 이 된다. 왜 이런 관계가 성립하는지 따져보자. 
 
![image](https://user-images.githubusercontent.com/64796257/150618879-ac3c266d-7667-489a-9f05-99979a21098f.png)
 
완전 이진 트리에서 `높이를 h` 라 할 때 
`h=0`이면, 그 높이의 단말 노드 개수는 `2^0 = 1`
`h=1`이면, 그 높이의 단말 노드 개수는 `2^1 = 2`   
`h=2`이면, 그 높이의 단말 노드 개수는 `2^2 = 4`  

따라서, h=n이면 단말 노드의 개수는 `2^n`이 된다. 

정리하면 단말 노드의 개수가 `2^n`이면 높이는 `n` <=> 단말 노드의 개수가 `n`이라면 높이는 `log_2(n)`이 된다.

앞서 언급했듯이 `트리의 높이`에 따라서 자료의 최악의 경우의 비교횟수가 결정된다고 했다. 

`n개의 자료`를 정렬할 때 만들어지는 결정 트리의 높이는 ![image](https://user-images.githubusercontent.com/64796257/150618863-2b7d0666-151e-4894-b224-1c74350c09cc.png)이다. 이 식을 가지고 비교횟수의 최악의 경우를 아래와 같이 계산해볼 수 있다. 

![image](https://user-images.githubusercontent.com/64796257/150618968-75e82b64-399c-4117-bf83-d7b914247b61.png)
![image](https://user-images.githubusercontent.com/64796257/150618985-fff8fbb3-e3f3-4364-8fdd-7a686c6c1ada.png)

Ω(nlogn)을 뛰어 넘는 정렬에 대해서 이제 살펴볼 것이다. 대표적으로 `기수 정렬(Radix sort)`와 `계수 정렬(Counting sort)`가 있는데 이에 대해서 다른 문서에서 살펴보겠다.














