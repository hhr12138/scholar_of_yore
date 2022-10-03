package common.log.scholar_of_yore.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.entity.valhalla.util.RequestIdUtil;
import common.log.scholar_of_yore.config.AppConfiguration;
import common.log.scholar_of_yore.config.KafkaConfiguration;
import common.log.scholar_of_yore.entity.CommonLog;
import common.log.scholar_of_yore.service.CommonLogService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogKafkaListener {
    @Resource
    CommonLogService commonLogService;
    @Resource
    RequestIdUtil requestIdUtil;
    ObjectMapper objectMapper = new ObjectMapper();
    @KafkaListener(topics = KafkaConfiguration.DEFAULT_TOPIC,containerFactory = "batchFactory")
    public void commonLogListener(ConsumerRecords<String,String> records){
        List<CommonLog> logs = new ArrayList<>();
        for(ConsumerRecord<String,String> record: records){
            String logStr = record.value();
            CommonLog log = null;
            try {
                log = objectMapper.readValue(logStr,CommonLog.class);
            } catch (JsonProcessingException e) {
                String requestId = requestIdUtil.getRequestId();
                log = new CommonLog(-1L,requestId, AppConfiguration.PSM,"error","type receiving error: logObj not instance of log");
                continue;
            }
            logs.add(log);
        }
        commonLogService.insertLogs(logs);
    }
}
