public 하게 노출되는 binarySearch 메소드의 정의는 아래와 같다.

``` java
private static final int BINARYSEARCH_THRESHOLD   = 5000;

    public static <T> int binarySearch(List<? extends Comparable<? super T>> list, T key) {
        if (list instanceof RandomAccess || list.size()<BINARYSEARCH_THRESHOLD)
            return Collections.indexedBinarySearch(list, key);
        else
            return Collections.iteratorBinarySearch(list, key);
    }
    
```

1) RandomAccess : 아무런 메소드를 정의하지 않은 `마커 인터페이스`이다. 

[출처](https://jyami.tistory.com/109)
마커 인터페이스란...

: 아무런 메소드를 담지 않고 단지 자신을 구현하는 클래스가 특정 속성을 가지는 걸 표시해주는 인터페이스

대표적으로 사용하는 `ArrayList`는 `RandomAccess` 인터페이스를 implements 한다. 

``` java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{ ... }
```

RandomAccess 에 대한 설명은 아래와 같이 나온다.

용어) random access : 기억 장치에서 자료를 읽거나 쓸 때 기억 `장소에 관계없이` 동일한 접근 시간이 걸리는 접근 방식

``` 
Marker interface used by List implementations to indicate that they support fast (generally constant time) random access.

RandomAccess 인터페이스는 List 구현에서 사용되는 마커 인터페이스다. 
이 인터페이스는 빠른(거의 상수시간) random access을 제공한다는 걸 나타낸다. 

해당 인터페이스를 사용하는 주된 목적은 
random access list 또는 sequential access list에 generic 알고리즘을 적용할 때 좋은 성능을 낼 수 있는 동작을 할 수 있도록 하는 것이다. 

random access list(ex. ArrayList)에서는 최고의 성능을 내는 알고리즘이
sequential access list(ex. LinkedList)에서는 시간복잡도가 2차식이 되는 알고리즘이 될 수 있다. 

때문에 Generic한 list 알고리즘은 주어진 list가 RandomAccess의 instanceof인지 먼저 확인할 것을 권장한다. 

물론, random access인지 sequential access 인지 구분하는 게 애매하다. 

크기가 커지면 linear한 복잡도를 나타내지만 평상시에는 상수 복잡도를 나타낼 수도 있기 때문이다. 

그래서 경험적으로 

for (int i=0, n=list.size(); i < n; i++) list.get(i); 이 방법의 속도가 

for (Iterator i=list.iterator(); i.hasNext();) i.next(); 이 방법의 속도보다 빠르다면 

RandomAccess 인터페이스를 implements 하자. 

```


2) instanceof

``` java
ArrayList list = new ArrayList();

// list는 ArrayList 타입이라서 true를 출력
System.out.println(list instanceof ArrayList); ==> true

// list는 ArrayList 타입이지만 ArrayList 타입이 List를 상속받기 때문에 true를 출력
System.out.println(list instanceof List); ==> true

// list는 ArrayList 타입인데 Set과는 관련이 없다. 그래서 false를 출력.
System.out.println(list instanceof Set) ==> false
```

3) Collections.indexedBinarySearch

``` java
    private static <T>
    int indexedBinarySearch(List<? extends Comparable<? super T>> list, T key) {
        int low = 0;
        int high = list.size()-1;

        while (low <= high) {
            int mid = (low + high) >>> 1; // 2로 나눈 거랑 동일함
            Comparable<? super T> midVal = list.get(mid); // 중간값
            int cmp = midVal.compareTo(key);
            
            // midVal < key => cmp = -1 < 0
            // midVal > key => cmp = 1 > 0
            // midVal == key => cmp = 0 else

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        
        return -(low + 1);  // key not found
    }
```

못 찾으면 `-(low + 1)`을 해서 있을 만한 위치를 반환해주는 건 생각못했다.

4) Collections.iteratorBinarySearch

``` java
    private static <T>
    int iteratorBinarySearch(List<? extends Comparable<? super T>> list, T key)
    {
        int low = 0;
        int high = list.size()-1;
        ListIterator<? extends Comparable<? super T>> i = list.listIterator();

        while (low <= high) {
            int mid = (low + high) >>> 1;
            Comparable<? super T> midVal = get(i, mid);
            int cmp = midVal.compareTo(key);
            
            // midVal < key => cmp = -1 < 0
            // midVal > key => cmp = 1 > 0
            // midVal == key => cmp = 0 else

            if (cmp < 0)
                low = mid + 1;
            else if (cmp > 0)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found
    }
```
