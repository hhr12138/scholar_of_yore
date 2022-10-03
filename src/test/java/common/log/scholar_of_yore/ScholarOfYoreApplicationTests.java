package common.log.scholar_of_yore;

import common.entity.valhalla.util.SnowflakeIdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class ScholarOfYoreApplicationTests {

    @Resource
    SnowflakeIdUtil snowflakeIdUtil;
    @Test
    void contextLoads() {
        System.out.println(snowflakeIdUtil.nextId());
    }

}
