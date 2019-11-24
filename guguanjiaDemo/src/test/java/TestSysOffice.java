import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysOfficeMapper;
import com.dfbz.service.SysOfficeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSysOffice {

    @Autowired
    SysOfficeService service;

    @Autowired
    SysOfficeMapper mapper;
    @Test
    public void test01(){
        Map<String,Object>  map = new HashMap<>();
        service.selectByCondition(map);
       /* List<SysOffice> sysOffices = mapper.selectByCondition(map);
        for (SysOffice sysOffice : sysOffices) {
            System.out.println(sysOffice);
        }*/
    }

}
