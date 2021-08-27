package tw.com.softleader.demoweb.tsample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.softleader.demoweb.tsample.entity.CustomerHealthyEntity;

public interface CustomerHealthyDao extends JpaRepository<CustomerHealthyEntity, Long> {
}
