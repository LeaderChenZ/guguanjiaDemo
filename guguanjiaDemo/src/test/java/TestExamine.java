import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.ExamineMapper;
import com.dfbz.entity.Examine;
import com.dfbz.service.ExamineService;
import com.dfbz.service.SysOfficeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/11/15 20
 */@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestExamine {

     @Autowired
    ExamineService examineService;

     @Autowired
    ExamineMapper mapper;

     @Autowired
    SysOfficeService service;

     @Test
    public void test(){
         HashMap<String, Object> map = new HashMap<>();
         map.put("userName", "工作");
         /*PageInfo<Examine> pageInfo = examineService.selectAll(map);
         System.out.println(pageInfo);*/

         List<Examine> examines = mapper.selectByCondition(map);
         System.out.println(examines);

     }
}
