package com.yyc.sb.springboot04web.dao.impl;

import java.util.*;

import com.yyc.sb.springboot04web.bean.Department;
import com.yyc.sb.springboot04web.bean.Employee;
import com.yyc.sb.springboot04web.dao.DepartmentDao;
import com.yyc.sb.springboot04web.dao.EmployeeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/*
    @Repository，此类为DAO类，会注入到容器中
 */
@Repository
public class EmployeeDaoImpl implements EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new TreeMap<Integer, Employee>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        employees.put(1001, new Employee(1001, "E-AA", "aa@163.com", 1, new Department(101, "D-AA")));
        employees.put(1002, new Employee(1002, "E-BB", "bb@163.com", 1, new Department(102, "D-BB")));
        employees.put(1003, new Employee(1003, "E-CC", "cc@163.com", 0, new Department(103, "D-CC")));
        employees.put(1004, new Employee(1004, "E-DD", "dd@163.com", 0, new Department(104, "D-DD")));
        employees.put(1005, new Employee(1005, "E-EE", "ee@163.com", 1, new Department(105, "D-EE")));
    }

    private static Integer initId = 1006;

    @Override
    public void save(Employee employee) {
        if (employee.getId() == null) {
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);

    }

    //查询所有员工
    @Override
    public Collection<Employee> getAll() {
        return employees.values();
    }

    @Override
    public Employee get(Integer id) {
        return employees.get(id);
    }

    @Override
    public void delete(Integer id) {
        employees.remove(id);
    }
}
