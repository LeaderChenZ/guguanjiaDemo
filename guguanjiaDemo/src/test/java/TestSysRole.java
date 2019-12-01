import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysRoleMapper;
import com.dfbz.entity.SysRole;
import com.dfbz.service.SysRoleService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/26 09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSysRole {

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysRoleService service;

    @Test
    public void test01() {
        Map<String, Object> map = new HashMap<>();
        PageInfo<SysRole> sysRolePageInfo = service.selectByCondition(map);
        System.out.println(sysRolePageInfo);
    }

    @Test
    public void test02() {
        int i = service.updateByUids(24, 40);
    }


}
