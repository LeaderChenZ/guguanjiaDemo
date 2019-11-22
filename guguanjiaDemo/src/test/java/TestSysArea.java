import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.SysAreaMapper;
import com.dfbz.entity.SysArea;
import com.dfbz.listener.SysAreaListener;
import com.dfbz.service.SysAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/11/15 20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestSysArea {

    @Autowired
    SysAreaMapper areaMapper;

    @Autowired
    SysAreaService service;


    @Test
    public void test01() {
        Map<String, Object> map = new HashMap<>();
//        map.put("parentName","区");
        service.selectByCondition(map);
    }

    @Test
    public void test02() throws FileNotFoundException {
        List<SysArea> list = areaMapper.selectByPid(1);
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\area\\area2.xlsx");
        ExcelWriter excelWriter = EasyExcel.write(fileOutputStream, SysArea.class).build();

        WriteSheet build = EasyExcel.writerSheet(0).build();
        excelWriter.write(list, build);
        excelWriter.finish();
    }

    @Test
    public void testRead(){
        ExcelReader excelReader = EasyExcel.read("D:\\area\\area.xlsx",SysArea.class,new SysAreaListener()).build();
        ReadSheet readSheet = EasyExcel.readSheet(0).build();
        excelReader.read(readSheet);
        excelReader.finish();
    }

    @Test
    public void test03(){
        List<SysArea> list = areaMapper.selectAll();
        int i = areaMapper.insertBatch(list);
    }

    @Test
    public void test04(){
        List<SysArea> list = service.selectByAreaId(2);
        for (SysArea sysArea : list) {
            System.out.println(sysArea);
        }
    }
}
