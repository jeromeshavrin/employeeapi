package com.example.employeeapi.controller;

import com.example.employeeapi.model.Employee;
import com.example.employeeapi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "index";
    }

   @PostMapping("/save")
public String saveEmployee(@ModelAttribute Employee employee) {
    System.out.println("Saving: " + employee.getName() + ", " + employee.getEmail() + ", " + employee.getLocation());
    employeeRepository.save(employee);
    return "redirect:/"; 
}

    @GetMapping("/displayAll")
    @ResponseBody
    public List<Employee> displayAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/display/{id}")
    @ResponseBody
    public Employee getEmployeeById(@PathVariable String id) {
        return employeeRepository.findById(id).orElse(null);
    }
 @GetMapping("/employees")
public String viewAllEmployees(Model model) {
    model.addAttribute("employees", employeeRepository.findAll());
    return "employees";
}
    
}

