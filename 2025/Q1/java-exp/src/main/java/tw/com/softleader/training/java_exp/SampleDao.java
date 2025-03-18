package tw.com.softleader.training.java_exp;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SampleDao extends JpaRepository<SampleEntity, Long> {

  Optional<SampleEntity> findByName(String name);

}
