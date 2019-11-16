package com.dfbz.dao;

import com.dfbz.entity.Qualification;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

public interface QualificationMapper extends Mapper<Qualification> {

    @Select(" SELECT   " +
            "  uu.`name` uploadUserName,cu.`name`  checkUserName" +
            " FROM   " +
            "  qualification qu  " +
            " LEFT JOIN  " +
            "  sys_user uu  " +
            " ON  " +
            "  qu.upload_user_id=uu.id  " +
            " LEFT JOIN  " +
            "  sys_user cu  " +
            " ON  " +
            "  qu.check_user_id=cu.id  " +
            " WHERE  " +
            "  qu.id=#{id}  " +
            " ")
    Map<String, Object> selectNames(Long id);
}