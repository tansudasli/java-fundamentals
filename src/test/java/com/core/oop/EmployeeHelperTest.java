package com.core.oop;


import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeHelperTest {

    @ParameterizedTest
    @ValueSource(strings = {"2 Maple Trail", "23860 Lillian Parkway", "052 Calypso Way", "479 Becker Pass", "2 Dunning Place Hill", "9 8th Terrace"})
    void validateStreet(String street) {

        assertTrue( EmployeeHelper.validateStreet(street));

    }

    @ParameterizedTest
    @ValueSource(strings = {"12345", "01234"})
    void validatePostCode(String postCode) {

        EmployeeHelper.validatePostCode(postCode);

    }

    @ParameterizedTest
    @ValueSource(strings = {"Maple Trail"})
    void invalidStreet(String street) {

        assertThrows(IllegalArgumentException.class,
                () -> EmployeeHelper.validatePostCode(street) );

    }

    @ParameterizedTest
    @ValueSource(strings = {"1234546", "1234", "123", "123456"})
    void invalidPostCode(String postCode) {

        assertThrows(IllegalArgumentException.class,
                () -> EmployeeHelper.validatePostCode(postCode) );
    }
}