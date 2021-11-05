package tw.com.softleader.springdatatraining.member;

import org.springframework.data.jpa.repository.Query;
import tw.com.softleader.springdatatraining.common.GenericDao;

import java.util.Optional;

public interface MemberDao extends GenericDao<MemberEntity> {

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    Optional<MemberEntity> findByName(String name);

//    MemberEntity findByPhone_PhoneNo(String phoneNo);

    @Query("from MemberEntity m join PhoneEntity p on m.phoneId = p.id where p.phoneNo = ?1")
    MemberEntity findByPhoneNo(String phoneNo);

}
