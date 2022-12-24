package com.oop;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class EmployeeTest {

    /** Global logging. static variable.
     * A logger that is not referenced by any variable can be garbage collected.
     * To prevent this, save a reference to the logger with a static variable, as in the example above.
     * resourceBundleName is for multi-language!
     */
    public static final Logger logs = Logger.getLogger("com.core", "logMessages");
    //Data mocking strategy: Array, ArrayList or Stream
    static List<Employee> staffList = Arrays.asList(TestData.staffs);
    static Stream<Employee> staffStream = staffList.stream();

    static void checkData() {

        assertEquals(15, staffList.size());
        assertEquals(9, staffStream.filter(employee -> employee.getSalary() >= 5000).count());
    }

    @BeforeAll
    @DisplayName("Be sure test data is ok")
    static void data() {

        TestData.createDummyData();

        checkData();

        System.out.printf("test data is ready & OK");

    }



    @RepeatedTest(5)
    @DisplayName("Employee's name & hiredate should be same to be equal")
    @Tag("functional")
    void equals() {
        //assertEquals(TestData.staffs[0], TestData.staffs[4], "equals method checked.");

        Employee actual = new Employee("tansu", 100000, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.IT);
        Employee expected = new Employee("tansu", 1000, 1, 1, 2015, null, null);

        assertEquals(expected, actual, "name & hireDate should be same.");

        testEquality();
    }

    @Test
    @DisplayName("Employee name is key for comparison check")
    @Tag("functional")
    void compareTo() {
        Employee actual = new Employee("tansu"
                , 100000
                , 1
                , 1
                , 2015
                , Employee.Type.PERMANENT
                , Employee.Department.IT);


        assertEquals("tansu", actual.getName(), "name should be same for comparability check.");

        testEquality();
    }


    void testEquality() {

        Employee actual = new Employee("tansu", 100000, 1, 1, 2015, Employee.Type.PERMANENT, Employee.Department.IT);
        Employee expected = new Employee("tansux", 1000, 1, 1, 2015, null, null);

        //assertEquals(expected , actual, "equals method checked.");

       // assertTrue(expected.equals(actual) && actual.compareTo(expected)==0, "compareTo() and equals() methods should be same.");

        assertAll("not ok"
                , () -> expected.equals(actual)
                , () -> actual.compareTo(expected) );

    }

    @Test
    @DisplayName("test raise salary")
    @Tag("functional")
    void raiseSalary() {

        for (Employee e : staffList) {
            double expected = e.getSalary();

            e.raiseSalary(0.05);
            assertEquals(expected * 1.05, e.getSalary());
        }
    }

    @Test
    @DisplayName("Employee's salary and payment amount must be same")
    @Tag("functional")
    void getPaymentAmount() {

        for (Employee e : staffList)
            assertEquals(e.getSalary(), e.getPaymentAmount());

    }

    @Test
    @DisplayName("Employee's salary and payment amount and earnings must be same")
    @Tag("functional")
    void earnings() {

        for (Employee e : staffList)
            assertEquals(e.getSalary(), e.earnings());

    }

    @ParameterizedTest
    @ValueSource(strings = {"tansu"})
    @DisplayName("Employee's name attribute")
    @Tag("functional")
    void getName(String name) {

        assertEquals(name, staffList.get(0).getName());
        assertEquals(name, staffList.get(4).getName());
    }

    @DisplayName("Employee's name attribute")
    @ParameterizedTest()
    @MethodSource("nameProvider")
    void getNameX(int index, String name) {

        assertEquals(name , staffList.get(index).getName());

    }

    private static Stream<Arguments> nameProvider() {

        return Stream.of(
                Arguments.of(0, "tansu"),
                Arguments.of(1, "abidin"),
                Arguments.of(2, "denyo"),
                Arguments.of(3, "denyo"),
                Arguments.of(4, "tansu"),
                Arguments.of(5, "salih"),
                Arguments.of(6, "hatib"),
                Arguments.of(7, "katip"),
                Arguments.of(8, "kezban"),
                Arguments.of(9, "osman"),
                Arguments.of(10, "hasan"),
                Arguments.of(11, "salih"),
                Arguments.of(12, "fatma"),
                Arguments.of(13, "ibrahim"),
                Arguments.of(14, "mukremin"));
    }


    @ParameterizedTest
    @EnumSource(value = Employee.Department.class,
            names = {"IT"})
    @DisplayName("Employee's department attribute")
    @Tag("functional")
    void getDept(Employee.Department dept) {

        assertEquals(dept, staffList.get(0).getDept());
        assertEquals(dept, staffList.get(11).getDept());
    }


    @ParameterizedTest
    @EnumSource(value = Employee.Type.class,
                names = {"PERMANENT"})
    @DisplayName("Employee's type attribute")
    @Tag("functional")
    void getType(Employee.Type type) {

        assertEquals(type, staffList.get(0).getType());
        assertEquals(type, staffList.get(4).getType());
    }

    @Test
    @DisplayName("Employee's string method must be valid ")
    @Tag("functional")
    void toStringX() {

        for (Employee e : staffList)
            assertNotNull(e.toString());
    }


    @Nested
    @DisplayName("Constructor cases")
    class ConstructorCases {
        @Test
        @DisplayName("test classcastexception")
        @Tag("functional")
        void testClassCastException() {
            Employee actual = new Employee("tansu"
                    , 100000
                    , 1
                    , 1
                    , 2015
                    , Employee.Type.PERMANENT
                    , Employee.Department.IT);

            assertThrows(ClassCastException.class, () -> actual.compareTo(new Manager("abidin", 1000, 1, 1, 1111))
                    , "Employee type compare not allowed !");
        }

        //TODO put all construction test into inner class for better readability

        @Test()
        @DisplayName("If Employee name is null, throw exception")
        @Tag("functional")
        void testEmployeeName() {

            Throwable exception = assertThrows(IllegalArgumentException.class,
                    () -> {
                        new Employee(null
                                , 100000
                                , 1
                                , 1
                                , 2015
                                , Employee.Type.PERMANENT
                                , Employee.Department.IT);
                    }
            );
            assertEquals("Employee name should not be empty", exception.getMessage());
        }

        @Test()
        @DisplayName("If Employee salary < 1000, throw exception")
        @Tag("functional")
        void testEmployeeSalary() {

            Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
                        new Employee("Tansu"
                                , 999
                                , 1
                                , 1
                                , 2015
                                , Employee.Type.PERMANENT
                                , Employee.Department.IT);
                    }
            );
            assertEquals("Salary should be greater than 1000 TL. But it is just 999.0", exception.getMessage());
        }


    }

    @Test
    @DisplayName("Employee's clone should be different in memory")
    @Tag("functional")
    void clonee() throws CloneNotSupportedException {
        Employee actual = new Employee("tansu"
                , 100000
                , 1
                , 1
                , 2015
                , Employee.Type.PERMANENT
                , Employee.Department.IT);


        Employee expected = actual.clone();
        expected.raiseSalary(0.5);
        expected.setHireDate(LocalDate.of(2020,1,1));

        assertTrue(expected.getSalary() != actual.getSalary(), "clone should be different object: salary");
        assertTrue(expected.getHireDate() != actual.getHireDate(), "clone should be different object: hiredate");

        //TODO we did not test Manager class clone capability!
    }


}