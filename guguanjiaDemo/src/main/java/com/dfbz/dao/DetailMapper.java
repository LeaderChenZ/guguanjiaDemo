package com.dfbz.dao;

import com.dfbz.entity.Detail;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DetailMapper extends Mapper<Detail> {
    @Select("select  de.*,wa.`name`,wa.`code` wasteCode,wt.`code` from  " +
            " detail de ,waste wa ,waste_type wt  " +
            " where de.work_order_id=#{id}  " +
            " and  " +
            " de.waste_id = wa.id  " +
            " and   " +
            " de.waste_type_id = wt.id")
    List<Detail> selectById(Long id);
}