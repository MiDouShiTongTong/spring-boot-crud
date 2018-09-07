package com.yyc.sb.springboot04web.dao;

import com.yyc.sb.springboot04web.bean.Department;

import java.util.Collection;

public interface DepartmentDao {
    Collection<Department> getDepartments();

    Department getDepartment(Integer id);
}
