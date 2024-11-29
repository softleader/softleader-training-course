package tw.com.softleader.example.training_20241129;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.springframework.boot.test.context.SpringBootTest;
import tw.com.softleader.kapok.core.KapokVersion;
import tw.com.softleader.kapok.test.KapokTestMethodOrder;

@SpringBootTest
@Timeout(1)
@KapokTestMethodOrder
class Training20241129ApplicationTests {

  @Test
  void contextLoads() {
    Assertions.assertThat(KapokVersion.getVersion()).isNotBlank();
  }
}
