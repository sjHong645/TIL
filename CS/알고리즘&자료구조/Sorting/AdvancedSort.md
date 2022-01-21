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
- while문 : 조건문이 `참`일 때 블록 안에 있는 문장을 수행하고 조건문이 `거짓`이 되는 순간 while 문 동작을 멈춘다.
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
- mid : left와 right의 중간값
- pivot : array[mid] 값

이 변수들을 표시해서 직접 써보면 아래와 같다. 

![image](https://user-images.githubusercontent.com/64796257/150547641-28431f26-36c1-4dab-8f0d-45de8cbd6c09.png)

어떻게 정렬되는지 하나씩 살펴보겠다. 

### 구현 


























