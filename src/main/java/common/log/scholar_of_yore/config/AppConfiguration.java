package common.log.scholar_of_yore.config;

import common.entity.valhalla.aspect.SerializerAspect;
import common.entity.valhalla.util.RequestIdUtil;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("app")
public class AppConfiguration {
    public static final String PSM = "common.log.scholar_of_yore";
    private long workId;
    private long datacenterId;
    @Bean
    public RequestIdUtil snowflakeIdUtil(){
        return new RequestIdUtil(workId,datacenterId);
    }
//    @Bean
//    public SerializerAspect serializerAspect(){
//        return new SerializerAspect();
//    }

    public long getWorkId() {
        return workId;
    }

    public void setWorkId(long workId) {
        this.workId = workId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }
}
