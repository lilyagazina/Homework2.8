package com.exam.homework28.service;

import java.util.stream.Stream;

import com.exam.homework28.exception.*;
import com.exam.homework28.model.Employee;
import org.assertj.core.api.AbstractBigDecimalAssert;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.ListAssert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.hasSize;


public class EmployeeServiceTest {

    private final EmployeeService employeeService = new EmployeeService(new ValidatorService());

    @BeforeEach
    public void beforeEach() {
        new Employee("Ivan", "Ivanov", 1, 70_000);
        new Employee("Petr", "Petrov", 2, 80_000);
        new Employee("Sergi", "Sergeev", 3, 90_000);
    }

    @AfterEach
    public void afterEach() {
        employeeService.getAll()
                .forEach(employee -> employeeService.remove(employee.getName(), employee.getSurname()));
    }

    public static Stream<Arguments> addWithIncorrectNameTestParams() {
        return Stream.of(
                Arguments.of("Ivan1"),
                Arguments.of("Ivan!"),
                Arguments.of("Ivan@")
        );
    }

    public static Stream<Arguments> addWithIncorrectSurnameTestParams() {
        return Stream.of(
                Arguments.of("Ivanov1"),
                Arguments.of("Ivanov!"),
                Arguments.of("Ivanov@")
        );
    }

    @Test
    public void addTest() {
        int beforeCount = employeeService.getAll().size();
        Employee expected = new Employee("Ivan", "Ivanov", 1, 70_000);

        assertThat(employeeService.add("Ivan", "Ivanov", 1, 70_000))
                .isEqualTo(expected)
                .isIn(employeeService.getAll());
        assertThat(employeeService.getAll()).hasSize(beforeCount + 1);
        assertThat(employeeService.find("Ivan", "Ivanov")).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectNameTestParams")
    public void addWithIncorrectNameTest(String incorrectName) {
        assertThatExceptionOfType(IncorrectNameException.class)
                .isThrownBy(() -> employeeService.add(incorrectName, "Ivan", 1, 70_0000));
    }

    @ParameterizedTest
    @MethodSource("addWithIncorrectSurnameTestParams")
    public void addWithIncorrectSurnameTest(String incorrectSurname) {
        assertThatExceptionOfType(IncorrectSurnameException.class)
                .isThrownBy(() -> employeeService.add("Ivanov", incorrectSurname, 1, 70_0000));
    }

    @Test
    public void addWhenStorageIsFullTest() {
        Stream.iterate(1, i -> i + 1)
                .limit(7)
                .map(number -> new Employee(
                                "Ivan" + ((char) ('a' + number)),
                                "Ivanov" + ((char) ('a' + number)),
                                number,
                                70_000 + number
                        )
                )
                .forEach(employee ->
                        employeeService.add(
                                employee.getName(),
                                employee.getSurname(),
                                employee.getDepartment(),
                                employee.getSalary()
                        )
                );
    }

    @Test
    public void removeWhenNotFoundTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Vasy", "Vasin"));
    }

    @Test
    public void findWhenNotFoundTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Vasy", "Vasin"));
    }
}
