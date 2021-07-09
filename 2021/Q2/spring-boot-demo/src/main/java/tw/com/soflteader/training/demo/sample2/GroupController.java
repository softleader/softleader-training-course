package tw.com.soflteader.training.demo.sample2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.com.soflteader.training.demo.sample1.MemberEntity;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired GroupDao groupDao;

    @GetMapping("/{groupName}")
    public GroupOnlyDto query(@PathVariable String groupName) {
        GroupEntity entity = groupDao.findByName(groupName);
        return GroupOnlyDto.builder()
            .id(entity.getId())
            .name(entity.getName())
            .build();
    }

    @GetMapping("/members")
    public List<GroupMemberVo> queryGm() {
        return groupDao.findAllGroupMember();
    }

    @PostMapping("/{groupName}")
    public void add(@PathVariable String groupName, @RequestBody GroupReq groupReq) {

        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setName(groupName);
        groupEntity.setMembers(groupReq.getMembers().stream()
            .map(m -> MemberEntity.builder().name(m).build())
            .collect(Collectors.toList()));

        groupDao.save(groupEntity);

    }

}
