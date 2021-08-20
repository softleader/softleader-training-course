package tw.com.softleader.demoweb.sample.dao;

import org.springframework.data.repository.CrudRepository;
import tw.com.softleader.demoweb.sample.entity.SampleEntity;

public interface SampleDao extends CrudRepository<SampleEntity, Long> {

}
