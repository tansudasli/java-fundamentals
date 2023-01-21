/**
 *
 * List<Employee> staffList = new ArrayList<>();
 * get a stream : Arrays.stream(staffs)
 * get an ArrayList: Arrays.asList(staffs)
 *
 * for inheritance testing, we need child class
 *         Manager boss = new Manager("boss", 30000.00, 1, 1, 2010);
 *         boss.setBonus(10000);
 *         staffs[3] = boss;
 */
public interface TestData {

    Employee[] staffs = new Employee[15];

    static void createDummyData() {
        //create objects

        try {

            staffs[0] = new Employee("tansu", 100000, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.IT);
            staffs[1] = new Employee("abidin", 10000.00, 1, 1, 2011, Employee.Type.TEMPORARY, Employee.Department.OPS);
            staffs[2] = new Employee("denyo", 5000.00, 1, 1, 2011, Employee.Type.INTERN, Employee.Department.OPS);
            staffs[3] = new Employee("denyo", 5000.00, 1, 1, 2011, Employee.Type.INTERN, Employee.Department.OPS);
            staffs[4] = new Employee("tansu", 5000.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.SALES);
            staffs[5] = new Employee("salih", 7500.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.MARKETING);
            staffs[6] = new Employee("hatib", 6500.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.MARKETING);
            staffs[7] = new Employee("katip", 7500.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.MARKETING);
            staffs[8] = new Employee("kezban", 3500.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.MARKETING);
            staffs[9] = new Employee("osman", 1000.00, 1, 1, 2011, Employee.Type.INTERN, Employee.Department.OPS);
            staffs[10] = new Employee("hasan", 1000.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.SALES);
            staffs[11] = new Employee("salih", 1000.00, 1, 1, 2015, Employee.Type.INTERN, Employee.Department.IT);
            staffs[12] = new Employee("fatma", 5500.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.IT);
            staffs[13] = new Employee("ibrahim", 2500.00, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.HR);
            staffs[14] = new Employee("mukremin", 1200.00, 1, 1, 2017, Employee.Type.INTERN, Employee.Department.HR);

        } catch (IllegalArgumentException e1) {
            // get an array if you need to analyze
            // System.out.println(e1.getStackTrace()[0].toString());
            e1.printStackTrace();
        }
    }
}
