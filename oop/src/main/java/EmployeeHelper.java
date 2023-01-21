public interface EmployeeHelper {


    static void validateName(String name) {

        if (name == null || name.trim().length() < 1)
            throw new IllegalArgumentException("Employee name should not be empty");

        if( !name.matches("[a-zA-Z]*"))
            throw new IllegalArgumentException("Name contains letter spaces, apostrophes and hyphens");
    }

    static void validateSurName(String surname) {

        if (surname == null || surname.trim().length() < 1)
            throw new IllegalArgumentException("Employee surname should not be empty");

        if( surname.matches("[a-zA-Z]*"))
            throw new IllegalArgumentException("Surname contains letter spaces, apostrophes and hyphens");
    }

    static void validateSalary(double salary) {

        if (salary < 1000)
            throw new IllegalArgumentException("Salary should be greater than 1000 TL. But it is just " + salary);
    }

    static boolean validateStreet(String street) {

         // \d+\s+([a-zA-Z1-9]+\s[a-zA-Z]*\s[a-zA-Z]*)
         // \d+\s+([a-zA-Z]+|[a-zA-Z]+\s[a-zA-Z]+)
        if (!street.matches("\\d+\\s+([a-zA-Z1-9]+\\s?[a-zA-Z]*\\s?[a-zA-Z]*\\s?[a-zA-Z]*)"))
            throw new IllegalArgumentException("Street sample is number + string : 12567 Lillian Parkaway");

        return true;

    }

    static void validateCity(String street) {

        //
        //
        if (!street.matches("([a-zA-Z]+\\s[a-zA-Z]*)"))
            throw new IllegalArgumentException("City sample is letters, spaces : Los Pinos");

    }

    static void validatePostCode(String postCode) {

        if (!postCode.matches("\\d{5}"))
            throw new IllegalArgumentException("Post code contains 5 digit numbers : 01234");

    }
}


