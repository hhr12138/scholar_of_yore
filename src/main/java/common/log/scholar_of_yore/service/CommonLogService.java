package common.log.scholar_of_yore.service;

import common.entity.valhalla.vo.RestResponse;
import common.storage.king.entity.Log;

import java.util.List;

public interface CommonLogService {
    Integer insertLogs(List<Log> logs);

    RestResponse<Integer> realDeleteLogsGeneratedXDayAgo(int x);

    RestResponse<List<Log>> selectLogsByRequestId(Long requestId, long startTime, long endTime, int size);

    RestResponse<List<Log>> selectLogsGeneratedXMin(int x);

    RestResponse<List<Log>> selectLogsByUserId(Long userId, long startTime, long endTime, int pageCnt, int size);
}
