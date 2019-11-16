package com.dfbz.service.impl;

import com.dfbz.dao.ExamineMapper;
import com.dfbz.entity.Examine;
import com.dfbz.service.ExamineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description 检测
 * @date 2019/11/15 19
 */
@Service
@Transactional
public class ExamineServiceImpl extends IServiceImpl<Examine> implements ExamineService {
    @Override
    public PageInfo<Examine> selectAll(Map<String, Object> params) {
        //默认值设置
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }
        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));
        ExamineMapper examineMapper = (ExamineMapper) mapper;
        List<Examine> ex = examineMapper.selectByCondition(params);
        PageInfo<Examine> pageInfo = new PageInfo<>(ex);
        return pageInfo;
    }
}
