package tw.com.softleader.training2;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
@RequiredArgsConstructor
public class JobExecutor {

  final List<ExecutableJob> jobs;
  final JobMsgService jobMsgService;
  ExecutorService executor = Executors.newSingleThreadExecutor();

  // 定期每分鐘檢查是否有job要執行
  @Scheduled(cron = "0 * * * * *")
  public void doExecute() {
    jobs.stream()
        .filter(job -> job.runAt().equals(LocalTime.now().withSecond(0)))
        .forEach(job -> executor.submit(() -> {
          try {
            job.doExecute();
          } catch (JobAbortException ignore) {
            // do nothing
          } catch (JobExecutionException e) {
            jobMsgService.saveJobMsg(e);
          }
        }));
  }


}
