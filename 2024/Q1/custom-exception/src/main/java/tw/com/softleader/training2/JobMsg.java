package tw.com.softleader.training2;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobMsg {
  @Id
  Long id;

  LocalDateTime recordTime;
  String jobName;
  String msgLevel; // INFO, WARN, ERROR
  String msgContent;

}
