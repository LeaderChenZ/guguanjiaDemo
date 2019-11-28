package com.dfbz.dao;

import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/28 17
 */
public class SysRoleProvider {
        public String selectByCondition(Map<String,Object> params){
            StringBuilder sb = new StringBuilder();
            sb.append("select " +
                    " sr.*,so.name officeName " +
                    "from " +
                    " sys_role sr,sys_office so " +
                    "where " +
                    " sr.office_id=so.id " +
                    "and" +
                    " sr.del_flag=0 "
            );

            if(params.containsKey("dataScope")&&!StringUtils.isEmpty(params.get("dataScope"))){

                sb.append(" and sr.data_score=#{dataScope}  ");
            }
            if(params.containsKey("remarks")&&!StringUtils.isEmpty(params.get("remarks"))){
                sb.append(" and sr.remarks=#{remarks} ");
            }

            if(params.containsKey("oid")&&!StringUtils.isEmpty(params.get("oid"))){
                sb.append(" and so.id=#{oid} ");
            }

            if(params.containsKey("name")&&!StringUtils.isEmpty(params.get("name"))){
                sb.append(" AND sr.name like CONCAT('%',#{name},'%') ");
            }
            return sb.toString();
        }



}
