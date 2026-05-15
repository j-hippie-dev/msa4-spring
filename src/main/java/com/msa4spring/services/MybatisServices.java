package com.msa4spring.services;

import com.msa4spring.entities.Employee;
import com.msa4spring.mappers.EmployeeMapper;
import com.msa4spring.requests.EmployeesStoreRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MybatisServices {
    private final EmployeeMapper employeeMapper;

    public List<Employee> getEmployees() {
        return employeeMapper.getEmployees();
    }

    @Transactional
    public Employee store(EmployeesStoreRequest employeesStoreRequest) {
        Employee employee = new Employee();
        employee.setBirth(employeesStoreRequest.birth());
        employee.setGender(employeesStoreRequest.gender());
        employee.setName(employeesStoreRequest.name());

        employeeMapper.store(employee);

        return employeeMapper.findByPk(employee.getEmpId());
    }
}