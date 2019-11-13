import com.alibaba.druid.pool.DruidDataSource;
import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.AppVersionMapper;
import com.dfbz.entity.AppVersion;
import com.dfbz.service.AppVersionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSSM {

    @Autowired
    DruidDataSource druidDataSource;

    @Autowired
    SqlSessionFactory factory;

    @Autowired
    AppVersionMapper mapper;

    @Autowired
    AppVersionService service;

    @Test
    public void testConn() throws SQLException {
        /*System.out.println(druidDataSource.getConnection());
        System.out.println(factory.openSession().getConnection());*/

        List<AppVersion> appVersions = service.selectAll();
        for (AppVersion appVersion : appVersions) {
            System.out.println(appVersion);
        }

    }

    @Test
    public void test01() {
        PageHelper.startPage(2, 3);
        List<AppVersion> appVersions = mapper.selectAll();
        PageInfo<AppVersion> pageInfo = new PageInfo<>(appVersions);
        System.out.println(pageInfo.getList());
    }
    @Test
    public void test02(){
        AppVersion appVersion = service.selectByPrimaryKey(1);
        System.out.println(appVersion);
    }


}
