package tw.com.softleader.demoweb.sample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import tw.com.softleader.demoweb.sample.entity.SampleEntity;

import java.time.LocalDate;
import java.util.List;

public interface SampleDao extends JpaRepository<SampleEntity, Long>, JpaSpecificationExecutor<SampleEntity> {

    @Query("from SampleEntity where date >= ?1 and date <= ?2")
    List<SampleEntity> findByDateBetween(LocalDate from, LocalDate to);


}
