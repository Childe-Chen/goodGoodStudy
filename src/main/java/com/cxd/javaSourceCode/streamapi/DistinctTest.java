package com.cxd.javaSourceCode.streamapi;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * desc
 *
 * @author childe
 * @date 2018/7/30 14:48
 **/
public class DistinctTest {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("zcdd"));
        personList.add(new Person("cd"));
        personList.add(new Person("tcdd"));
        personList.add(new Person("acdd"));
        personList.add(new Person("vcdd"));
        personList.add(new Person("ccd"));
        personList.add(new Person("vcdd"));
        personList.add(new Person("cd"));

        List<String> nameList = personList.stream()
                .map(Person::getName).collect(Collectors.toList());

        nameList.forEach(System.out::println);

        System.out.println("----------------");
        List<String> nameList1 = nameList.stream().distinct().collect(Collectors.toList());

        nameList1.forEach(System.out::println);

        System.out.println("----------------");
        List<String> nameList2 = personList.stream()
                .map(Person::getName).distinct().collect(Collectors.toList());
        nameList2.forEach(System.out::println);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Person {
        private String name;

        Person (String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return getName();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            return new EqualsBuilder()
                    .append(name, person.name)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(name)
                    .toHashCode();
        }
    }
}
