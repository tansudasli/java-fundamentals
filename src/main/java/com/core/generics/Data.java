package com.core.generics;

/**
 * Data<T> is T type. So we can use data as integer, string etc..<br/>
 * - Generics class  can be implemented using generic Interfaces . <br/>
 * - There are also generic interfaces such as Comparable<T> ... <br/>
 * - All Collection class was rewritten. So an ArrayList can hold int or string at the sametime .<br/>
 *
 *<br><br>
 *
 * @param <T>
 */
public class Data<T> implements IData<T> {  //added <T> to define as generics !!

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

