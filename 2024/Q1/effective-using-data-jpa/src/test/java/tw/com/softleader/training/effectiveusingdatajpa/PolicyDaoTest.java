package tw.com.softleader.training.effectiveusingdatajpa;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@DataJpaTest public class PolicyDaoTest {

  @Autowired PolicyDao policyDao;

  @Test public void test1() {
    var policies = policyDao.findAll();
    Assertions.assertThat(policies).hasSize(0);
  }

  @Test public void test2() {
    policyDao.save(PolicyEntity.builder().policyNo("POL20240001").previousPolicyNo("POL20230001").build());

    var start = LocalDateTime.now();
    var prevPolicyNo = policyDao.findPrevPolicyNo("POL20240001");
    var finish = LocalDateTime.now();
    log.info("policyDao.findPrevPolicyNo cost: {}", ChronoUnit.MILLIS.between(start, finish));

    Assertions.assertThat(prevPolicyNo).isEqualTo("POL20230001");
  }

  @Test
  public void test3() {
    policyDao.save(PolicyEntity.builder().policyNo("POL20240002").productTypeId(999L).previousPolicyNo("POL20230002")
        .endstPolicyNo("POL20230002_ENDST").quotationNo("POL20230002_QNO").originQuotationNo("POL20230002_OQNO")
        .renewPolicyNo("POL20230002_REN").endstNo(999L).mainPolicyEndstNo(999L).extEndstNo(999L).build());

    var policyVo = policyDao.findByPolicyNo("POL20240002");

    Assertions.assertThat(policyVo)
        .extracting(
            "policyNo", "productTypeId", "previousPolicyNo", "endstPolicyNo", "quotationNo", "originQuotationNo",
            "renewPolicyNo", "endstNo", "mainPolicyEndstNo", "extEndstNo")
        .contains(
            "POL20240002", 999L, "POL20230002", "POL20230002_ENDST", "POL20230002_QNO", "POL20230002_OQNO",
            "POL20230002_REN", 999L, 999L, 999L);
  }

  @Test public void test4slow() {
    var policyNos = new ArrayList<String>();
    var policies = new ArrayList<PolicyEntity>();
    for (int i = 0; i < 100000; i++) {
      var policyNo = Integer.toString(i);
      policyNos.add(policyNo);
      policies.add(PolicyEntity.builder().policyNo(policyNo).build());
    }
    policyDao.saveAll(policies);

    var start = LocalDateTime.now();
    var dbPolicies = policyNos.stream()
        .map(policyNo -> policyDao.findByPolicyNo(policyNo))
        .toList();
    var finish = LocalDateTime.now();
    log.info("policyDao.findByPolicyNo x size({}) cost: {}", policyNos.size(), ChronoUnit.MILLIS.between(start, finish));

    Assertions.assertThat(dbPolicies.size()).isEqualTo(policies.size());
  }

  @Test public void test4() {
    var policies = new ArrayList<PolicyEntity>();
    {
      var start = LocalDateTime.now();
      for (int i = 0; i < 100000; i++) {
        var policyNo = Integer.toString(i);
        policies.add(PolicyEntity.builder().policyNo(policyNo).build());
      }
      policyDao.saveAll(policies);
      var finish = LocalDateTime.now();
      log.info("policyDao.saveAll cost: {}", ChronoUnit.MILLIS.between(start, finish));
    }

    {
      var start = LocalDateTime.now();
      var dbPolicies = policyDao.findAll();
      var finish = LocalDateTime.now();
      log.info("policyDao.findAll cost: {}", ChronoUnit.MILLIS.between(start, finish));
      Assertions.assertThat(dbPolicies.size()).isEqualTo(policies.size());
    }

  }

}
