package tw.com.softleader.example.training_20241129;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleTest {

  @Autowired SampleService sampleService;

  @Test
  public void test() {
    sampleService.save();
    // 驗證測試基本架構OK
    Assertions.assertThat(sampleService.findAll()).isNotEmpty();

    sampleService.saveWithDetail();
    // 驗證Detail有因為cascadeType而被儲存
    Assertions.assertThat(sampleService.findAll()).isNotEmpty();
    // 驗證sample1-detail1的main有拉到關聯
    Assertions.assertThat(sampleService.findDetailByName("sample1-detail1").getMain())
        .isNotNull()
        .extracting("name")
        .isEqualTo("sample1");

    sampleService.replaceDetail();
    // 驗證sample1-detail1因關聯移除而被刪除
    Assertions.assertThat(sampleService.findDetailByName("sample1-detail1")).isNull();
    // 驗證sample1-detail2已經與main綁上關聯
    Assertions.assertThat(sampleService.findDetailByName("sample1-detail2").getMain())
        .isNotNull()
        .extracting("name")
        .isEqualTo("sample1");

    var main = sampleService.findMain("sample1");
    var detail = main.getDetails().get(0);
    detail.setName("sample1-detail3");
    sampleService.saveDetail(detail);
    // 驗證detail跟main的關聯還在
    Assertions.assertThat(sampleService.findDetailByName("sample1-detail3").getMain()).isNotNull();
    var newMain = sampleService.findMain("sample1");
    // 驗證從main方向去看detail的name已經修改
    Assertions.assertThat(newMain.getDetails().get(0).getName()).isEqualTo("sample1-detail3");
  }
}
