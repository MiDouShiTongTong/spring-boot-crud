package com.yyc.sb.springboot04web.dao;

import com.yyc.sb.springboot04web.bean.Employee;

import java.util.Collection;

public interface EmployeeDao {
    void save(Employee employee);

    //查询所有员工
    Collection<Employee> getAll();

    Employee get(Integer id);

    void delete(Integer id);
}
