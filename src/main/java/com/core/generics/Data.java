package com.core.generics;

/**
 * Data<T> is T type. So we can use data as integer, string etc..
 *
 * @param <T>
 */
public class Data<T> {  //added <T> to define as generics !!

    private T data;

    public Data(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Data{" +
                "data=" + data +
                '}';
    }

    //main
    public static void main(String[] args) {
        Data<Integer> d1 = new Data<>(10);
        System.out.println(d1);

        Data<String> s1 = new Data<>("Sirma");
        System.out.println(s1);

    }


}

