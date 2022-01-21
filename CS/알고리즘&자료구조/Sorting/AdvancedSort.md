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
    T[] temp = (T[]) new Comparable[length];
    int i = left;
    int j = mid + 1;
    int k = 0;

    while (i <= mid && j <= right) {
      if (arr[i].compareTo(arr[j]) <= 0) {
        temp[k++] = arr[i++];
      } else {
        temp[k++] = arr[j++];
      }
    }

    while (i <= mid) {
      temp[k++] = arr[i++];
    }

    while (j <= right) {
      temp[k++] = arr[j++];
    }

    System.arraycopy(temp, 0, arr, left, length);
  }

```
여기서 merge 메소드에 대해 좀 더 자세히 살펴보겠다. 변수 선언 부분을 그림으로 표현하면 아래와 같다. 


### 성능 평가 





























