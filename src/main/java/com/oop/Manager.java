package com.oop;

/* Extends Employee class for some additional fields and functions...
* */
public class Manager extends Employee {


    private double bonus;

    /* there is no default constructor.
    * so initialization of variables may be problematic
    * */

    //constructor of super
    public Manager(String name, double salary, int day, int month, int year)  {

        //should be first statement of constructor
        super(name, salary, day, month, year);

        bonus = 0;
    }


    /** constructor with bonus field.
     * use setBonus() method if You have Employee object.
     *
     * @param name
     * @param salary
     * @param bonus
     * @param day
     * @param month
     * @param year
     */
    public Manager(String name, double salary, double bonus, int day, int month, int year)  {

        super(name, salary, day, month, year);

        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }


    /**
     * checks Employee's name and hireDay are same, plus Bonus equality.
     * But suppose we used an Employee.ID for equality testing. This notion of equality makes sense for all subclasses.
     * Then we could use the instanceof test (instead getClass), and we should have declared Employee.equals as final.
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        if (!super.equals(obj))
            return false;

        return bonus == ((Manager)obj).bonus;

    }

    @Override
    public String toString() {
        return "salary= " + this.getSalary() + " of "+ this.getName();
    }

    @Override
    public double getPaymentAmount() {
        return super.getPaymentAmount();
    }

    @Override
    public double earnings() {
        return super.earnings() + getBonus();
    }
}
