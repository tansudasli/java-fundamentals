package com.oop;

public class Sales extends Employee {


    private double grossSales;
    private double commissionRate;


    public Sales(String name, double salary, int day, int month, int year, double grossSales, double commissionRate)  {
        super(name, salary, day, month, year);

        this.grossSales = grossSales;
        this.commissionRate = commissionRate;

    }

    public double getCommissionRate() {
        return commissionRate;
    }
    public double getGrossSales() {
        return grossSales;
    }

    @Override
    public double getPaymentAmount() {
        return super.getPaymentAmount() + getCommissionRate() * getGrossSales() ;
    }

    @Override
    public double earnings() {
        return super.earnings() + getCommissionRate() * getGrossSales();
    }
}
