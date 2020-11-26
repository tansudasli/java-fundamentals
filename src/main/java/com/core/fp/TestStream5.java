package com.core.fp;

import com.core.oop.Employee;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;



public class TestStream5 {

    /** Global logging. static variable.
     * A logger that is not referenced by any variable can be garbage collected.
     * To prevent this, save a reference to the logger with a static variable, as in the example above.
     * resourceBundleName is for multi-language!
     */
    public static final Logger logs = Logger.getLogger("com.core", "com.core.oop.logMessages");



    private static void testEmployee() {
        try {
            /* transfer logs to a file (javaNNN.log) under user's home directory.
             * ConsoleHandler (default), FileHandler and SocketHandler or StreamHandler are the other types.
             * You can also define your own handlers by extending the Handler or the StreamHandler class. append is default false
             */
            FileHandler fileHandler = new FileHandler("%h/com.core.example_%u.log", true);
            logs.addHandler(fileHandler);

            //assertion
            //assert com.test.oop.TestData.staffs == null : "TestData.staffs == null";

            List<Employee> staffList = Arrays.asList(com.test.oop.TestData.staffs);


            //filter employees & sort
            staffList.stream()
                    .filter(employee -> employee.getSalary() >= 5000)
                    .sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName))
                    .forEach(System.out::println);

            //lets write a function version of toString method
            System.out.println("\n\nfirst found employee > 3000 is : " +
                    staffList.stream()
                            .filter(employee -> employee.getSalary() > 3000 )
                            .findFirst()
                            .get());

            System.out.println("\n\ngroup by department");
            Map<Employee.Department, List<Employee>> groupedByDept =
                    staffList.stream()
                            .collect(Collectors.groupingBy(Employee::getDept));

            groupedByDept.forEach((department, employees) -> {
                System.out.printf("%n%s%n", department);

                employees.forEach(employee -> System.out.printf("\t%s%n", employee));
            });

            System.out.println("\n\ncount of employees by depts.");
            Map<Employee.Department, Long> employeeCountByDept = staffList.stream()
                    .collect(Collectors.groupingBy(Employee::getDept, Collectors.counting())); //downstream collector

            employeeCountByDept.forEach((department, count) -> System.out.printf("%s has %d people%n", department, count));

            System.out.println("\n\ncost of employees by depts.");
            Map<Employee.Department, Double> employeeCostByDept =
                    staffList.stream()
                            .collect(Collectors.groupingBy(Employee::getDept, Collectors.summingDouble(Employee::getSalary)));

            employeeCostByDept.forEach((department, cost) -> System.out.printf("%s cost is %f%n", department, cost));

            System.out.println("\n\nstatistical ops. on object streams");
            System.out.println("statistics of Employees: " +
                    staffList.stream()
                            .mapToDouble(Employee::getSalary)
                            .summaryStatistics());

        }
        catch (IOException io) {

            logs.info("fileHandlerFailed");
            logs.log(Level.SEVERE, io.toString(), new Object[]{"OMG"});
        }
        catch (Exception ex) {
            // A common use for logging is to log unexpected exceptions.
            // throwing() creates a Level.FINE record. */
            logs.throwing(ex.getStackTrace()[0].getClassName(), ex.getStackTrace()[0].getMethodName(), ex );

            //multi-language messages
            logs.info("testFailed");
            //logs.log(Level.WARNING, ex.toString(), ex);
            logs.log(Level.WARNING, ex.toString(), new Object[]{"OMG"});

            throw ex;
        }
    }

    public static void main(String[] args) {
        //create Test Data
        com.test.oop.TestData.createDummyData();

        testEmployee();
    }
}
