package tw.com.softleader.training.mapstruct_training;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.softleader.kapok.core.KapokVersion;
import tw.com.softleader.kapok.test.KapokTestMethodOrder;

@SpringBootTest
@Timeout(1)
@KapokTestMethodOrder
class MapstructTrainingApplicationTests {

  @Test
  void contextLoads() {
    Assertions.assertThat(KapokVersion.getVersion()).isNotBlank();
  }
}
