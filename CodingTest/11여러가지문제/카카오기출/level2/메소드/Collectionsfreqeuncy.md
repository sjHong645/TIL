frequency(Collection<?> c, Object o) 

`c`라는 Collections 자료형에 `o`라는 대상의 개수가 몇 개인지 count하는 메소드


``` java
    public static int frequency(Collection<?> c, Object o) {
        int result = 0;
        if (o == null) {
            for (Object e : c)
                if (e == null)
                    result++;
        } else {
            for (Object e : c)
                if (o.equals(e))
                    result++;
        }
        return result;
    }
```
