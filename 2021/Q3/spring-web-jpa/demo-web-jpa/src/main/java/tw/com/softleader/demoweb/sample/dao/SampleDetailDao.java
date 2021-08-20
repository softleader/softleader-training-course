package tw.com.softleader.demoweb.sample.dao;

import org.springframework.data.repository.CrudRepository;
import tw.com.softleader.demoweb.sample.entity.SampleDetailEntity;

public interface SampleDetailDao extends CrudRepository<SampleDetailEntity, Long> {

}
