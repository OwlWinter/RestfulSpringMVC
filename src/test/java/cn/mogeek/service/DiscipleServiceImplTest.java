package cn.mogeek.service;

import cn.mogeek.mapper.DiscipleMapper;
import cn.mogeek.model.Disciple;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-service.xml", "classpath:spring-mybatis.xml"})
public class DiscipleServiceImplTest {
    @Autowired
    DiscipleService discipleService;

    @Autowired
    DiscipleMapper discipleMapper;

    @Test
    public void insertTest() throws Exception {
        Disciple disciple = new Disciple();
        disciple.setMajor_subject("无限奥义循环");
        disciple.setCome_from("英国");
        disciple.setDaily_report("https://baike.baidu.com/item/%E5%A5%87%E5%BC%82%E5%8D%9A%E5%A3%AB/2492942");
        disciple.setBrother("古一法师");
        disciple.setSlogan("多玛姆，我是来找你谈判的！");
        disciple.setGraduated_school("格林威治村");

        Disciple temp = null;
        try {
            temp = (Disciple)disciple.clone();
        }catch (CloneNotSupportedException e){
            System.out.println(e);
            return;
        }

        long num;
        for (int i = 1; i <= 10; i ++){
            num = System.currentTimeMillis();
            temp.setQq_num((int)(num % 100000));
            temp.setStudent_id((int)(num % 10000));
            temp.setStudent_name("奇异博士-NO:" + i);
            int ass = discipleService.insert(temp);
            Assert.assertTrue(ass != -1);
        }
    }

}
