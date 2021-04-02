package io.fikru.EmployeeManager.service;

import io.fikru.EmployeeManager.exception.UserNotFoundException;
import io.fikru.EmployeeManager.model.Employee;
import io.fikru.EmployeeManager.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class EmployeeService {

//    @Autowired => private EmployeeRepo employeeRepo;

      private final EmployeeRepo employeeRepo;
      @Autowired
      public EmployeeService(EmployeeRepo employeeRepo){this.employeeRepo = employeeRepo;}

      public Employee addEmployee(Employee employee){
          employee.setEmployeeCode(UUID.randomUUID().toString());
          return employeeRepo.save(employee);
      }

      public List<Employee> getAllEmployee(){
          return employeeRepo.findAll();
      }

      public Employee updateEmployee(Long id, Employee employee){
          Employee emp = employeeRepo.getOne(id);
          emp.setName(employee.getName());
          emp.setEmail(employee.getEmail());
          emp.setJobTitle(employee.getJobTitle());
          emp.setImageUrl(employee.getImageUrl());
          emp.setPhone(employee.getPhone());
          return employeeRepo.save(emp);

      }

      public void deleteEmployee(Long id){
          employeeRepo.deleteEmployeeById(id);
      }

      public Employee findEmployeeById(Long id){
          return employeeRepo.findEmployeeById(id).
                  orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not found"));
      }




}
