package tw.com.softleader.training.java_exp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SampleService {

  final SampleDao sampleDao;

  public SampleService(SampleDao sampleDao) {
    this.sampleDao = sampleDao;
  }

  public void addSample(String name) {
    var sampleEntity = new SampleEntity();
    sampleEntity.setName(name);
    sampleDao.save(sampleEntity);
  }

  public void addSubSample(String name, List<String> subs) {
    var sample = sampleDao.findByName(name).orElseThrow();

    sample.getSubSamples().clear();
    subs.stream().map(subName -> {
//      var sub = new SubSampleEntity();
      var sub = new SubSampleEmbed();
      sub.setName(subName);
      return sub;
    }).forEach(sample.getSubSamples()::add);

    sampleDao.save(sample);
  }

  public void addTag(String name, List<String> tag) {
    var sample = sampleDao.findByName(name).orElseThrow();
    tag.forEach(sample.getTags()::add);
    sampleDao.save(sample);
  }

  public void deleteSubSample(String name, String subName) {
    var sample = sampleDao.findByName(name).orElseThrow();
    sample.getSubSamples().removeIf(sub -> sub.getName().equals(subName));
    sampleDao.save(sample);
  }

}
