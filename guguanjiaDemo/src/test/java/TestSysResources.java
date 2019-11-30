import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.SysResource;
import com.dfbz.service.SysResourceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/11/26 09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSysResources {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysResourceService service;


    @Test
    public void test3(){
        List<SysResource> sysResources = service.selectAllByUid(45L);
        for (SysResource sysResource : sysResources) {
            System.out.println(sysResource);
        }
    }
}
