package com.zaurtregulov.spring.mvc_hibernate_aop.controller;

import com.zaurtregulov.spring.mvc_hibernate_aop.dao.EmployeeDAO;
import com.zaurtregulov.spring.mvc_hibernate_aop.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

  // добавляем dependency injection (зависимость)
  @Autowired
  private EmployeeDAO employeeDAO;

  @RequestMapping("/")
  public String showAllEmployees(Model model) {

    List<Employee> allEmployees = employeeDAO.getAllEmployees();
    //добавляем в модель аттрибут с именем и значением (теперь view сможет отобразить значения аттрибута в браузере)
    model.addAttribute("allEmps", allEmployees);
    return "all-employees";
  }
}
