package tw.com.softleader.demoweb.tsample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class NbService {

    final CustomerService customerService;
    final PolicyService policyService;

    public void save() {
        log.warn("NbService.save");
        saveCustomer();
        savePolicy();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveCustomer() {
        log.warn("call customerService");
        customerService.save();
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void savePolicy() {
        log.warn("call policyService");
        policyService.save();
    }

}
