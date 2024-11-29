package tw.com.softleader.example.training_20241129.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleMainDao extends JpaRepository<SampleMainEntity, Long> {
  SampleMainEntity findByName(String name);
}
