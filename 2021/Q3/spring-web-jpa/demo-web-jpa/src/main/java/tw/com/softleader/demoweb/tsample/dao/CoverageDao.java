package tw.com.softleader.demoweb.tsample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.softleader.demoweb.tsample.entity.CoverageEntity;

public interface CoverageDao extends JpaRepository<CoverageEntity, Long> {
}
