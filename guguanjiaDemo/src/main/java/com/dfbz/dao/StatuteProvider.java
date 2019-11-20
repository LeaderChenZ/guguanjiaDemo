package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/20 19
 */

public class StatuteProvider {
    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT   " +
                "se.area_id,   " +
                "se.type,   " +
                "se.title,   " +
                "se.pub_date,   " +
                "se.`code`,   " +
                "se.stem_from,   " +
                "se.description,   " +
                "se.create_date,   " +
                "se.update_date,   " +
                "se.del_flag,   " +
                "se.create_by,   " +
                "se.id   " +
                "FROM   " +
                "statute AS se   " +
                "WHERE   " +
                "se.del_flag = 0");
        if (!StringUtils.isEmpty(params.get("type"))){
            sb.append(" and se.type=#{type}");
        }
        return sb.toString();
    }
}
