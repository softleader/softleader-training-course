package tw.com.softleader.demoweb.tsample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.softleader.demoweb.tsample.entity.CustomerInfoEntity;

public interface CustomerInfoDao extends JpaRepository<CustomerInfoEntity, Long> {
}
