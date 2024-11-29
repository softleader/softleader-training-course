package tw.com.softleader.example.training_20241129.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleDetailDao extends JpaRepository<SampleDetailEntity, Long> {

  SampleDetailEntity findByName(String name);
}
