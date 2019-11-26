import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysOfficeMapper;
import com.dfbz.entity.SysOffice;
import com.dfbz.service.SysOfficeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSysOffice {

    @Autowired
    SysOfficeService service;

    @Autowired
    SysOfficeMapper mapper;

    @Autowired
    RedisTemplate<String, Object> template;

    @Test
    public void test01() {
        List<SysOffice> sysOffices = service.selectAll();
        System.out.println(sysOffices);
    }

    @Test
    public void testSelectAll() {
        List<SysOffice> sysOffices = service.selectAll();

        //数据放入redis缓存起来
        ValueOperations<String, Object> operations = template.opsForValue();
        //将集合放入value为string结构的redis结构中，template会根据系列化策略，将sysOffice转换成string类型
        operations.set("sysOffice", sysOffices);
        System.out.println("_________");

        System.out.println(operations.get("sysOffice"));
    }

    @Test
    public void testSelectOid() {
        SysOffice sysOffice1 = service.selectByOid(2);
        System.out.println(sysOffice1);
        SysOffice sysOffice2 = service.selectByOid(2);
        System.out.println(sysOffice2);
        SysOffice sysOffice3 = service.selectByOid(5);
        System.out.println(sysOffice3);


    }
}
