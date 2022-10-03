package common.log.scholar_of_yore.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.log.scholar_of_yore.entity.CommonLog;
import common.storage.king.entity.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NoSpringBootTest {
    @Test
    public void changTest(){
        Log log = new Log(-1L,"adsfaf","dafadsf","warn","dafadf");
        Long start = System.currentTimeMillis();
        for(int i = 0; i < 10000; i++){
            CommonLog commonLog = new CommonLog();
            BeanUtils.copyProperties(log,commonLog);
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    @Test
    public void changeTest1() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Log> logs = new ArrayList<>();
        Log log = new Log(-1L,"adsfaf","dafadsf","warn","dafadf");
        for(int i = 0; i < 10000; i++){
            logs.add(log);
        }
        long start = System.currentTimeMillis();
        JsonNode jsonNode = objectMapper.valueToTree(logs);
        List<CommonLog> commonLogs = objectMapper.treeToValue(jsonNode,List.class);
        System.out.println(System.currentTimeMillis() - start);
    }
}
