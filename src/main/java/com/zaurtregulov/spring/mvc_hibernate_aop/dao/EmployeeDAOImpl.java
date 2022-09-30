package com.zaurtregulov.spring.mvc_hibernate_aop.dao;

import com.zaurtregulov.spring.mvc_hibernate_aop.entity.Employee;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //аннотация для DAO (Spring будет сканировать, искать эту аннотацию)
public class EmployeeDAOImpl implements EmployeeDAO {

  //внедрение автоматической зависимости от Session factory
  @Autowired
  private SessionFactory sessionFactory;

  public List<Employee> getAllEmployees() {

    //получение сессии
    Session session = sessionFactory.getCurrentSession();

//    List<Employee> allEmployees = session.createQuery("from Employee", Employee.class).getResultList();
    Query<Employee> query = session.createQuery("from Employee", Employee.class);
    List<Employee> allEmployees = query.getResultList();

    return allEmployees;
  }

  public void saveEmployee(Employee employee) {

    Session session = sessionFactory.getCurrentSession();
    session.save(employee);

  }
}
