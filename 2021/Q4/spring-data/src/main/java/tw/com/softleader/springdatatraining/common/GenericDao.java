package tw.com.softleader.springdatatraining.common;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface GenericDao<T extends GenericEntity> extends JpaRepository<T, Long> {

}
