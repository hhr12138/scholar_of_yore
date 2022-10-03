package common.log.scholar_of_yore.handler;

import common.entity.valhalla.util.RequestIdUtil;
import common.entity.valhalla.util.SnowflakeIdUtil;
import common.log.scholar_of_yore.service.CommonLogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
public class LogDeleteHandler {
    @Resource
    CommonLogService commonLogService;
    public int deletedDay = 3;
    @Resource
    RequestIdUtil requestIdUtil;

    //秒 分钟 小时 日 月 周 年
    @Scheduled(cron = "0 0 3 * * * *")
    private void deleteLogXDaysAgo(){
        String requestId = requestIdUtil.getRequestId();
        commonLogService.realDeleteLogsGeneratedXDayAgo(requestId,deletedDay);
    }
}
