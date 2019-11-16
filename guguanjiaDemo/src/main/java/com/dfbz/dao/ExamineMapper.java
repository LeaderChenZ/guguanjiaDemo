package com.dfbz.dao;

import com.dfbz.entity.Examine;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface ExamineMapper extends Mapper<Examine> {

    /**
     * 动态sql实现  连表查询
     * @param params   {officeId：,      userName: ,     type:}
     * @return
     */

    @SelectProvider(type = ExamineSqlProvider.class,method = "selectByCondition")
    List<Examine> selectByCondition(Map<String,Object> condition);

}