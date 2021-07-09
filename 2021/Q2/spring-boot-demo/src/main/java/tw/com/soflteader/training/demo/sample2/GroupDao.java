package tw.com.soflteader.training.demo.sample2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupDao extends JpaRepository<GroupEntity, Long> {

    GroupEntity findByName(String name);

    @Query("select g.name as group, m.name as memberName from GroupEntity g join g.members m")
    List<GroupMemberVo> findAllGroupMember();

}
