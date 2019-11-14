package com.dfbz.service.impl;

import com.dfbz.entity.Qualification;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description qualification
 * @date 2019/11/14 20
 */
@Service
@Transactional
public class QualificationServiceImpl extends IServiceImpl<Qualification> implements QualificationService {


    /*
     *根据条件判断，进行动态sql
     *
     * params：pageNum  pageSize  type check begin end
     *
     * */
    @Override
    public PageInfo<Qualification> selectByCondition(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));

        //拼接sql   调用mapper方法
        Example example = new Example(Qualification.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("delFlag", "0");
        if (!StringUtils.isEmpty(params.get("type"))) {
            criteria.andEqualTo("type", params.get("type"));
        }
        if (!StringUtils.isEmpty(params.get("check"))) {
            criteria.andEqualTo("check", params.get("check"));
        }
        if (!StringUtils.isEmpty(params.get("begin"))) {
            criteria.andGreaterThan("createDate", params.get("begin"));
        }
        if (!StringUtils.isEmpty(params.get("end"))) {
            criteria.andLessThan("createDate", params.get("end"));
        }

        List<Qualification> qualifications = mapper.selectByExample(example);

//        QualificationMapper qualificationMapper = (QualificationMapper) mapper;
//
//        //根据qualification中的uploadUserId 和 checkUserId 查询数据
//        for (Qualification qualification : qualifications) {
//            qualificationMapper.selectNames()
//        }


        return new PageInfo<Qualification>(qualifications);
    }
}
