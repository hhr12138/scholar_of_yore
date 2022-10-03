package common.log.scholar_of_yore.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import common.entity.valhalla.util.RequestIdUtil;
import common.entity.valhalla.util.SerializerUtil;
import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.entity.CommonLog;
import common.log.scholar_of_yore.service.CommonLogService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class CommonLogServiceImplTest {

    @Resource
    CommonLogService commonLogService;
    RequestIdUtil requestIdUtil = new RequestIdUtil();
    @Test
    void insertLogs(){
        List<CommonLog> logs = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            logs.add(new CommonLog(-1L,"cadfdaf","PSM","error","test"));
        }
        commonLogService.insertLogs(logs);
    }

    @Test
    void realDeleteLogsGeneratedXDayAgo() {
        String requestId = requestIdUtil.getRequestId();
        RestResponse<Integer> integerRestResponse = commonLogService.realDeleteLogsGeneratedXDayAgo(requestId, 3);
        if(integerRestResponse.getData() != 200){
            System.out.println(integerRestResponse.getMsg());
            return;
        }
        System.out.println(integerRestResponse.getData());
    }

    @Test
    void selectLogsByRequestId() throws JsonProcessingException {
        String requestId = requestIdUtil.getRequestId();
        RestResponse<List<CommonLog>> listRestResponse = commonLogService.selectLogsByRequestId(requestId, "cadfdaf");
        List<CommonLog> data = listRestResponse.getData();
        for(CommonLog commonLog: data){
            System.out.println(commonLog);
        }
    }

    @Test
    void selectLogsGeneratedXMin() {

    }

    @Test
    void selectLogsByUserId() {
    }
}