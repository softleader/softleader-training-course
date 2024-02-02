package tw.com.softleader.training2;

import java.time.LocalTime;

public interface ExecutableJob {

  void execute() throws JobExecutionException;
  LocalTime runAt();
  String getJobName();

  default void doExecute() {
    try {
      execute();
    } catch (JobExecutionException e) {
      throw new JobExecutionException(getJobName(), e);
    }
  }

}
