package tw.com.softleader.training2;

import lombok.Getter;

public class JobExecutionException extends RuntimeException {

  @Getter
  private final String jobName;

  public JobExecutionException(String jobName, Throwable cause) {
    super(cause);
    this.jobName = jobName;
  }

}
