package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/27 14
 */
public class SysUserProvider {

    public String selectByCondition(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT " +
                "su.*,so.name officeName " +
                "FROM " +
                "sys_user su " +
                "LEFT JOIN " +
                "sys_office so " +
                "ON " +
                " su.office_id=so.id ");

        if (params.containsKey("rid") && !StringUtils.isEmpty(params.get("rid"))) {
            sb.append("left join  " +
                    "sys_user_role sur   " +
                    "on   " +
                    "sur.user_id = su.id  " +
                    "left join  " +
                    "sys_role sr   " +
                    "on  " +
                    "sur.role_id = sr.id " +
                    " where su.del_flag=0 ");
            sb.append(" and sr.id=#{rid} ");
        } else {
            sb.append("where su.del_flag=0");
        }
        if (params.containsKey("uid") && !StringUtils.isEmpty(params.get("uid"))) {
            sb.append(" and su.id=#{uid} ");
        }
        if (params.containsKey("oid") && !StringUtils.isEmpty(params.get("oid"))) {
            sb.append(" and so.id=#{oid} ");
        }
        if (params.containsKey("name") && !StringUtils.isEmpty(params.get("name"))) {
            sb.append(" AND su.name like CONCAT('%',#{name},'%') ");
        }
        return sb.toString();
    }
}
