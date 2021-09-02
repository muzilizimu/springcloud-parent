package com.lee.spring.cloud.handler;

import com.lee.spring.cloud.entity.Employee;
import com.lee.spring.cloud.util.ResultEntity;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeHandler {

    @RequestMapping("/provider/get/employee/remote")
    public Employee getEmployeeRemote() {
        return new Employee(555, "tom555", 555.55);
    }

    @RequestMapping("/provider/get/employees/remote")
    List<Employee> getEmployeesRemote(@RequestParam String keyword) {

        List<Employee> lists = new ArrayList<>();
        lists.add(new Employee(1,"lee", 10000.0));
        lists.add(new Employee(2,"wang", 9999.0));
        lists.add(new Employee(2,keyword, 9999.0));

        return lists;
    }
    @HystrixCommand(fallbackMethod = "getEmployeeCircuitBackup")
    @ResponseBody
    @RequestMapping("/hystrix/get/employee")
    public ResultEntity<Employee> getEmployeeCircuit(@RequestParam( value = "signal",required = false) String signal){

        if ("quick-bang".equals(signal)){
            throw new RuntimeException();
        }
        if ("slow-bang".equals(signal)){
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Employee employee = new Employee(1,"lee", 10000.0);
        return ResultEntity.successWithData(employee);
    }

    public ResultEntity<Employee> getEmployeeCircuitBackup(@RequestParam( value = "signal",required = false) String signal){

        return ResultEntity.failed("provide服务出错" + signal);
    }


}
