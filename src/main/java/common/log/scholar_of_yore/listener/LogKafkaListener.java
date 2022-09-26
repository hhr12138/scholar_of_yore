package common.log.scholar_of_yore.listener;

import common.storage.king.entity.Log;
import common.storage.king.service.LogService;
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
    LogService logService;
    @KafkaListener(topics = "common.log.scholar-of-yore.common-log",containerFactory = "batchFactory")
    public void commonLogListener(ConsumerRecords<String,Log> records){
        List<Log> logs = new ArrayList<>();
        for(ConsumerRecord<String,Log> record: records){
            Log val = record.value();
            logs.add(val);
        }
        logService.insertLogs(logs);
    }
}
