package com.drhs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.drhs.entity.Employees;
import com.drhs.service.EmployeesService;
import com.drhs.mapper.EmployeesMapper;
import org.springframework.stereotype.Service;

/**
* @author 1011
* @description 针对表【EMPLOYEES(employees table. Contains 107 rows. References with departments,
jobs, job_history tables. Contains a self reference.)】的数据库操作Service实现
* @createDate 2023-04-10 14:28:22
*/
@Service
public class EmployeesServiceImpl extends ServiceImpl<EmployeesMapper, Employees>
    implements EmployeesService {

}




