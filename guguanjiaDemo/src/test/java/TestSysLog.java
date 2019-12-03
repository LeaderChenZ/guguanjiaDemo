import com.dfbz.config.SpringMvcConfig;
import com.dfbz.config.SpringMybatis;
import com.dfbz.controller.AppVersionController;
import com.dfbz.entity.AppVersion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Chen
 * @description
 * @date 2019/11/15 20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMybatis.class, SpringMvcConfig.class})
@WebAppConfiguration
public class TestSysLog {

     @Autowired
     private AppVersionController appVersionController;

     @Test
    public void test(){
         AppVersion appVersion = appVersionController.toUpdate(1l);

     }
}
