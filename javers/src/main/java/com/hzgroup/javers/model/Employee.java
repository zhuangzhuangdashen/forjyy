package com.hzgroup.javers.model;

import com.hzgroup.javers.utils.ToStringBuilder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.javers.core.metamodel.annotation.Id;
import org.javers.core.metamodel.annotation.TypeName;

import java.time.ZonedDateTime;
import java.util.*;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by LIZHUANGZHUANG001 on 2019/3/11.
 */
@Setter
@Getter
@ToString
@TypeName("Employee")
public class Employee {

    @Id
    private String name;

    private Position position;

    private int salary;

    private int age;

    private Employee boss;

    private List<Employee> subordinates = new ArrayList<>();

    private Address primaryAddress;

    private Address postalAddress;

    private Set<String> skills;

    private Map<Integer, String> performance;

    private ZonedDateTime lastPromotionDate;

    public Employee() {
    }

    public Employee(String name) {
        this(name, 10000);
    }

    public Employee(String name, int salary) {
        checkNotNull(name);
        this.name = name;
        this.salary = salary;
    }

    public Employee(String name, int salary, String position) {
        checkNotNull(name);
        checkNotNull(position);
        this.name = name;
        this.salary = salary;
        this.position = Position.valueOf(position);
    }

    public Employee addSubordinate(Employee employee) {
        checkNotNull(employee);
        employee.boss = this;
        subordinates.add(employee);
        return this;
    }

    public Employee addSubordinates(Employee... employees) {
        checkNotNull(employees);
        for (Employee e : employees) {
            addSubordinate(e);
        }
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Employee)) {
            return false;
        }
        Employee that = (Employee) obj;

        return this.name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}