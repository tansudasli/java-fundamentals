import java.time.LocalDate;
import java.util.*;


/**
 * Employee class
 */
public class Employee implements Comparable<Employee>, Cloneable, Payable, CompensationModel {


    private int ID;
    private String name;
    private double salary;
    private LocalDate hireDate;
    private LocalDate endDate;
    public enum Grade {S01, S02, S03, M01, M02, M03, C_LEVEL, CEO}
    public enum Type {PERMANENT, TEMPORARY, INTERN}
    public enum Department {IT, HR, SALES, MARKETING, OPS}


    private Department dept;
    private Type type;

    /* for only complex initializations, you may need static constructor
    static {
        id = Math.random();
    }
    */

    /* deprecated anymore.
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
    */

    /** Constructor
     *
     * @param name
     * @param salary
     * @param day
     * @param month
     * @param year
     */
    public Employee(String name, double salary, int day, int month, int year) {

        EmployeeHelper.validateName(name);
        this.name = name;

        /* basic implementation
         this.ID = (int) (Math.random());
        *
        * below will call logging mechanism in generateID() method.
        * */
        this.ID = (int) (1000000 * generateID().nextDouble());

        EmployeeHelper.validateSalary(salary);
        this.salary = salary;

        this.hireDate = LocalDate.of(year, month, day);
    }
    public Employee(String name, double salary, int day, int month, int year, Type type, Department dept) {

        this(name, salary, day, month, year);
        this.type = type;
        this.dept = dept;
    }


    /**
     * if you have trouble with the nextDouble method of the Random class, you can create a proxy object as an instance of an anonymous subclass:
     * */
    private Random generateID() {

        Random r = new Random() {

            //create an anonymous subclass class
            @Override
            public double nextDouble() {
                double result = super.nextDouble();

                //TestStream5.logs.info("generatedDoubleID");
                //TestStream5.logs.log(Level.INFO, "generatedDoubleID=" + result);

                return result;
            }
        };

        return r;
        //r.nextDouble() lets create a proxy
    }

    /** raises salary.
     *
     * @param percentage
     */
    public void raiseSalary(double percentage) {
        salary = salary * (1 + percentage);
    }

    @Override
    public double getPaymentAmount() {
        return getSalary();
    }

    @Override
    public double earnings() {
        return getSalary();
    }

    //TODO create more employee methods using generics


    //getters & setters
    public int getID() {
        return ID;
    }
    public String getName() {
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public LocalDate getHireDate() {
        return hireDate;
    }
    public Department getDept() {
        return dept;
    }
    public Type getType() {
        return type;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }


    /**
     * Most (but not all) toString methods follow this format: the name of the class, then the field values enclosed in square brackets
     * @return
     */
    @Override
    public String toString() {
        return  getClass().getName() + "[" +
                "salary= " + salary +
                ", name= " + getName() +
                ", type= " + getType() +
                ", dpt= " + getDept() + "]";
    }

    /** checks Employee's name and hireDate are same.
     * But suppose we used an Employee.ID for equality testing. This notion of equality makes sense for all subclasses.
     * Then we could use the instanceof test (instead getClass), and we should have declared Employee.equals as final.
     *
     * If you redefine the equals method, you will also need to redefine the hashCode method for objects that users might insert into a hash table
     * 
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {

        try {
            if (this == obj)
                return true;

            // Check the antisymmetry rule,
            if (this.getClass() != obj.getClass())
                return false;

            //Use == for primitive type fields, Objects.equals for object fields
            return getName().equals(((Employee) obj).getName()) &&
                   hireDate.equals(((Employee) obj).hireDate);

        } catch (NullPointerException ne) {
            return false;
        }
    }

    /**
     * Your definitions of equals and hashCode must be compatible:
     * If x.equals(y) is true, then x.hashCode() must return the same value as y.hashCode().
     * For example, if you define Employee.equals to compare employee IDs, then the hashCode method needs to hash the IDs,
     * not employee names or memory addresses.
     *
     * @return
     */
    @Override
    public int hashCode() {
        /* default
         return super.hashCode();
        */

        /* better
        return 7 * getName().hashCode() +
                11 * Double.hashCode(salary) +
                13 * hireDate.hashCode();
         */

        /* much better */
        return Objects.hash(getName(), hireDate);

    }

    /** Comparable interface suggests that the compareTo method should be compatible with the equals method.
     * sort() method use this.
     *
     * As with the equals method, problems can arise when inheritance comes into play.
     * Since Manager extends Employee, it implements Comparable<Employee> and not Comparable<Manager>.
     * That's why we add getClass() check.
     *
     * If there is a common algorithm for comparing subclass objects,
     * simply provide a single compareTo method in the superclass and declare it as final.
     *
     * For example, suppose you want managers to be better than regular employees, regardless of salary.
     * What about other subclasses such as Executive and Secretary? If you need to establish a pecking order,
     * supply a method such as rank in the Employee class. Have each subclass override rank,
     * and implement a single compareTo method that takes the rank values into account.
     *
     * @param o
     * @return
     */
    @Override
    public int compareTo(Employee o) {

        // Check the antisymmetry rule,
        if (getClass() != o.getClass())
            throw new ClassCastException();

        return Arrays.compare(getName().toCharArray(), o.getName().toCharArray());


        /* not similar with equals method
         return Double.compare(salary, o.salary);
        * */
    }

    /**
     * make it public, default is protected.
     * Employee type
     * cast super method
     *
     * @return a clone of this instance.
     * @throws CloneNotSupportedException if the object's class does not
     *                                    support the {@code Cloneable} interface. Subclasses
     *                                    that override the {@code clone} method can also
     *                                    throw this exception to indicate that an instance cannot
     *                                    be cloned.
     */
    @Override
    public Employee clone() throws CloneNotSupportedException {
        //toDo: To make a deep copy, you have to work harder and clone the mutable instance fields.

        try {
            Employee cloned = ((Employee) super.clone());

            // clone mutable fields. but LocalDate is immutable and thread-safe. Date class is mutable.
            // but it is not like that in our case!
            // Date hiredDay;
            // cloned.hireDate = (hireDate).clone(); or new Date(hireDate.getTime())

            return cloned;
        }
        catch (CloneNotSupportedException e) {
            e.printStackTrace();
            throw e;
        }
    }



}
