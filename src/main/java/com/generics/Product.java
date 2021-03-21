package com.generics;

/*
generic class
 */
public class Product<T, U> {

    private T id;    //at class level we added T, so class is generic type
    private U name;  //so we did not used T, means name can be different type
    private T description;

    public Product(T id, U name, T description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }


    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    public U getName() {
        return name;
    }

    public void setName(U name) {
        this.name = name;
    }

    public T getDescription() {
        return description;
    }

    public void setDescription(T description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name=" + name +
                ", description=" + description +
                '}';
    }

    //lets test
    public static void main(String[] args) {

        /*
         * T int, U string...
         * so, id, desc must be int. Or we need to add more generic types at class level like <T,U,W>!
         */
        Product<Integer, String> product = new Product<>(1, "Tansu", 1000);
        System.out.println(product.toString());

        Product<String, String> product2 = new Product<>("10000eyZuu8", "Tansu", "1000");
        System.out.println(product2.toString());
    }
}


