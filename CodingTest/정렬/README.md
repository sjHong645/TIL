## Arrays.sort vs Collection.sort 

### Arrays.sort ⇒ 배열을 정렬하는데 사용한다.

- 오름차순 : Arrays.sort(배열);
- 내림차순 : Arrays.sort(배열, Collections.reverseOrder());

이때, 별도의 정렬 기준으로 정렬하고 싶다면 아래와 같이 하면 된다.

- 관련된 [예시](https://github.com/sponbob-pat/TIL/blob/main/CodingTest/%EC%A0%95%EB%A0%AC/%EB%8B%A8%EC%96%B4%EC%A0%95%EB%A0%AC.md)

- [출처](https://ifuwanna.tistory.com/232)
``` java
class CustomComparator implements Comparator<String> { 
  @Override 
  public int compare(String o1, String o2) { 
      return o2.compareTo(o1); // 내림차순 : z ⇒ a 순으로 정렬
  } 
} 
  
String[] stringArr = new String[] {"A","C","B","E","D"}; 

// 새로 정의한 CustomCompartor 클래스의 compare 함수에 따라 정렬
Arrays.sort(stringArr, new CustomComparator()); 

// 출력 : E D C B A

```
Comparator를 구현하는 별도의 클래스 정의 없이  
Comparator 인스턴스 생성과 동시에 compare() 메서드를 Override 하는 것도 가능하다.

``` java
Arrays.sort(stringArr, new Comparator<String>() { 
  @Override
  public int compare(String o1, String o2) { 
    
     return o2.compareTo(o1); // 내림차순 : z ⇒ a 순으로 정렬
     
  }

}); 
```

Java 8 이상에서는 Stream API를 이용해서 정렬할 수 있다.  
이때, 람다식(Lambda Expression)을 이용해서 좀 더 간결하게 표현할 수 있다.

``` java
String str = "ACBED"; 

String[] stringArr = str.split(""); // str 문자열을 new String[] {"A","C","F","E","D"} 배열로 변환 

// stringArr를 sorted에 의해서 정렬하고 나서
// collect(Collectors.joining())을 통해 

// 원래는 문자열 배열이었던 것을 그냥 String 문자열로 모아서 저장한다. 

String streamSortASC = Stream.of(stringArr).sorted().collect(Collectors.joining()); // 오름차순 
String streamSortDESC = Stream.of(stringArr).sorted(Comparator.reverseOrder()).collect(Collectors.joining()); // 내림차순 

// Lambda 
String streamSortASC_Lambda = Stream.of(stringArr).sorted((o1,o2)->o1.compareTo(o2)).collect(Collectors.joining()); // 오름차순 

String streamSortDESC_Lambda = Stream.of(stringArr).sorted((o1,o2)->o2.compareTo(o1)).collect(Collectors.joining()); // 내림차순

```


### Collections.sort ⇒ List를 정렬하는데 사용한다.

- 오름차순 : Collections.sort(List 자료);
- 내림차순 : Collections.sort(List 자료, Comparator.reverseOrder()); 

이때, 별도의 정렬 기준으로 정렬하고 싶다면 아래와 같이 하면 된다.

``` java
class CC implements Comparator<String> { 
	  @Override 
	  public int compare(String o1, String o2) { 
	      return o2.compareTo(o1); // 내림차순 : z ⇒ a 순으로 정렬
	  } 
}

public class Ji { 

	public static void main(String[] args) {
		List<String> stringArr = new ArrayList<>(); 

		stringArr.add("A"); 
		stringArr.add("C"); 
		stringArr.add("B"); 
		stringArr.add("E"); 
		stringArr.add("D"); 
		
		Collections.sort(stringArr, new CC()); 	
		
		
		for(Iterator<String> itr = stringArr.iterator(); itr.hasNext();) {
			System.out.println(itr.next() + " ");
		}
		
	}

}
// 출력 : E D C B A
```
Comparator를 구현하는 별도의 클래스 정의 없이  
Comparator 인스턴스 생성과 동시에 compare() 메서드를 Override 하는 것도 가능하다.

이 내용은 Arrays.sort 내용과 동일하니까 생략.

스트림 사용하는 것도 위와 동일하다. 다만 다른점은 아래와 같다.

``` java
List<String> sortedList = list.stream().sorted().collect(Collectors.toList());

// 정렬한 내용을 List에 저장하고 싶다면
// .collect(Collectors.toList())를 마지막에 추가해주면 된다.
```

클래스를 정렬하고 싶을 때

클래스가 Comparable<>을 implement해서 compareTo를 구현하거나

Comparator<>을 implement해서 compare를 구현해서 정렬하는 방법도 있다.

















