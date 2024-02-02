package tw.com.softleader.training2;

import org.springframework.stereotype.Component;

import java.nio.file.Paths;
import java.time.LocalTime;

@Component
public class DataReadJob implements ExecutableJob {

  public void execute() {
    // do something
    var file = Paths.get("/data/need/to/read").toFile();
    if (!file.exists()) {
      // 中斷Job, 但不存Msg
      throw new JobAbortException();
    }

  }

  @Override public LocalTime runAt() {
    return LocalTime.of(1, 30);
  }

  @Override
  public String getJobName() {
    return "DataReadJob";
  }

}
