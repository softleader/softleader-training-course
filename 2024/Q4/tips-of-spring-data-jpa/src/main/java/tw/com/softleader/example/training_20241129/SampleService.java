package tw.com.softleader.example.training_20241129;

import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.softleader.example.training_20241129.data.SampleDetailDao;
import tw.com.softleader.example.training_20241129.data.SampleDetailEntity;
import tw.com.softleader.example.training_20241129.data.SampleDetailVo;
import tw.com.softleader.example.training_20241129.data.SampleMainDao;
import tw.com.softleader.example.training_20241129.data.SampleMainEntity;
import tw.com.softleader.example.training_20241129.data.SampleMainVo;
import tw.com.softleader.example.training_20241129.data.SampleMapper;

@Service
@Transactional
@RequiredArgsConstructor
public class SampleService {

  final SampleMainDao sampleMainDao;
  final SampleDetailDao sampleDetailDao;
  final SampleMapper sampleMapper;

  public void save() {
    sampleMainDao.save(SampleMainEntity.builder().build());
  }

  public void saveWithDetail() {
    sampleMainDao.save(
        SampleMainEntity.builder()
            .name("sample1")
            .details(List.of(SampleDetailEntity.builder().name("sample1-detail1").build()))
            .build());
  }

  public void replaceDetail() {
    var main = sampleMainDao.findByName("sample1");
    main.getDetails().clear();
    main.getDetails().add(SampleDetailEntity.builder().name("sample1-detail2").build());
    sampleMainDao.save(main);
  }

  public SampleMainVo findMain(String s) {
    return Optional.ofNullable(sampleMainDao.findByName(s))
        .map(sampleMapper::fromEntity)
        .orElse(null);
  }

  public void saveDetail(SampleDetailVo vo) {
    var entity = sampleMapper.toEntity(vo);
    sampleDetailDao
        .findById(vo.getId())
        .ifPresent(dbEntity -> sampleDetailDao.save(sampleMapper.replaceEntity(entity, dbEntity)));
  }

  public List<SampleMainEntity> findAll() {
    return sampleMainDao.findAll();
  }

  public SampleDetailEntity findDetailByName(String s) {
    return sampleDetailDao.findByName(s);
  }
}
