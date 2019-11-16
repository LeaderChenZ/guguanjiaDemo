package com.dfbz.service.impl;

import com.dfbz.dao.QualificationMapper;
import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.Qualification;
import com.dfbz.entity.SysUser;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.io.File;
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


    @Autowired
    SysUserMapper userMapper;

    @Value("${imgPath}")
    String imgPath;

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

        QualificationMapper qualificationMapper = (QualificationMapper) mapper;

//        //根据qualification中的uploadUserId 和 checkUserId 查询数据
        for (Qualification qualification : qualifications) {
            Map<String, Object> selectNames = qualificationMapper.selectNames(qualification.getId());
            qualification.setCheckUserName((String) selectNames.get("checkUserName"));
            qualification.setUploadUserName((String) selectNames.get("uploadUserName"));
        }


        return new PageInfo<Qualification>(qualifications);
    }

    @Override
    public Qualification selectByPrimaryKey(Object key) {
        Qualification qualification = mapper.selectByPrimaryKey(key);
        SysUser sysUser = userMapper.selectByPrimaryKey(qualification.getUploadUserId());

        qualification.setAddress(imgPath + sysUser.getOfficeId() + File.separator + qualification.getAddress());
        return qualification;
    }
}
