package tw.com.softleader.demoweb.tsample.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tw.com.softleader.demoweb.tsample.entity.PolicyCoverageCustomerEntity;

public interface PolicyCoverageCustomerDao extends JpaRepository<PolicyCoverageCustomerEntity, Long> {
}
