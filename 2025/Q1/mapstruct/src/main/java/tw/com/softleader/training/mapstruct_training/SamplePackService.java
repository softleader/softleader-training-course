package tw.com.softleader.training.mapstruct_training;

import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SamplePackService {

  final SamplePackDao samplePackDao;
  final SamplePackMapper samplePackMapper;

  @PostConstruct
  public void init() {
    var entity =
        samplePackDao.save(
            SamplePackEntity.builder()
                .samples(
                    List.of(
                        SampleEntity.builder().name("AAA").age(BigDecimal.valueOf(1)).build(),
                        SampleEntity.builder().name("BBB").age(BigDecimal.valueOf(2)).build()))
                .build());
  }

  public SamplePackEntity save(SamplePackUpdateCmd cmd) {
    return samplePackDao.findAll().stream()
        .findFirst()
        .map(dbEntity -> samplePackMapper.update(dbEntity, cmd))
        .orElseThrow();
  }
}
