package com.yyc.sb.springboot04web.controller;

import com.yyc.sb.springboot04web.bean.Department;
import com.yyc.sb.springboot04web.bean.Employee;
import com.yyc.sb.springboot04web.dao.DepartmentDao;
import com.yyc.sb.springboot04web.dao.EmployeeDao;
import com.yyc.sb.springboot04web.dao.impl.DepartmentDaoImpl;
import com.yyc.sb.springboot04web.dao.impl.EmployeeDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Collection;

@Controller
@RequestMapping(value = "/admin")
public class EmployeeController {
    @Autowired
    public EmployeeDao employeeDao;

    @Autowired
    public DepartmentDao departmentDao;

    // 员工列表页面
    @GetMapping(value = "/employees")
    public String listView(Model model) {
        // 查询所有员工
        Collection<Employee> employees = employeeDao.getAll();
        // 存入request域中
        model.addAttribute("employees", employees);

        // thymeleaf会加载classpath:/template/employee/list.html
        return "employee/list";
    }

    // 员工添加页面
    @GetMapping(value = "/employee")
    public String addView(Model model) {
        // 查询所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        return "/employee/add-and-update";
    }

    // 员工添加操作
    @PostMapping(value = "/employee")
    public String add(Employee employee) {
        // 添加员工操作
        System.out.println(employee);
        employeeDao.save(employee);
        // 转发到列表页面
        return "redirect:/admin/employees";
    }

    // 员工修改页面
    @GetMapping(value = "/employee/{id}")
    public String updateView(@PathVariable("id") Integer id, Model model) {
        // 查询当前需要修改的员工
        Employee employee = employeeDao.get(id);

        // 查询所有部门
        Collection<Department> departments = departmentDao.getDepartments();

        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "employee/add-and-update";
    }

    // 修改员工
    @PutMapping(value = "/employee/{id}")
    public String update(Employee employee) {
        // 修改
        employeeDao.save(employee);
        return "redirect:/admin/employees";
    }

    // 删除员工
    @DeleteMapping(value = "/employee/{id}")
    public String delete(@PathVariable("id") Integer id) {
        // 删除
        employeeDao.delete(id);
        return "redirect:/admin/employees";
    }
}
