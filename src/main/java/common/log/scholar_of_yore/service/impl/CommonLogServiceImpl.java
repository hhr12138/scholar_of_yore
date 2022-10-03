package common.log.scholar_of_yore.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.entity.CommonLog;
import common.log.scholar_of_yore.service.CommonLogService;
import common.storage.king.entity.Log;
import common.storage.king.service.LogClient;
import lombok.SneakyThrows;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@DubboService
public class CommonLogServiceImpl implements CommonLogService {
    public static final String PSM = "common.log.scholar_of_yore";
    @DubboReference
    LogClient logClient;

    @SneakyThrows
    @Override
    public Integer insertLogs(List<CommonLog> commonLogs) {
        List<Log> logs = commonLogsToLogs(commonLogs);
        if(logs == null) return -1;
        return logClient.insertLogs(logs).getData();
    }

    @Override
    public RestResponse<Integer> realDeleteLogsGeneratedXDayAgo(String requestId, int x) {
        return logClient.realDeleteLogsGeneratedXDayAgo(x);
    }

    @Override
    public RestResponse<List<CommonLog>> selectLogsByRequestId(String requestId, String targetRequestId, long startTime, long endTime) {
        RestResponse<List<Log>> resp = logClient.selectLogsByRequestId(targetRequestId,startTime,endTime);
        List<Log> logs = resp.getData();
        List<CommonLog> commonLogs = logsToCommonLogs(requestId, logs);
        if(logs != null) return RestResponse.success(commonLogs);
        return RestResponse.fail(500,"scholar_of_yore serializable err");
    }

    @Override
    public RestResponse<List<CommonLog>> selectLogsByRequestId(String requestId, String targetRequestId) {
        long now = System.currentTimeMillis();
        long start = now - TimeUnit.DAYS.toMillis(3);
        return this.selectLogsByRequestId(requestId,targetRequestId,start,now);
    }

    @Override
    public RestResponse<List<CommonLog>> selectLogsGeneratedXMin(String requestId, int x) {
        RestResponse<List<Log>> resp = logClient.selectLogsGeneratedXMin(x);
        List<Log> logs = resp.getData();
        List<CommonLog> commonLogs = logsToCommonLogs(requestId, logs);
        if(logs != null) return RestResponse.success(commonLogs);
        return RestResponse.fail(500,"scholar_of_yore serializable err");
    }

    @Override
    public RestResponse<List<CommonLog>> selectLogsByUserId(String requestId, Long userId, long startTime, long endTime, int pageCnt, int size) {
        RestResponse<List<Log>> resp = logClient.selectLogsByUserId(userId,startTime,endTime,pageCnt,size);
        List<Log> logs = resp.getData();
        List<CommonLog> commonLogs = logsToCommonLogs(requestId, logs);
        if(logs != null) return RestResponse.success(commonLogs);
        return RestResponse.fail(500,"scholar_of_yore serializable err");
    }

    private List<CommonLog> logsToCommonLogs(String requestId, List<Log> logs){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.valueToTree(logs);
            List<CommonLog> commonLogs = objectMapper.treeToValue(jsonNode,List.class);
            return commonLogs;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Log> commonLogsToLogs(List<CommonLog> commonLogs){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode jsonNode = objectMapper.valueToTree(commonLogs);
            List<Log> logs = objectMapper.treeToValue(jsonNode,List.class);
            return logs;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
