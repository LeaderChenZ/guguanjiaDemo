package com.dfbz.dao;

import com.dfbz.entity.Transfer;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TransferMapper extends Mapper<Transfer> {
    @Select("select tr.*,su.`name` " +
            " from transfer tr ,sys_user su " +
            " where tr.work_order_id = #{id} " +
            " and tr.oprate_user_id = su.id " +
            " ORDER BY tr.create_date DESC")
     List<Transfer> selectById(Long id);
    }
