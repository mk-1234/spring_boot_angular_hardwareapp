package hr.tvz.krznar.hardwareapp;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

@Slf4j
public class HardwareJob extends QuartzJobBean {

    @Autowired
    HardwareService hardwareService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("Ovo su trenutno dostupni hardveri");
        log.info("--------------------------------");
        for (HardwareDTO h : hardwareService.findAllInStock()) {
            log.info(h.getName() + " - " + h.getStock());
        }
        log.info("--------------------------------\n");
    }

}
