import com.dfbz.config.SpringMybatis;
import com.dfbz.dao.WorkOrderMapper;
import com.dfbz.entity.Statute;
import com.dfbz.entity.WorkOrder;
import com.dfbz.service.StatuteService;
import com.dfbz.service.WorkOrderService;
import com.github.pagehelper.PageInfo;
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
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatis.class)
public class TestWorkOrder {

    @Autowired
    WorkOrderService workOrderService;

    @Autowired
    WorkOrderMapper mapper;

    @Autowired
    StatuteService statuteService;


    @Test
    public void test() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("status", 2);
        map.put("officeName", "重庆市利特环保工程有限公司（黑石子）");
        map.put("begin", "2016-09-20");
//        PageInfo<WorkOrder> pageInfo = workOrderService.selectByCondition(map);
        List<WorkOrder> workOrders = mapper.selectByCondition(map);
        for (WorkOrder workOrder : workOrders) {
            System.out.println(workOrder);
        }
    }

    @Test
    public void test01(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("pageNum",2);
        map.put("pageSize",7);
        map.put("type",1);
        PageInfo<Statute> pageInfo = statuteService.selectByCondition(map);
        System.out.println(pageInfo);

    }
}
