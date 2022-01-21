## SortUtil : 정렬할때 사용하는 유용한 메소드들 
[출처](https://github.com/sponbob-pat/Java/blob/master/src/main/java/com/thealgorithms/sorts/SortUtils.java)

``` java
import java.util.Arrays;
import java.util.List;

final class SortUtils {

  // 배열 array의 idx번째 값과 idy번째 값을 서로 바꾼다.
  static <T> boolean swap(T[] array, int idx, int idy) {
    T swap = array[idx];
    array[idx] = array[idy];
    array[idy] = swap;
    return true;
  }

  // v가 w보다 작으면 true 아니면 false
  static <T extends Comparable<T>> boolean less(T v, T w) {
    return v.compareTo(w) < 0;
  }

  // v가 w보다 크면 true 아니면 false
  static <T extends Comparable<T>> boolean greater(T v, T w) {
    return v.compareTo(w) > 0;
  }

  // 전달한 매개변수 리스트의 성분을 출력
  static void print(List<?> toPrint) {
    toPrint.stream().map(Object::toString).map(str -> str + " ").forEach(System.out::print);

    System.out.println();
  }

  // 전달한 매개변수 배열의 성분을 출력
  static void print(Object[] toPrint) {
    System.out.println(Arrays.toString(toPrint));
  }

  /**
   * Swaps all position from {@param left} to @{@param right} for {@param array}
   *
   * @param array is an array
   * @param left is a left flip border of the array
   * @param right is a right flip border of the array
   */
  static <T extends Comparable<T>> void flip(T[] array, int left, int right) {
    while (left <= right) {
      swap(array, left++, right--);
    }
  }
}
```
