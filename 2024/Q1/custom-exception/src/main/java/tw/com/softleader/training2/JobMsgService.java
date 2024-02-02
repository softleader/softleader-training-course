package tw.com.softleader.training2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)
@RequiredArgsConstructor
public class JobMsgService {

  final JobMsgDao jobMsgDao;

  public void saveJobMsg(JobExecutionException e) {
    JobMsg.builder()
        .recordTime(LocalDateTime.now())
        .jobName(e.getJobName())
        .msgLevel("ERROR")
        .msgContent(e.getMessage())
        .build();
  }

}
