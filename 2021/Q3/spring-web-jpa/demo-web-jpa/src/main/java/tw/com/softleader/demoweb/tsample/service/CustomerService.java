package tw.com.softleader.demoweb.tsample.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tw.com.softleader.demoweb.tsample.dao.CustomerAddressDao;
import tw.com.softleader.demoweb.tsample.dao.CustomerHealthyDao;
import tw.com.softleader.demoweb.tsample.dao.CustomerInfoDao;
import tw.com.softleader.demoweb.tsample.entity.CustomerAddressEntity;
import tw.com.softleader.demoweb.tsample.entity.CustomerHealthyEntity;
import tw.com.softleader.demoweb.tsample.entity.CustomerInfoEntity;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CustomerService {

    final CustomerInfoDao customerInfoDao;
    final CustomerAddressDao customerAddressDao;
    final CustomerHealthyDao customerHealthyDao;

    public void save() {
        log.warn("CustomerService.save");
//        Transaction2-Customer
//        └Transaction2-1 Customer-Info
//        └Transaction2-2 Customer-Address
//        └Transaction2-3 Customer-Healthy

        var customer = new CustomerInfoEntity();

        var address1 = new CustomerAddressEntity();
        var address2 = new CustomerAddressEntity();

        var healthy1 = new CustomerHealthyEntity();
        var healthy2 = new CustomerHealthyEntity();
        var healthy3 = new CustomerHealthyEntity();

        customerInfoDao.save(customer);
        customerAddressDao.save(address1);
        customerAddressDao.save(address2);
        customerHealthyDao.save(healthy1);
        customerHealthyDao.save(healthy2);
        customerHealthyDao.save(healthy3);

    }

}
