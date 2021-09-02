package com.lee.spring.cloud.handler;

import com.lee.spring.cloud.api.EmployeeRemoteService;
import com.lee.spring.cloud.entity.Employee;
import com.lee.spring.cloud.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HumanResourceHandler {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeRemoteService employeeRemoteService;

    @RequestMapping("/consumer/get/employee")
    public Employee getEmployeeRemote() {
    // 远程调用方法的主机地址
        String host = "http://lee-provider";
    // 远程调用方法的具体 URL 地址
        String url = "/provider/get/employee/remote";
        return restTemplate.getForObject(host + url, Employee.class);
    }

    @RequestMapping("/consumer/hystrix/employee")
    public ResultEntity<Employee> getEmployeeHystrix(@RequestParam String signal) {

        return employeeRemoteService.getEmployeeCircuit(signal);
    }
}
