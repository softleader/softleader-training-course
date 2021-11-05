package tw.com.softleader.springdatatraining.member;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import tw.com.softleader.springdatatraining.common.GenericEntity;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@DataJpaTest
@Transactional
class MemberDaoTest {

    @Autowired
    MemberDao memberDao;

    @Autowired
    PhoneDao phoneDao;

    @Autowired
    EntityManager entityManager;

    @Test
    public void test1() {
        MemberEntity rhys = MemberEntity.builder()
            .name("Rhys")
            .salary(new BigDecimal("999999"))
            .loginTime(LocalDateTime.now())
            .type(MemberType.EMPLOYEE)
            .build();

        var dbRhys = memberDao.save(rhys);
        var id = dbRhys.getId();
        log.info("new: {}", rhys);
        log.info("db: {}", dbRhys);

        log.info("id: {}={}", rhys.getId(), id);
        dbRhys.setName("RRRR");
        flushAndDetach(dbRhys);
        var dbRhys2 = memberDao.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID不對"));
        log.info("db2: {}", dbRhys2.name);
    }
    @Test
    public void test2() {
        MemberEntity matt = MemberEntity.builder()
            .id(99L)
            .name("matt")
            .salary(new BigDecimal("123456789"))
            .loginTime(LocalDateTime.now())
            .type(MemberType.EMPLOYEE)
            .build();

        memberDao.save(matt);
        flushAndDetach(matt);
        log.warn("matt: {}", matt.getId());

        var mattForm = MemberEntity.builder()
            .id(1L)
            .name("matt1")
            .salary(new BigDecimal("123456789"))
            .loginTime(LocalDateTime.now())
            .type(MemberType.EMPLOYEE)
            .build();

        var dbMatt = memberDao.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("ID不對"));
        dbMatt.setName(mattForm.getName());
        memberDao.save(dbMatt);
        flushAndDetach(dbMatt);
        log.warn("dbMatt: {}", dbMatt.getId());

        var dbMatt2 = memberDao.findById(1L)
            .orElseThrow(() -> new IllegalArgumentException("ID不對"));
        log.warn("dbMatt2: {}", dbMatt2.getId());

    }

    @Test
    public void test3() {
        PhoneEntity phone = PhoneEntity.builder().phoneNo("098765421").build();
        MemberEntity rhys = MemberEntity.builder()
            .name("Rhys")
            .build();

        memberDao.save(rhys);
        var rhysId = rhys.getId();
        flushAndDetach(rhys);

//        var dbRhys = memberDao.findByName("Rhys")
//            .orElseThrow(() -> new IllegalArgumentException("ID不對"));
//        var phones = new ArrayList<PhoneEntity>();
//        phones.add(phone);
//        dbRhys.setPhones(phones);
//        memberDao.save(dbRhys);
//        flushAndDetach(dbRhys);

        var dbRhys2 = memberDao.findByName("Rhys")
            .orElseThrow(() -> new IllegalArgumentException("ID不對"));
        log.warn("dbRhys2: {}", dbRhys2);
        flushAndDetach(dbRhys2);

//        var allPhone = phoneDao.findAll();
//        phoneDao.deleteAll(allPhone);
//        flushAndDetach(allPhone);

        var dbRhys3 = memberDao.findByName("Rhys")
            .orElseThrow(() -> new IllegalArgumentException("ID不對"));
        log.warn("dbRhys3: {}", dbRhys3);
    }

    private void flushAndDetach(GenericEntity entity) {
        entityManager.flush();
        entityManager.detach(entity);
    }

    private void flushAndDetach(List<? extends GenericEntity> entities) {
        entityManager.flush();
        for (GenericEntity entity : entities) {
            entityManager.detach(entity);
        }
    }

}