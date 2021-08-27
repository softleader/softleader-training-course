package tw.com.softleader.demoweb.tsample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.softleader.demoweb.tsample.entity.PolicyEntity;

public interface PolicyDao extends JpaRepository<PolicyEntity, Long> {
}
