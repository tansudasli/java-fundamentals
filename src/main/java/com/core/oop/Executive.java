package com.core.oop;

/* not extendable ! (final keyword)*/
public final class Executive extends Manager {


    public Executive (String name, double salary, int day, int month, int year)  {

        //should be first statement of constructor
        super(name, salary, day, month, year);

        super.setBonus(0);
    }
}
