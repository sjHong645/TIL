ArrayList의 `indexOf`와 `lastIndexOf`

ArrayList는 `elementData`라는 이름의 배열에 저장하는 형태로 list를 구현했다.

1) indexOf - 중복된 값이 있다면 그 값들 중에서 가장 앞에 있는 index를 출력

``` java
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }
```

2) lastIndexOf - 중복된 값이 있다면 그 값들 중에서 가장 뒤에 있는 index를 출력

``` java

    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementData[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(elementData[i]))
                    return i;
        }
        return -1;
    }

```
