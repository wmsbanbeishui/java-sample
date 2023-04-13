package com.drhs.mapper;

import com.drhs.entity.AuthItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 1011
* @description 针对表【AUTH_ITEM(权限表)】的数据库操作Mapper
* @createDate 2023-04-12 11:33:26
* @Entity com.drhs.entity.AuthItem
*/
@Mapper
public interface AuthItemMapper extends BaseMapper<AuthItem> {
    List<AuthItem> findAuthListByUserId(@Param("userId") Long userId);
}




