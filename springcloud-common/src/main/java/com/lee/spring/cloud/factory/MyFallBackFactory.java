package com.lee.spring.cloud.factory;

import com.lee.spring.cloud.api.EmployeeRemoteService;
import com.lee.spring.cloud.entity.Employee;
import com.lee.spring.cloud.util.ResultEntity;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Component
public class MyFallBackFactory implements FallbackFactory<EmployeeRemoteService> {
    // cause 对象是失败原因对应的异常对象
    @Override
    public EmployeeRemoteService create(Throwable cause) {
        return new EmployeeRemoteService() {
            @Override
            public Employee getEmployeeRemote() {
                return null;
            }

            @Override
            public List<Employee> getEmployeesRemote(@RequestParam("keyword") String keyword) {
                return null;
            }

            @Override
            public ResultEntity<Employee> getEmployeeCircuit(String signal) {
                return ResultEntity.failed(cause.getMessage());
            }
        };
    }
}
