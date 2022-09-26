package common.log.scholar_of_yore.handler;

import common.log.scholar_of_yore.service.CommonLogService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LogDeleteHandler {
    @Resource
    CommonLogService logService;
    public int deletedDay = 3;

    //秒 分钟 小时 日 月 周 年
    @Scheduled(cron = "0 0 3 * * * *")
    private void deleteLogXDaysAgo(){
        logService.realDeleteLogsGeneratedXDayAgo(deletedDay);
    }
}
