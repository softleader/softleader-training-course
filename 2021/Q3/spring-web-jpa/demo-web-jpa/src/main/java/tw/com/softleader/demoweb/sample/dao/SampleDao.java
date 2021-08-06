package tw.com.softleader.demoweb.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import tw.com.softleader.demoweb.sample.entity.SampleEntity;

public interface SampleDao extends JpaRepository<SampleEntity, Long>, JpaSpecificationExecutor<SampleEntity> {

}
