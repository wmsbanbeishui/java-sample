package com.drhs.mapper;

import com.drhs.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 1011
* @description 针对表【ADMIN(管理员表)】的数据库操作Mapper
* @createDate 2023-04-11 15:08:23
* @Entity com.drhs.entity.Admin
*/
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}




