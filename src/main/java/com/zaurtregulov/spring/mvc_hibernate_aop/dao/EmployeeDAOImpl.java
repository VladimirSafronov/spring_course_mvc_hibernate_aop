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
    session.saveOrUpdate(employee); //если id == 0 - save, либо update

  }

  public Employee getEmployee(int id) {

    Session session = sessionFactory.getCurrentSession();
    return session.get(Employee.class, id);
  }

  public void deleteEmployee(int id) {

    Session session = sessionFactory.getCurrentSession();
    Query<Employee> query = session.createQuery("delete from Employee where id =:employeeId"); //вместо :employeeId мы запишем параметр
    query.setParameter("employeeId", id);
    query.executeUpdate(); //executeUpdate() отвечает как за update так и за delete операции
  }
}
