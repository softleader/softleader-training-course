package tw.com.softleader.demoweb.sample.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tw.com.softleader.demoweb.sample.entity.SampleEntity;

import java.time.LocalDate;
import java.util.List;

public interface SampleDao extends CrudRepository<SampleEntity, Long> {

    @Query("from SampleEntity where date >= ?1 and date <= ?2")
    List<SampleEntity> findByDateBetween(LocalDate from, LocalDate to);


}
