package v1ch05.arrayList;

import java.util.*;

/**
 * This program demonstrates the ArrayList class.
 *
 * @author Cay Horstmann
 * @version 1.11 2012-01-26
 */
public class ArrayListTest {
    public static void main(String[] args) {
        Integer x= 3;
        tripleTest(x);
        System.out.println(x);
    }

    private static void test1() {
        // fill the staff array list with three Employee objects
        ArrayList<Employee> staff = new ArrayList<>();

        staff.add(new Employee("Carl Cracker", 75000, 1987, 12, 15));
        staff.add(new Employee("Harry Hacker", 50000, 1989, 10, 1));
        staff.add(new Employee("Tony Tester", 40000, 1990, 3, 15));

        // raise everyone's salary by 5%
        for (Employee e : staff) {
            e.raiseSalary(5);
        }

        // print out information about all Employee objects
        for (Employee e : staff) {
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary() + ",hireDay="
                    + e.getHireDay());
        }
    }

    public static void testArray() {
        ArrayList<Employee> list = new ArrayList(100);//capacity100，size0
        list.set(0, new Employee("Tony Tester", 40000, 1990, 3, 15));//noelement0yet

    }

    public static void tripleTest(Integer x) {
        x = x * 3;
        System.out.println(x);
    }
}