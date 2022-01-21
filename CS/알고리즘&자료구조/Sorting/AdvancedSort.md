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

### 과정 

다음 배열을 `오름차순`으로 정렬하려 한다.  
![image](https://user-images.githubusercontent.com/64796257/150496053-52d0a54a-6bc8-47d9-8717-b612191a214a.png)

퀵 정렬에서 사용한 4개 지점의 이름은 다음과 같다. 
- left/right : 가장 왼쪽/오른쪽 지점을 가리키는 이름
- low/high : 피벗을 제외한 가장 왼쪽/오른쪽 지점을 가리키는 이름

여기서는 일단 가장 왼쪽에 위치한 데이터를 퀵 정렬에 필요한 `pivot`이라 정하자.

- 1단계

![image](https://user-images.githubusercontent.com/64796257/150497452-99d713bd-0723-4c3c-ae1a-c527dbd874fe.png)

`low`는 `오른쪽`으로 `high`는 `왼쪽`으로 이동한다. 이동의 기준은 다음과 같다.

low : pivot보다 `큰 값`을 만날 때까지 `오른쪽 이동`
high : pivot보다 `작은 값`을 만날 때까지 `왼쪽 이동`

이를 좀 더 일반화해서 표현하면 

low : pivot보다 정렬의 `우선순위가 낮은 데이터`를 만날 때까지 `오른쪽 이동`
high : pivot보다 정렬의 `우선순위가 높은 데이터`를 만날 때까지 `왼쪽 이동`

때문에 `low는 7`에 머물게 되고 `high는 4`에 머물게 된다. 이렇게 하고 나서 둘을 서로 교환한다.  
- 2단계   
![image](https://user-images.githubusercontent.com/64796257/150497712-dbd0f3a9-5a14-42d1-8556-4be6639f0a9a.png)

7과 4를 교환하고 나서 똑같은 원리로 low와 high를 이동시킨다.

그러면 `low는 9`를 가리키고 `high는 2`를 가리키게 된다. 그렇게 하고 나서 그 둘을 서로 교환한다.

- 3단계  

![image](https://user-images.githubusercontent.com/64796257/150497798-f4ffc1aa-7be5-4c92-b313-1911a3e43084.png)

- 4단계 

이후에도 low와 high를 한 칸씩 이동하면 되는데 이때 low와 high가 다음과 같이 서로 교차된다는 걸 알 수 있다.

![image](https://user-images.githubusercontent.com/64796257/150497911-37cc03ea-6952-457a-a050-7774bf4b3f51.png)

이때는 low와 high를 서로 교환하지 않는다. 이 상황은 low와 high의 이동 및 교환의 과정이 완료되었음을 의미하기 때문이다.

- 5단계
이제 pivot과 high가 가리키는 데이터를 서로 교환한다.

![image](https://user-images.githubusercontent.com/64796257/150497973-39c273fa-50a1-4d98-9e8e-43f04ccd2233.png)

여기까지가 `퀵 정렬`의 핵심연산이다.

이렇게 한 차례의 `퀵 정렬`을 완료하면 `pivot값 이었던 5`가 `혼자 정렬`되었다는 걸 알 수 있다.  
왜냐하면, 5를 기준으로 `왼쪽`에는 `5보다 작은 값만 위치`하고 `오른쪽`에는 `5보다 큰 값만 위치`하고 있기 때문이다.

이제는 제자리를 찾은 5를 기준으로 왼쪽과 오른쪽 영역을 대상으로 `퀵 정렬`을 `재귀적으로 반복`한다.

![image](https://user-images.githubusercontent.com/64796257/150498108-351cef1f-bb55-4579-b6fa-6333797465e7.png)

그렇다면 이 과정은 언제까지 진행될까?  
left와 right가 각각 정렬대상의 시작과 끝을 의미하는데 `left > right` 즉, left와 right가 서로 교차되는 상황이 되었을 때 이 과정은 끝난다.

이러한 과정을 전체적으로 구성했을 때 다음과 같이 코드를 구성할 수 있다.

``` java
  private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
    if (left < right) {
      int pivot = randomPartition(array, left, right); // pivot을 정하고 
      doSort(array, left, pivot - 1);                  // left ~ (pivot - 1) 부분에 대한 정렬
      doSort(array, pivot, right);                     // pivot ~ right 부분에 대한 정렬
    }
  }
```

left와 right가 서로 교차될 때 정렬이 끝난다고 했다. 그래서 if의 조건을 위와 같이 설정했다.

Partition 함수를 통해서 pivot의 인덱스값을 찾아낼 것이다. 그 pivot을 기준으로 왼쪽, 오른쪽에도 똑같이 QuickSort가 동작하도록 했다.

### 구현 

``` java
  public <T extends Comparable<T>> T[] sort(T[] array) {
    doSort(array, 0, array.length - 1); // array 배열 0번째 부터 (길이-1)까지 정렬 
    return array;
  }

  private static <T extends Comparable<T>> void doSort(T[] array, int left, int right) {
    .. 위에 있는 부분과 동일 ...
  }
  /**
   * Ramdomize the array to avoid the basically ordered sequences
   *
   * @param array The array to be sorted
   * @param left The first index of an array
   * @param right The last index of an array
   * @return the partition index of the array
   */
   
  // 이미 정렬된 배열에 대해서 merge sort를 진행하는 건 
  // merge sort에 있어서 최악의 상황이다. 그래서 이를 피하기 위해 의도적으로 배열을 섞는 동작을 수행한다.
  private static <T extends Comparable<T>> int randomPartition(T[] array, int left, int right) {
    
    // Math.random()은 0.0 ~ 1.0의 double 형 난수를 return한다.
    // 즉, pivot이 될 부분을 무작위로 선택하는 과정이다.
    int randomIndex = left + (int) (Math.random() * (right - left + 1));
   
    swap(array, randomIndex, right);
    
    // 위 동작들을 통해 배열은 무작위가 되었다. 이제 pivot값을 찾을 차례다.
    return partition(array, left, right);
  }
  
    /**
   * This method finds the partition index for an array
   *
   * @param array The array to be sorted
   * @param left The first index of an array
   * @param right The last index of an array Finds the partition index of an array
   */
  private static <T extends Comparable<T>> int partition(T[] array, int left, int right) {
       
    int mid = (left + right) >>> 1; // left + right의 중간값을 mid에 저장
    T pivot = array[mid]; // mid번째 값을 pivot이라 했다.

    // left와 right가 만나기 전까지 while 문을 동작한다.
    while (left <= right) {
    
      // left번째 값이 pivot보다 작으면 left를 1 증가시킨다.
      // left번째 값이 pivot보다 커지거나 같아지면 해당 동작을 멈춘다.
      while (less(array[left], pivot)) { ++left; }

      // pivot이 right번째 값보다 작으면 right를 1 감소시킨다.
      // pivot이 right번째 값보다 커지거나 같아지면 해당 동작을 멈춘다.
      while (less(pivot, array[right])) {
        --right;
      }
      
      // 위에 있는 2개의 while문을 통해 서로 값을 바꿀 left와 right 인덱스 구했다.
      // 이제 left번째 원소값과 right번째 원소값을 바꾼다.
      // 그렇게 바꾸고 나서 left와 right를 각각 1씩 증가/감소시킨다.
      if (left <= right) {
        swap(array, left, right);
        ++left;
        --right;
      }
    }
    // 최종적으로 left값을 반환한다. 
    return left;
  }

```























