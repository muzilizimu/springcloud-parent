package com.lee.spring.cloud.api;

import com.lee.spring.cloud.entity.Employee;
import com.lee.spring.cloud.factory.MyFallBackFactory;
import com.lee.spring.cloud.util.ResultEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "lee-provider",fallbackFactory = MyFallBackFactory.class)
public interface EmployeeRemoteService {
    @RequestMapping("/provider/get/employee/remote")
    Employee getEmployeeRemote();

    @RequestMapping("/provider/get/employees/remote")
    List<Employee> getEmployeesRemote(@RequestParam("keyword") String keyword);

    @RequestMapping("/hystrix/get/employee")
    ResultEntity<Employee> getEmployeeCircuit(@RequestParam( value = "signal",required = false) String signal);
}
