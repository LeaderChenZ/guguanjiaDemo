import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysUserMapper;
import com.dfbz.entity.SysUser;
import com.dfbz.service.SysUserService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/26 09
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSysUser {

    @Autowired
    SysUserMapper userMapper;

    @Autowired
    SysUserService service;

    @Test
    public void test01() {
        Map<String, Object> map = new HashMap<>();
        List<SysUser> sysUsers = userMapper.selectByCondition(map);
        for (SysUser sysUser : sysUsers) {
            System.out.println(sysUser);
        }
    }

    @Test
    public void test02() {
        Map<String, Object> map = new HashMap<>();
        PageInfo<SysUser> sysUserPageInfo = service.selectByCondition(map);
        System.out.println(sysUserPageInfo);
    }
}
