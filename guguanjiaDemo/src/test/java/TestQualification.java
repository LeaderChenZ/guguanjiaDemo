import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.QualificationMapper;
import com.dfbz.entity.Qualification;
import com.dfbz.service.QualificationService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/14 20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestQualification {

    @Autowired
    QualificationMapper mapper;
    //
    @Autowired
    QualificationService service;

    @Test
    public void testQualification() {
        Example exemple = new Example(Qualification.class);//select * from  qualification

        //创建一个动态sql  构建对象   andEqualTo(属性名，属性值)
        Example.Criteria criteria = exemple.createCriteria().andEqualTo("type", 1);
        criteria.andEqualTo("check", 0).andGreaterThan("updateDate", "2019-11-14").andLessThan("updateDate", "2018-01-11");
        List<Qualification> qualifications = mapper.selectByExample(exemple);
        for (Qualification qualification : qualifications) {
            System.out.println(qualification);
        }
    }


    @Test
    public void testQualicationService(){
        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("type","1");
        map.put("end","2018-01-11");
        PageInfo<Qualification> qualificationPageInfo = service.selectByCondition(map);
        System.out.println(qualificationPageInfo);
    }
}
