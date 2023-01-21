import java.util.List;

/**
 * Data<T> is T type. So we can use data as integer, string etc..<br/>
 * - You need to define T, U .... types at class level.
 * - Generics class also can be implemented using generic Interfaces . <br/>
 * - There are also generic interfaces such as Comparable<T> ... <br/>
 * - All Collection classes were rewritten. So an ArrayList can hold int or string at the sametime .<br/>
 * - There are class level and method level generics
 *
 *<br><br>
 * Representations<br>
 *
 * - T, first type and S, second, and U as third generic type, V forth
 * - E, element (esp. in collections)
 * - K key, V value (esp. in maps)
 * - N numbers
 *
 * @param <T>
 */
public class Data<T> implements IData<T> {  //added <T> to define as generics at class level!!

    private T data;  //here is 1 generic type. so at class level only 1 !!

    public Data(T data) {
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }

    /* Method level
     * generic method to convert array to list
     * and, E is not defined at class level !!
     *
     * public static <E> void func(E value)
     * public static <E> List<E> func(E value)
     */
    public static <E> List<E> toList(E[] source) {
        return List.of(source);   // in java8       return Arrays.asList(source);
    }

    //lets test
    public static void main(String[] args) {
        Data<Integer> d1 = new Data<>(10);
        System.out.println(d1);

        Data<String> s1 = new Data<>("Sirma");
        System.out.println(s1);

        IData<String> i1 = new Data<>("via generic interface");
        System.out.println(i1);

    }


}

