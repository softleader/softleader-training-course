package tw.com.softleader.demoweb.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.softleader.demoweb.sample.entity.SampleDetailEntity;

public interface SampleDetailDao extends JpaRepository<SampleDetailEntity, Long> {

}
