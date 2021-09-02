package com.lee.spring.cloud.handler;

import com.lee.spring.cloud.api.EmployeeRemoteService;
import com.lee.spring.cloud.entity.Employee;
import com.lee.spring.cloud.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FeignHumanResourceHandler {

    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/feign/consumer/get/employee")
    public Employee getEmployeeRemote() {

        return employeeRemoteService.getEmployeeRemote();
    }

    @RequestMapping("/feign/consumer/get/employees")
    public List<Employee> getEmployeesRemote(@RequestParam("keyword") String keyword){

        return employeeRemoteService.getEmployeesRemote(keyword);
    }

    @RequestMapping("/hystrix/consumer/get/employees")
    public ResultEntity<Employee> getEmployeesRemoteHystrix(@RequestParam("keyword") String keyword){

        return employeeRemoteService.getEmployeeCircuit("111");
    }

}
