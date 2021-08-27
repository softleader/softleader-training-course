package tw.com.softleader.demoweb.tsample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tw.com.softleader.demoweb.tsample.dao.CoverageDao;
import tw.com.softleader.demoweb.tsample.dao.PolicyCoverageCustomerDao;
import tw.com.softleader.demoweb.tsample.dao.PolicyDao;
import tw.com.softleader.demoweb.tsample.entity.CoverageEntity;
import tw.com.softleader.demoweb.tsample.entity.PolicyCoverageCustomerEntity;
import tw.com.softleader.demoweb.tsample.entity.PolicyEntity;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class PolicyService {

    final PolicyDao policyDao;
    final CoverageDao coverageDao;
    final PolicyCoverageCustomerDao policyCoverageCustomerDao;

    public void save() {
        log.warn("PolicyService.save");
        policyDao.save(new PolicyEntity());
        coverageDao.save(new CoverageEntity());
        policyCoverageCustomerDao.save(new PolicyCoverageCustomerEntity());
        throw new RuntimeException();
    }

}
