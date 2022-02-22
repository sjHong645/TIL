DP 문제를 해결하기 위해서는 결국 `점화식`을 제대로 세울 수 있어야 한다.  

점화식을 잘 만들었다면 그에 따른 초기 조건을 세워서 해결하면 된다.

DP문제를 해결하는 방법은 크게 두 가지가 있다. 각각의 문제들을 어떤 방식으로 해결했는지 정리해봤다.

## Top-down 방식 

- [파도반 수열](/CodingTest/DP/파도반수열.md)
- [피보나치 함수](/CodingTest/DP/피보나치함수.md)
- [계단 오르기](/CodingTest/DP/계단오르기.md)
- [1로 만들기](/CodingTest/DP/1로만들기.md)

## Bottom-up 방식 

- [RGB 거리](/CodingTest/DP/RGB거리.md)
- [정수 삼각형](/CodingTest/DP/정수삼각형.md)
- [쉬운 계단수](/CodingTest/DP/쉬운계단수.md)

## long 타입 

long 타입 => `16 Bytes = 64 bits` 데이터를 표현하기 위해 사용됨 

즉, -9223372036854775808 ~ 9223372036854775807 범위의 숫자를 표현할 수 있는 타입이다.

## int 배열 vs Integer 배열의 차이

- int 배열 : 초기화하지 않은 부분의 값은 모두 0이 된다.

- Integer 배열 : 초기화하지 않은 부분의 값은 null이 된다.

``` java
public class temp {

	public static void main(String[] args) {
		
		int[] array1 = new int[11];
		
		Integer[] array2 = new Integer[11];
		
		for(int i = 0; i < array1.length; i++) {
			
			System.out.println("array1[" + i + "] = " + array1[i] + " array2[" + i + "] = " + array2[i]);
		}

	}

}
```

- 출력 

``` console
array1[0] = 0 array2[0] = null
array1[1] = 0 array2[1] = null
array1[2] = 0 array2[2] = null
array1[3] = 0 array2[3] = null
array1[4] = 0 array2[4] = null
array1[5] = 0 array2[5] = null
array1[6] = 0 array2[6] = null
array1[7] = 0 array2[7] = null
array1[8] = 0 array2[8] = null
array1[9] = 0 array2[9] = null
array1[10] = 0 array2[10] = null

```
