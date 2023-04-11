package com.drhs.mapper;

import com.drhs.entity.Employees;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 1011
* @description 针对表【EMPLOYEES(employees table. Contains 107 rows. References with departments,
jobs, job_history tables. Contains a self reference.)】的数据库操作Mapper
* @createDate 2023-04-10 14:28:22
* @Entity com.drhs.entity.Employees
*/

@Mapper
public interface EmployeesMapper extends BaseMapper<Employees> {

}




