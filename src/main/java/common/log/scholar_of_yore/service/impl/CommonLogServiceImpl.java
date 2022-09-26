package common.log.scholar_of_yore.service.impl;

import common.entity.valhalla.vo.RestResponse;
import common.log.scholar_of_yore.service.CommonLogService;
import common.storage.king.entity.Log;
import common.storage.king.service.LogService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@DubboService
public class CommonLogServiceImpl implements CommonLogService {
    @DubboReference
    LogService logService;

    @Override
    public Integer insertLogs(List<Log> logs) {
        return logService.insertLogs(logs).getData();
    }

    @DubboService
    @Override
    public RestResponse<Integer> realDeleteLogsGeneratedXDayAgo(int x) {
        return logService.realDeleteLogsGeneratedXDayAgo(x);
    }

    @DubboService
    @Override
    public RestResponse<List<Log>> selectLogsByRequestId(Long requestId, long startTime, long endTime, int size) {
        return logService.selectLogsByRequestId(requestId,startTime,endTime,size);
    }

    @DubboService
    @Override
    public RestResponse<List<Log>> selectLogsGeneratedXMin(int x) {
        return logService.selectLogsGeneratedXMin(x);
    }

    @DubboService
    @Override
    public RestResponse<List<Log>> selectLogsByUserId(Long userId, long startTime, long endTime, int pageCnt, int size) {
        return logService.selectLogsByUserId(userId,startTime,endTime,pageCnt,size);
    }

}
