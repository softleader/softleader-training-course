package tw.com.softleader.training.effectiveusingdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PolicyDao extends JpaRepository<PolicyEntity, Long> {


  PolicyVo findByPolicyNo(String policyNo);

  @Query("select p.previousPolicyNo from PolicyEntity p where p.policyNo = ?1")
  String findPrevPolicyNo(String policyNo);

}
